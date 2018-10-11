package com.example.demo.entity;

/**
 * 苹果
 *
 * @Author: zhuangf
 * @Date: 2018/10/11
 */
public class Apple extends Fruit {
    @Override
    public String toString() {
        return "Apple{" +
                "name='" + super.getName() + '\'' +
                '}';
    }
}
