package com.example.demo.entity;

import java.util.Date;

public class Account {
    private String user;
    private Date time;
    private int sum ;

    public Account(String user, Date time, int sum) {
        this.user = user;
        this.time = time;
        this.sum = sum;
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

    @Override
    public String toString() {
        return "Account{" +
                "user='" + user + '\'' +
                ", time=" + time +
                ", sum=" + sum +
                '}';
    }
}
