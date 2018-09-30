package com.example.demo.execute;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Optional示例
 *
 * @Author: zhuangf
 * @Date: 2018/9/30
 */
public class OptionalDemo {
    public static void main(String[] args) {
        UserOptional();
//        interOptional();
    }

    private static void interOptional() {
        Integer int1 = null;
        Integer int2 = 8;
        //获得Optional对象
//        Optional<Integer> intOptional = Optional.of(int1);//报错,of() 方法会抛出 NullPointerException
        Optional<Integer> intOptional = Optional.ofNullable(int2);//接收空值

        //判断值是否存在
        intOptional.ifPresent(x -> System.out.println("对象有值:"+x));//如果值不为空，则进行消费者处理
        System.out.println(intOptional.isPresent());

       //取回实际对象
//        System.out.println(intOptional.get());//如果实际对象为空，则会报错
        //orElse,如果值存在，返回它，否则返回默认值，单值使用
        System.out.println("orElse:"+Optional.ofNullable(int1).orElse(new Integer(0)));
        //实际对象为非空时，orElse仍会根据方法获得默认值，然后再进行判断。此时适用于orElseGet
        System.out.println("orElse:"+Optional.ofNullable(int2).orElse(createNum()));
        //orElseGet,如果值存在，返回它,否则可根据工厂类返回值
        System.out.println("orElseGet:"+Optional.ofNullable(int2).orElseGet(() -> createNum()));

        //orElseThrow,如果值存在，返回它,否则抛出异常
        System.out.println( Optional.ofNullable(int2).orElseThrow(() -> new IllegalArgumentException()));
    }

    private static Integer createNum() {
        int num = new Random().nextInt(100);
        System.out.println("获得随机值"+num);
        return num;
    }

    private static void UserOptional() {
        User user1 = new User(null,"",null);
        User user2 = new User("zhuang","12598765432",new Address("中国","浙江省","杭州市"));
        //转换值:Map()
        System.out.println(Optional.ofNullable(user1).map(x -> x.getName()).orElse("Unknown"));
        System.out.println(user1.getName());
        //如果属性getter方法返回Optional类型，可用flatMap();
        System.out.println(
                Optional.ofNullable(user2).flatMap(x -> x.getAddress()).orElse(new Address("未知","未知","未知"))
        );

        //过滤值:filter()
        System.out.println(
                Optional.ofNullable(user2).filter(x -> x!=null && x.getMobile()!=null && x.getMobile().length() == 11)
                        .orElseThrow(() -> new IllegalArgumentException("数据格式不合法"))
        );

        //链式方法
        System.out.println(
                "用户国家:" +
                Optional.ofNullable(user2).flatMap(x -> x.getAddress()).map(x -> x.getCountry()).orElse("未知")
        );
    }
}
