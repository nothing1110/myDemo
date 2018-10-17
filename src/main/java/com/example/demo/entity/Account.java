package com.example.demo.entity;

import java.util.Date;

/**
 * @Description: 账户相关信息操作
 * @Author: zhuangf
 * @Date: 2018/9/28
 */
public class Account {
    private String user;
    private Date time;
    private int sum ;
    private int type;//账户类型:1,2,3;一类，二类，三类用户

    public Account() {
    }

    public Account(String user, Date time, int sum, int type) {
        this.user = user;
        this.time = time;
        this.sum = sum;
        this.type = type;
    }

    public Account(String user, Date time, int sum) {
        this.user = user;
        this.time = time;
        this.sum = sum;
        this.type = 1;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "user='" + user + '\'' +
                ", time=" + time +
                ", sum=" + sum +
                ", type='" + type + '\'' +
                '}';
    }
}
