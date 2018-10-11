package com.example.demo.entity;

/**
 * 水果类
 *
 * @Author: zhuangf
 * @Date: 2018/10/11
 */
public class Fruit {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                '}';
    }
}
