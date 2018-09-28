package com.example.demo.entity;

/**
 * @Description: 账户相关信息操作
 * @Author: zhuangf
 * @Date: 2018/9/28
 */
public class AccountOpt {
    /**
     * 实现断言型接口: Predicate<T>
     * 接收一个T类型的参数，返回一个boolean类型的结果
     */
    @FunctionalInterface
    public interface PredicateDemo<T> {
        Boolean isFlag(T t);
    }

    public Boolean isVIP(Integer num,PredicateDemo<Integer> predicate) {
        return predicate.isFlag(num);
    }

}
