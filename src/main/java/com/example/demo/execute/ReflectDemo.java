package com.example.demo.execute;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射的示例
 *
 * @Author: zhuangf
 * @Date: 2018/10/10
 */
public class ReflectDemo {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<String>();
        System.out.println(((ParameterizedType)strList.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * 获得Class对象的方法
     */
    private static void getClassDemo() {

    }
}
