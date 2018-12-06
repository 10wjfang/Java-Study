package com.fang.stream;

/**
 * 实体类
 *
 * @author fwj
 * @date 2018-12-06 13:54
 **/
public class Dish {
    private String name;
    private Integer size;

    public Dish(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
