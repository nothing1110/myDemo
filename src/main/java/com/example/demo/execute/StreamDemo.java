package com.example.demo.execute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * java8 Stream 示例
 *
 * @Author: zhuangf
 * @Date: 2018/10/12
 */
public class StreamDemo {
    public static void main(String[] args) {
//        streamSimple();
//        createStream();
    }

    /**
     * Stream创建方法
     * 一.静态工厂方法(of,generator,iterate,empty)
     * 二.Collection接口和数组的默认方法
     */
    private static void createStream() {
        //of方法,有限长度的Stream
        Stream<Integer> intStream = Stream.of(new Integer[] {1,2,3,4});
        Stream<Integer> intStream2 = Stream.of(5,6,7,8,9);

        //generator方法,返回一个无限长度的Stream, limit()为限制条数
        Stream.generate(()-> java.lang.Math.random()).limit(5).forEach(System.out::println);

        //iterate方法,返回一个无限长度的Stream
        Stream.iterate(1,i -> i += 2).limit(5).forEach(System.out::println);

        //在Collection接口中，定义了一个默认方法stream()，用来生成一个Stream。
        String[] StrArray = new String[]{"a","b","c","d","e","f"};
        Arrays.stream(StrArray).forEach(System.out::println);
    }

    /**
     * Stream是元素的集合,类似Iterator
     * Stream源数据可以是无限的
     * Stream本身不存储数据
     * Stream操作会产生结果，但不会修改其来源
     */
    private static void streamSimple() {
        List<Integer> numList = Arrays.asList(new Integer[]{1,null,3,5,5,null,10});
        /*1.创建Stream;2.转换Stream;3.聚合操作，流的终止操作*/
        /*
         * 流的操作包括中间操作(Intermediate)以及终止操作(Terminal)
         * 中间操作可以多个，这些操作都是惰性的，即仅仅调用这类方法，并未流的遍历
         *      [map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 skip、 parallel、 sequential、 unordered]
         * 终止操作一个流只有一个，执行真正的流遍历，并且生成一个结果
         *      [forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、iterator]
         * 所以一个流最终只遍历一遍
         */
        System.out.println("过滤空值的数量:"+numList.stream().filter(num -> num != null).distinct().count());
        /*Stream操作会产生结果，但不会修改其来源*/
        System.out.println("原List:"+numList);
    }
}
