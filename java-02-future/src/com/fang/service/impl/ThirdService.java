package com.fang.service.impl;

import com.fang.service.IService;

import java.util.Arrays;
import java.util.List;

/**
 * 返回列表服务
 *
 * @author fwj
 * @date 2018-12-20 17:51
 **/
public class ThirdService implements IService<String> {
    @Override
    public String findById(Integer id) {
        return null;
    }

    public List<String> findListById(Integer id) {
        return Arrays.asList("11", "22");
    }
}
