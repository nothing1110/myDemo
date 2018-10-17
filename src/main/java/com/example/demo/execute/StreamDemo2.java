package com.example.demo.execute;

import com.example.demo.entity.Account;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8 Stream 高级应用示例
 *
 * @Author: zhuangf
 * @Date: 2018/10/12
 */
public class StreamDemo2 {
    /**
     * 根据java Stream 性能测试，结果如下
     * 1.对于简单操作，比如最简单的遍历，Stream串行API性能明显差于显示迭代，但并行的Stream API能够发挥多核特性。
     * 2.对于复杂操作，Stream串行API性能可以和手动实现的效果匹敌，在并行执行时Stream API效果远超手动实现。
     *
     * 处于性能考虑，使用建议
     * 1. 对于简单操作推荐使用外部迭代手动实现；
     * 2. 对于复杂操作，推荐使用Stream API；
     * 3. 在多核情况下，推荐使用并行Stream API来发挥多核优势；
     * 4.单核情况下不建议使用并行Stream API。
     * @param args
     */
    public static void main(String[] args) {
//        reduceDemo();
//        collectSimple();//collect简单示例
          collectDemo();//collect复杂示例
    }

    /**
     * collect()复杂操作
     */
    private static void collectDemo() {
        List<Account> accountList = new ArrayList<Account>();
        accountList.add(new Account("a",new Date(),100,1));
        accountList.add(new Account("c",new Date(),77,2));
        accountList.add(new Account("b",new Date(),980,3));
        accountList.add(new Account("d",new Date(),350));

        /*生成Map(1.toMap();2.partitioningBy()3.groupingBy())*/
        /**
         * 1.Collectors.toMap()生成收集器。用户需要指定如何生成Map的key和value。
         */
        //Function.identity()返回一个输出跟输入一样的Lambda表达式对象,等价于形如t -> t
        Map<String,Integer> arrMap = Stream.of(new String[]{"b","ab","abc","abcd"}).distinct()
                .collect(Collectors.toMap(Function.identity(),String::length));
//        arrMap.forEach((x,y)-> System.out.println(x+"->"+y));

        Map<String,Account> accountMap = accountList.stream().collect(Collectors.toMap(Account::getUser,Function.identity()));
//        accountMap.forEach((x,y)-> System.out.println(x+"-->"+y));

        /**
         * 2.Collectors.partitioningBy()生成的收集器，对元素进行二值逻辑（满足条件，或不满足）操作时用到。
         */
        //账户金额是否大于100
        Map<Boolean,List<Account>> accountMap2 = accountList.stream().collect(Collectors.partitioningBy(a -> a.getSum()>100));
//        accountMap2.forEach((x,y)-> System.out.println(x+"-->"+y));

        /**
         * 3.Collectors.groupingBy()生成的收集器，对元素做group操作时用到。
         */
        //根据类型分类

        Map<Integer,List<Account>> accountMap3 = accountList.stream().collect(Collectors.groupingBy(Account::getType));
        accountMap3.forEach((x,y)-> System.out.println(x+"-->"+y));

        /*
        * groupingBy()允许我们对元素分组之后再执行某种运算，比如求和、计数、平均值、类型转换等
        * 先将元素分组的收集器叫做上游收集器，之后执行其他运算的收集器叫做下游收集器(downstream Collector)
        */
        //利用下游收集器统计每个类型的人数
        Map<Integer, Long> accountMap4 = accountList.stream().collect(Collectors.groupingBy(Account::getType,Collectors.counting()));
        accountMap4.forEach((x,y)-> System.out.println("类型："+x+"-->数量："+y));

        //下游收集器包含更下游的收集器，获得每组类型下的成员名称
        Map<Integer,List<String>> accountMap5 = accountList.stream().collect(
                Collectors.groupingBy(Account::getType,
                        Collectors.mapping(Account::getUser,
                                Collectors.toList())));
        accountMap5.forEach((x,y)-> System.out.println("类型："+x+"-->成员名称："+y));

        /*Collectors.joining()拼接字符串*/
        System.out.println("拼接字符串"+Stream.of("I","am","running").collect(Collectors.joining(",","{","}")));
    }

    /**
     * collect()操作
     *从Stream生成一个集合或者Map等复杂的对象
     */
    private static void collectSimple() {
        String[] strArr = new String[]{"b","ab","abc","abcd","abcde"};
        Integer[] intArr = new Integer[]{1,3,56,78,90};

        //======生成List
        List<String> arrList = Stream.of(strArr).collect(ArrayList::new,//新建ArrayList
                ArrayList::add,//对元素操作
                ArrayList::addAll);//多部分连接,执行并行的时候用到
//        List<String> arrList = Stream.of(strArr).collect(Collectors.toList());//Collectors方法
        System.out.println("生成List:"+arrList);

        //使用toCollection()指定规约容器的类型
        ArrayList<String> arrList2 = Stream.of(strArr).collect(Collectors.toCollection(ArrayList::new));

        //=====生成Set(无序不重复)
        Set<String> arrSet = Stream.of(strArr).collect(Collectors.toSet());
        System.out.println("生成Set:"+arrSet);

        //======生成数组
//        Account[] accountArray = Stream.of(accountList).toArray(x->new Account[x]);//报错，List不能转变
//        String[] strArray2 = Stream.of(intArr).toArray(String[]::new);//报错，类型需相同

//        String[] strArray2 = Stream.of(intArr).toArray(x-> {
//            System.out.println("数组的量"+x);
//            return new String[x];
//        });//效果同下
        String[] strArray2 = Stream.of(strArr).toArray(String[]::new);
//        for (String str : strArray2) {
//            System.out.println(str);
//        }

    }

    /**
     * reduce操作
     * 最常用的场景就是从一堆值中生成一个值
     * sum()、max()、min()、count()本质上也是reduce操作
     */
    private static void reduceDemo() {
        String[] strArr = new String[]{"b","ab","abc","abcd","abcde","c"};
        //找出最短的一个单词
        System.out.println("reduce方法："+Stream.of(strArr).reduce((s1,s2) -> s1.length() < s2.length() ? s1 : s2).orElse("nothing"));
        System.out.println("min方法: "+Stream.of(strArr).min(Comparator.comparing(String::length)).orElse("nothing"));

        //求单词长度之和
        Integer total = Stream.of(strArr).reduce(0,//sum初始值,决定reduce返回值的类型
                (sum,str) -> sum+=str.length(),//执行，累加器
                (a,b)->a+b);//拼接器,执行并行的时候用到

        int total2 = Stream.of(strArr).mapToInt(String::length).sum();
        System.out.println("reduce方法："+total);
        System.out.println(total == total2);
    }
}
