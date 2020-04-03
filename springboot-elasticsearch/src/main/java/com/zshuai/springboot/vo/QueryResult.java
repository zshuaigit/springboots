package com.zshuai.springboot.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/4/3 2:46 PM
 * @Version 1.0
 **/
@Data
public class QueryResult<T> {
    //操作是否成功,true为成功，false操作失败
    private boolean success;
    //操作代码
    private int code;
    //提示信息
    private String message;
    //数据列表
    private List<T> list;
    //数据总数
    private long total;

    //数据总页数
    private long totalPage;
}