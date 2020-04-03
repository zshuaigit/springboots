package com.zshuai.springboot.service;

import com.zshuai.springboot.po.Item;
import com.zshuai.springboot.repository.ItemRepository;
import com.zshuai.springboot.vo.AvgResult;
import com.zshuai.springboot.vo.QueryResult;
import io.swagger.models.auth.In;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zshuai
 *
 * @Date :2020/4/3 11:01 AM
 * @Version 1.0
 **/
@Service
public class ItemService {
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    ItemRepository itemRepository;

    public String createIndex(Class<Item> itemClass) {
        //创建索引
        boolean tag = elasticsearchTemplate.createIndex(itemClass);
        logger.info("创建索引成功！");
        //添加映射 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        boolean mapping = elasticsearchTemplate.putMapping(itemClass);
        logger.info("添加映射成功成功！");
        if(tag && mapping){
            return "创建索引、映射成功";
        }else{
            return "创建失败，请查看日志!";
        }
    }

    public String deleteIndex(Class<Item> itemClass) {
        //删除索引
        boolean tag = elasticsearchTemplate.deleteIndex(itemClass);
        logger.info("删除索引成功！");
        //添加映射 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        if(tag ){
            return "删除索引成功";
        }else{
            return "删除失败，请查看日志!";
        }
    }


    public String addOne(Item item) {
        Item save = itemRepository.save(item);
        if (save!=null){
            logger.info("保存的信息为"+save.toString());
        }
        return "保存成功";
    }

    public List<Item> findAll() {
        Iterable<Item> items = itemRepository.findAll();
        List<Item> list = new ArrayList();
        for (Item item : items) {
            System.out.println(item);
            list.add(item);
        }
        return list;
    }
    public List<Item> findAllByPriceDesc() {
        Iterable<Item> items = itemRepository.findAll(Sort.by("price").descending());
        List<Item> list = new ArrayList();
        for (Item item : items) {
            System.out.println(item);
            list.add(item);
        }
        return list;
    }

    public List<Item> findByTitle(String title) {
        List<Item> items = itemRepository.findByTitle(title);
        if (items != null){
            return  items;
        }
        return null;
    }

    public List<Item> finByPriceBetween(Double start, Double stop) {
        List<Item> items = itemRepository.findByPriceBetween(start, stop);
        if (items != null){
            return  items;
        }
        return null;
    }



    public String saveAll(List<Item> list) {
        Iterable<Item> items = itemRepository.saveAll(list);
        for (Item item : items) {
            System.out.println(item);
        }
        return "保存成功！";

    }


    public List<Item> queryByBuilderMatch(String row ,String val) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        queryBuilder.withQuery(QueryBuilders.matchQuery(row, val));

        Page<Item> page = itemRepository.search(queryBuilder.build());

        long totalElements = page.getTotalElements();
        logger.info("获取的总条数："+totalElements);

        List<Item> list = new ArrayList();
        for (Item item : page) {
            System.out.println(item);
            list.add(item);
        }
        return list;
    }

    public List<Item> queryByBuilderTerm(String row ,String val) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        queryBuilder.withQuery(QueryBuilders.termQuery(row, val));

        Page<Item> page = itemRepository.search(queryBuilder.build());

        long totalElements = page.getTotalElements();
        logger.info("获取的总条数："+totalElements);

        List<Item> list = new ArrayList();
        for (Item item : page) {
            System.out.println(item);
            list.add(item);
        }
        return list;
    }

    public List<Item> queryByBuilderBoolean(String row ,String val) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        queryBuilder.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery(row, val))
//                .must(QueryBuilders.matchQuery("brand","华为"))
        );

        Page<Item> page = itemRepository.search(queryBuilder.build());
        long totalElements = page.getTotalElements();
        logger.info("获取的总条数："+totalElements);

        List<Item> list = new ArrayList();
        for (Item item : page) {
            System.out.println(item);
            list.add(item);
        }
        return list;
    }



    public List<Item> queryByBuilderFuzzyQuery(String row ,String val) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        queryBuilder.withQuery(QueryBuilders.fuzzyQuery(row,val));
        Page<Item> page = itemRepository.search(queryBuilder.build());
        long totalElements = page.getTotalElements();
        logger.info("获取的总条数："+totalElements);

        List<Item> list = new ArrayList();
        for (Item item : page) {
            System.out.println(item);
            list.add(item);
        }
        return list;
    }


    public QueryResult<Item> findByPage(Integer num, Integer size, String title) {

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        queryBuilder.withQuery(QueryBuilders.matchQuery("category",title));

        queryBuilder.withPageable(PageRequest.of(num, size));

        Page<Item> page = itemRepository.search(queryBuilder.build());
        QueryResult<Item>  result= new QueryResult<>();
        result.setCode(200);
        result.setMessage("查询成功！");
        result.setSuccess(true);
        result.setTotal(page.getTotalElements());
        result.setTotalPage(page.getTotalPages());
        result.setList(page.getContent());
        return result;
    }

    public QueryResult<Item> findByPageOrderId(Integer num, Integer size, String category) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        queryBuilder.withQuery(QueryBuilders.matchQuery("category",category))
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC));

        queryBuilder.withPageable(PageRequest.of(num, size));
        Page<Item> page = itemRepository.search(queryBuilder.build());
        QueryResult<Item>  result= new QueryResult<>();
        result.setCode(200);
        result.setMessage("查询成功！");
        result.setSuccess(true);
        result.setTotal(page.getTotalElements());
        result.setTotalPage(page.getTotalPages());
        result.setList(page.getContent());
        return result;
    }

    public Map<String, Integer> findByBucket() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        queryBuilder.addAggregation(AggregationBuilders.terms("brandsName").field("brand"));

        Page<Item> page = itemRepository.search(queryBuilder.build());

        AggregatedPage<Item> aggregatedPage = (AggregatedPage<Item>) page;

        Aggregation aggregation = aggregatedPage.getAggregation("brandsName");

        StringTerms st = (StringTerms) aggregation;

        List<StringTerms.Bucket> buckets = st.getBuckets();

        Map<String, Integer> map = new HashMap();


        for (StringTerms.Bucket sb : buckets) {

            System.out.println(sb.getKeyAsString() +";"+(int)sb.getDocCount());
            map.put(sb.getKeyAsString() ,(int)sb.getDocCount());

        }
        return map;

    }

    public List<AvgResult> findByBucketAvg() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.addAggregation(AggregationBuilders.terms("brandKey").field("brand")
        .subAggregation(AggregationBuilders.avg("avgPrice").field("price")));
        AggregatedPage<Item> aggregatedPage = (AggregatedPage<Item>) itemRepository.search(queryBuilder.build());
        StringTerms stringTerms = (StringTerms) aggregatedPage.getAggregation("brandKey");

        List<StringTerms.Bucket> buckets = stringTerms.getBuckets();
        List<AvgResult> avgList = new ArrayList<>();
        for (StringTerms.Bucket bucket : buckets) {
            InternalAvg avgPrice = (InternalAvg) bucket.getAggregations().asMap().get("avgPrice");
            System.out.println(bucket.getKeyAsString()+":"+bucket.getDocCount()+":"+avgPrice.getValue());
            avgList.add(new AvgResult(bucket.getKeyAsString(), bucket.getDocCount(), avgPrice.getValue()));
        }
        return avgList;
    }
}
