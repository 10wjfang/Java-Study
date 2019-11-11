package com.fang.service;

/**
 * 接口
 *
 * @author fwj
 * @date 2018-12-20 16:49
 **/
public interface IService<T> {
    T findById(Integer id);
}
