package com.zshuai.springboot.repository;

import com.zshuai.springboot.po.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/4/3 10:59 AM
 * @Version 1.0
 **/
public interface ItemRepository  extends ElasticsearchRepository<Item, Long> {

    public List<Item> findByTitle(String title);

    public List<Item> findByPriceBetween(Double minPrice,Double maxPrice);

}
