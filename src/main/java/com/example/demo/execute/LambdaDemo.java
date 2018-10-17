package com.example.demo.execute;

import com.example.demo.entity.Account;

import java.util.*;

/**
 * @author: zhuangf
 * @description:java8 Lambda例子
 * @create: 2018-09-28 15:53
 **/
public class LambdaDemo {
    public static void main(String[] args) {
//        testLsitFor();
//        tetsNumListSort();
        testAccountSort();
    }

    public static void testAccountSort() {
        //先按用户降序，再按金额升序
        List<Account> accountList = new ArrayList<Account>();
        accountList.add(new Account("a",new Date(),100));
        accountList.add(new Account("c",new Date(),100));
        accountList.add(new Account("b",new Date(),980));
        accountList.add(new Account("a",new Date(),250));
        accountList.add(new Account("b",new Date(),90));
        /*
         * System.out::println 为方法引用，可替代Lambda表达式
         * 1.引用静态方法 Integer::sum
         * 2.引用某个对象的方法 accountList::add
         * 3.引用某个类的方法 Account::getUser
         * 4.引用构造方法 HashMap::new
         */
        accountList.sort(Comparator.comparing(Account::getUser).reversed().thenComparing(Account::getSum));
        accountList.forEach(System.out::println);

    }

    public static void tetsNumListSort() {
        Integer[] numArray = new Integer[]{1,36,789,545,789,4,6,9,8,50,90,184};
        List<Integer> numList = Arrays.asList(numArray);
        numList.sort((num1,num2) ->
            num1.compareTo(num2)
        );

        numList.forEach(System.out::println);
    }

    public static void testLsitFor() {
        Integer[] numArray = new Integer[]{1,36,789,545,789,4,6,9,8,50,90,184};
        List<Integer> numList = Arrays.asList(numArray);
        Account account = new Account("a",new Date(),0);
        int total = 0;
        //传统的遍历
//        for(int num : numArray) {
//            System.out.println(num);
//        }
        //Lambda遍历
        numList.forEach((num) -> System.out.println(num));
        numList.forEach(System.out::println);

        //Lambda求和
//        numList.forEach((num) -> {total += num;}); //报错，Lambda表达式内只能引用外部为final的变量
        numList.forEach((num) -> {account.setSum(account.getSum()+num);});//通过实体来赋值
        System.out.println("合计为:"+account.getSum());

    }

}
