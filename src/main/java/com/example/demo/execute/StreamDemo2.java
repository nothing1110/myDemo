package com.example.demo.execute;

import com.example.demo.entity.Account;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8 Stream 高级应用示例
 *
 * @Author: zhuangf
 * @Date: 2018/10/12
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        String[] strArr = new String[]{"b","ab","abc","abcd","abcde","b"};
        Integer[] intArr = new Integer[]{1,3,56,78,90};
        List<Account> accountList = new ArrayList<Account>();
        accountList.add(new Account("a",new Date(),100));
        accountList.add(new Account("c",new Date(),100));
        accountList.add(new Account("b",new Date(),980));
        //生成List
        List<String> arrList = Stream.of(strArr).collect(Collectors.toList());
        System.out.println("生成List:"+arrList);

        //生成Set(无序不重复)
        Set<String> arrSet = Stream.of(strArr).collect(Collectors.toSet());
        System.out.println("生成Set:"+arrSet);

        //生成数组
//        Account[] accountArray = Stream.of(accountList).toArray(x->new Account[x]);//报错，List不能转变
//        String[] strArray2 = Stream.of(intArr).toArray(String[]::new);//报错，类型需相同
//        String[] strArray2 = Stream.of(intArr).toArray(x-> {
//            System.out.println("数组的量"+x);
//            return new String[x];
//        });//效果同下
        String[] strArray2 = Stream.of(strArr).toArray(String[]::new);

        for (String str : strArray2) {
            System.out.println(str);
        }

        //生成Map
//        Map<String,Account> arrMap = Stream.of(accountList).collect(Collectors.toMap(Account::getUser, Function.identity()));
        Map<String,Integer> arrMap = Stream.of(strArr).distinct().collect(Collectors.toMap(Function.identity(),String::length));
        arrMap.forEach((x,y)-> System.out.println(x+"->"+y));
    }
}
