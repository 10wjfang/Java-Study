package com.fang.service.impl;

import com.fang.service.IService;

/**
 * 第二个服务
 *
 * @author fwj
 * @date 2018-12-20 16:51
 **/
public class SecondService implements IService<Integer> {
    @Override
    public Integer findById(Integer id) {
        return id;
    }
}
