package com.example.demo.execute;

import com.example.demo.entity.Account;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8 Stream 示例
 *
 * @Author: zhuangf
 * @Date: 2018/10/12
 */
public class StreamDemo {
    public static void main(String[] args) {
//        streamSimple();//Stream简单示例
//        createStream();//创建Stream
//        streamIntermediate();//Stream常用中间操作
        streamTerminal();//Stream常用聚合函数
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

    private static void streamIntermediate() {
        List<String> strList = Arrays.asList(new String[]{"abc","a","bc","abcd"});
        List<Integer> numList = Arrays.asList(new Integer[]{1,null,3,5,5,null,10});
        //======map把一种类型的流转换为另外一种类型的流
        System.out.println("小写转为大写:"+strList.stream().map(x -> x.toUpperCase()).collect(Collectors.toList()));
        System.out.println("求平方:"+numList.stream().map(x -> {
            if(x!=null) return x * x;
                return 0;
            }).collect(Collectors.toList()));

        //======flatMap,流扁平化处理，每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中
        Stream<List<Integer>> intListStream = Stream.of(
                Arrays.asList(new Integer[]{1,2,3,4}),
                Arrays.asList(new Integer[]{5,6,7,8}),
                Arrays.asList(new Integer[]{9,10,11,12}));
//        System.out.println("多数组流:"+intListStream.collect(Collectors.toList()));//collect为结束操作，一个流只能执行一次
        System.out.println("多数组形成一个流"+intListStream.flatMap(list -> list.stream()).collect(Collectors.toList()));
        System.out.println("字符串数组拆分去重:"+
                Arrays.stream(new String[]{"Hello","World"})
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList())
        );//["Hello","World"] --> [H, e, l, o, W, r, d]

        //=======sorted方法，排序，可以传入自定义排序接口Comparator
        //自动排序
        System.out.println("自动排序:"+strList.stream().sorted().collect(Collectors.toList()));
        //按照长度进行排序
        System.out.println("按长度排序:"+strList.stream().sorted((x,y) -> {
            if(x.length()>y.length()) {
                return 1;
            } else if (x.length()<y.length()) {
                return -1;
            }
            return 0;
        }).collect(Collectors.toList()));

        //倒序(利用Comparator)
        System.out.println("自动倒序:"+strList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
        System.out.println("按长度倒序:"+
                strList.stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList()));

        //======skip方法,跳过前n个数据
        System.out.println("跳过一个数据:"+Stream.iterate(1,x->x+2).skip(1).limit(5).collect(Collectors.toList()));

        //======concat方法，两个stream合并成一个stream
        System.out.println("两stream合并:"+Stream.concat(strList.stream(),numList.stream()).collect(Collectors.toList()));

    }

    private static void streamTerminal() {
        String[] strArr = new String[]{"b","ab","abc","abcd","abcde","b"};

        //max、min最大最小值
        Stream.of(strArr).max(Comparator.comparing(String::length)).ifPresent(System.out::println);
        Stream.of(strArr).min(Comparator.comparing(String::length)).ifPresent(System.out::println);

        //count求数量
        System.out.println("数量："+Stream.of(strArr).count());

        //findFirst 查找第一个满足条件的
        System.out.println("首先满足要求："+Stream.of(strArr).filter(x -> x.length()>5).findFirst().orElse("nothing"));

        //findAny 找到任何一个满足条件的；对并行流十分有效
        //只要在任何片段发现了第一个匹配元素就会结束整个运算
        System.out.println("任何一个满足要求:"+Stream.of(strArr).parallel().filter(x->x.length()>2).findAny().orElse("nothjing"));

        //anyMatch 是否含有匹配条件的元素
        System.out.println(Stream.of(strArr).anyMatch(x->x.startsWith("c")));


    }

}
