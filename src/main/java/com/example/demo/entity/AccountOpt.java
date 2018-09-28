package com.example.demo.entity;

import java.util.List;
import java.util.function.Function;

/**
 * @Description: 账户相关信息操作
 * @Author: zhuangf
 * @Date: 2018/9/28
 */
public class AccountOpt {
    /**
     * 实现断言型接口: Predicate<T>
     * 实现FunctionalInterface原则
     * 1、有且只有一个抽象方法
     */
    @FunctionalInterface
    public interface PredicateDemo<T> {
        Boolean isFlag(T t);
    }

    public Boolean isVIP(Integer num,PredicateDemo<Integer> predicate) {
        return predicate.isFlag(num);
    }

    public int MathOperation(List<Integer> numList,Function<List<Integer>,Integer> opt) {
        int resultNum = 0;
        if(numList!=null && !numList.isEmpty()) {
            if(opt != null) {
                resultNum = opt.apply(numList);
            }
        }
        return resultNum;
    }
}
