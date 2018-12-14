package com.fang;

/**
 * Shop
 *
 * @author fwj
 * @date 2018-12-12 17:25
 **/
public class Shop {
    private String name;

    public Double getPrice() {
        return CompletableFutureTest.calculatePrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
