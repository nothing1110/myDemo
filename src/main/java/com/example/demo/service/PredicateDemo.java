package com.example.demo.service;

/**
 * 实现断言型接口: Predicate<T>
 * 实现FunctionalInterface原则
 * 1.有且只有一个抽象方法
 * 2.接口只有一个抽象方法，则默认是函数式接口。
 * @Author: zhuangf
 * @Date: 2018/9/29
 */
@FunctionalInterface
public interface PredicateDemo<T> {
    Boolean isFlag(T t);

//    Integer addNum();//报错

    /**
     * 静态方法，不是抽象方法
     * java8开始接口里可以有静态方式，用static修饰，
     * 但是接口里的静态方法的修饰符只能是public，且默认是public。
     */
    static void testStatic() {
        System.out.println("接口里的静态方法！");
    }

    /**
     *  java8开始还可以写非静态方法，但是必须用default修饰，且只能是public，默认也是public。
     */
    public default void testDefault() {
        System.out.println("这个是接口里的default方法");
    }

    public static void main(String[] args) {
        testStatic();
    }
}
