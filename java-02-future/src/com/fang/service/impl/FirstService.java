package com.fang.service.impl;

import com.fang.service.IService;

/**
 * 第一个服务
 *
 * @author fwj
 * @date 2018-12-20 16:49
 **/
public class FirstService implements IService<String> {
    @Override
    public String findById(Integer id) {
        return "First has " + id;
    }
}
