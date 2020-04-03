package com.zshuai.springboot.controller;

import com.zshuai.springboot.po.Item;
import com.zshuai.springboot.service.ItemService;
import com.zshuai.springboot.vo.AvgResult;
import com.zshuai.springboot.vo.QueryResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zshuai
 *
 * @Date :2020/4/3 11:01 AM
 * @Version 1.0
 **/
@Api(value = "es相关操作控制类", description = "涉及索引映射创建、数据增删改查、过滤排序等操作，由于模仿试验，" +
        "不涉及前端，故全部为get请求")
@RestController
@RequestMapping("es")
public class ItemController {

    @Autowired
    ItemService itemService;


    @ApiOperation(httpMethod = "GET", value = "创建索引及映射，无需传递参数")
    @GetMapping("create")
    public String createIndex() {
        return itemService.createIndex(Item.class);
    }

    @ApiOperation(httpMethod = "GET", value = "删除索引及映射，无需传递参数")
    @GetMapping("deleteIndex")
    public String deleteIndex() {
        return itemService.deleteIndex(Item.class);
    }

    @ApiOperation(httpMethod = "GET", value = "添加单个数据，无需传递参数，实际生产环境应为post或put")
    @GetMapping("addOne")
    public String addOne() {
        Item item = new Item(1L, "苹果11", "手机",
                "iPhone", 7999.00, System.currentTimeMillis(), "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585898432816&di=b46d0812a3f9eb1eb3a38851f719fd9d&imgtype=0&src=http%3A%2F%2Fwnews.zhuotingwl.com%2Fpic%2F201909%2F11%2Fb35482d3.jpg");
        return itemService.addOne(item);
    }

    @ApiOperation(httpMethod = "GET", value = "list形式添加多个数据，无需传递参数")
    @GetMapping("addList")
    public String addList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "苹果9", " 手机", "iPhone", 2999.00, System.currentTimeMillis(), "http://image.baidu.com/13123.jpg"));
        list.add(new Item(3L, "华为P40", " 手机", "华为", 4499.00, System.currentTimeMillis(), "http://image.baidu.com/13123.jpg"));
        list.add(new Item(4L, "小米手机7facebook", "手机", "小米", 3299.00, System.currentTimeMillis(),"http://image.baidu.com/13123.jpg"));
        list.add(new Item(5L, "坚果facebook手机R1", "手机", "锤子", 3699.00, System.currentTimeMillis(),"http://image.baidu.com/13123.jpg"));
        list.add(new Item(6L, "华为META10facebook", "手机", "华为", 4499.00, System.currentTimeMillis(),"http://image.baidu.com/13123.jpg"));
        list.add(new Item(7L, "facebook小米Mix2S", "手机", "小米", 4299.00,System.currentTimeMillis(), "http://image.baidu.com/13123.jpg"));
        list.add(new Item(8L, "荣耀V10facebook", "手机", "华为", 2799.00, System.currentTimeMillis(),"http://image.baidu.com/13123.jpg"));

        list.add(new Item(9L, "小米手机7facebook", "手机", "小米", 3299.00,System.currentTimeMillis(), "http://image.baidu.com/13123.jpg"));
        list.add(new Item(10L, "坚果facebook手机R1", "手机", "锤子", 3699.00,System.currentTimeMillis(), "http://image.baidu.com/13123.jpg"));
        list.add(new Item(11L, "华为META10facebook", "手机", "华为", 4499.00, System.currentTimeMillis(),"http://image.baidu.com/13123.jpg"));
        list.add(new Item(12L, "facebook小米Mix2S", "手机", "小米", 4299.00, System.currentTimeMillis(),"http://image.baidu.com/13123.jpg"));
        list.add(new Item(13L, "荣耀V10facebook", "手机", "华为", 2799.00, System.currentTimeMillis(),"http://image.baidu.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        return itemService.saveAll(list);
    }

    @ApiOperation(httpMethod = "GET", value = "查询所有数据，无过滤及排序等操作")
    @GetMapping("findAll")
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @ApiOperation(httpMethod = "GET", value = "查询所有数据，按照价格降序")
    @GetMapping("priceDesc")
    public List<Item> priceDesc() {
        return itemService.findAllByPriceDesc();
    }

    @ApiOperation(httpMethod = "GET", value = "使用repository支持的自定义查询方法,模拟使用title查询，方法自带支持模糊查询")
    @GetMapping("findByTitle/{title}")
    public List<Item> findByTitle(@PathVariable("title") String title) {
        return itemService.findByTitle(title);
    }


    @ApiOperation(httpMethod = "GET", value = "区间查询，根据指定的条件进行区间查询，如价格区间，时间区间")
    @GetMapping("finByPriceBetween/{start}/{stop}")
    public List<Item> finByPriceBetween(@PathVariable("start") Double start,
                                        @PathVariable("stop") Double stop) {

        return itemService.finByPriceBetween(start, stop);
    }



    @ApiOperation(httpMethod = "GET", value = "采用底层的QueryBuilder进行词条匹配查询 matchQuery：词条匹配，先分词然后在调用termQuery进行匹配")
    @GetMapping("queryBuilderMatch/{row}/{val}")
    public List<Item> queryByBuilderMatch(@PathVariable("row") String row,@PathVariable("val") String val) {

        return itemService.queryByBuilderMatch(row, val);

    }

    @ApiOperation(httpMethod = "GET", value = "采用底层的QueryBuilder进行词条匹配查询 TermQuery：词条匹配，不分词")
    @GetMapping("queryBuilderTerm/{row}/{val}")
    public List<Item> queryByBuilderTerm(@PathVariable("row") String row,@PathVariable("val") String val) {

        return itemService.queryByBuilderTerm(row, val);

    }

    @ApiOperation(httpMethod = "GET", value = "采用底层的QueryBuilder进行词条匹配查询 booleanQuery：布尔查询" +
            "比如分类必须为手机，品牌必须为华为")
    @GetMapping("queryBuilderBoolean/{row}/{val}")
    public List<Item> queryByBuilderBoolean(@PathVariable("row") String row,@PathVariable("val") String val) {

        return itemService.queryByBuilderBoolean(row, val);

    }

    @ApiOperation(httpMethod = "GET", value = "采用底层的QueryBuilder进行词条匹配查询 fuzzyQuery：模糊匹配")
    @GetMapping("queryBuilderFuzzyQuery/{row}/{val}")
    public List<Item> queryByBuilderFuzzyQuery(@PathVariable("row") String row,@PathVariable("val") String val) {

        return itemService.queryByBuilderFuzzyQuery(row, val);

    }

    @ApiOperation(httpMethod = "GET", value = "es的分页查询,传入参数为页码，每页条数，查询的title，传入参数不能为空，null对URI毫无意义")
    @GetMapping("findPage/{num}/{size}/{title}")
    public QueryResult<Item> findByPage(@PathVariable("num") Integer num, @PathVariable("size") Integer size,
                                  @PathVariable("title") String title ) {
        return itemService.findByPage(num, size, title);
    }

    @ApiOperation(httpMethod = "GET", value = "es的分页查询,传入参数为页码，每页条数，查询的category，按照id升序排列。传入参数不能为空，null对URI毫无意义")
    @GetMapping("findPageOrderId/{num}/{size}/{category}")
    public QueryResult<Item> findByPageOederId(@PathVariable("num") Integer num, @PathVariable("size") Integer size,
                                        @PathVariable("category") String category ) {
        return itemService.findByPageOrderId(num, size, category);
    }

    @ApiOperation(httpMethod = "GET", value = "使用bucket分组查询，根据品牌分组，获取每个品牌的对应数据总和")
    @GetMapping("findByBucket")
    public Map<String , Integer> findByBucket(){
        return itemService.findByBucket();
    }


    @ApiOperation(httpMethod = "GET", value = "使用bucket分组查询，根据品牌分组，获取每个品牌的对应数据总和及均价")
    @GetMapping("findByBucketAvg")
    public List<AvgResult> findByBucketAvg(){
        return itemService.findByBucketAvg();
    }


}
