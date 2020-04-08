package com.zshuai.springboot.baseRepository;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by zshuai
 *
 * @Date :2020/4/8 10:57 AM
 * @Version 1.0
 **/

public interface IBaseDao<T>  extends Mapper<T>, MySqlMapper<T> {
}
