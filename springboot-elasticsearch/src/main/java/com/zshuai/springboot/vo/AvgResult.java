package com.zshuai.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Created by zshuai
 *
 * @Date :2020/4/3 4:05 PM
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
public class AvgResult  {

    //品牌
    private String brand;
    //总数
    private long total;

    //均价
    private Double avgPrice;
}
