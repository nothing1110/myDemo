package com.example.demo.entity;

import com.example.demo.service.PredicateDemo;

import java.util.List;
import java.util.function.Function;

/**
 * @Description: 账户相关信息操作
 * @Author: zhuangf
 * @Date: 2018/9/28
 */
public class AccountOpt {

    public Boolean isVIP(Integer num, PredicateDemo<Integer> predicate) {
        predicate.testDefault();
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
