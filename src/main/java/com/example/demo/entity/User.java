package com.example.demo.entity;

import java.util.Date;
import java.util.Optional;

/**
 * @Description: 用户信息
 * @Author: zhuangf
 * @Date: 2018/9/28
 */
public class User {
    private String name;
    private String mobile;
    private Address address;

    public User(String name, String mobile, Address address) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    //返回Optional类型
    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address=" + address +
                '}';
    }
}
