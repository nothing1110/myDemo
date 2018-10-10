package com.example.demo.entity;

/**
 * @Author: zhuangf
 * @Date: 2018/10/10
 */
public class VIPUser extends User {
    public VIPUser(String name, String mobile, Address address) {
        super(name, mobile, address);
    }
    //需要在父类建立空的构造方法，否则需要重写父类的构造方法
}
