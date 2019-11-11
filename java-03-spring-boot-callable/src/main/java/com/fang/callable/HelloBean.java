package com.fang.callable;

/**
 * 测试实体类
 *
 * @author fwj
 * @date 2018-12-14 14:22
 **/
public class HelloBean {
    private String name;
    private Integer age;

    public HelloBean(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
