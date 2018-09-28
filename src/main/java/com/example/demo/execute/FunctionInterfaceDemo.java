package com.example.demo.execute;

import com.example.demo.entity.AccountOpt;

import java.util.Arrays;
import java.util.function.*;

/**
 * @Description: FunctionInterface示例
 * @Author: zhuangf
 * @Date: 2018/9/28
 */
public class FunctionInterfaceDemo {
    public static void main(String[] args) {
//        predicateDemo();
//        consumerDemo();
//        supplierDemo();
//        functionDemo();
        myOperat();
    }

    private static void myOperat() {
        AccountOpt opt = new AccountOpt();
        int resultNum = opt.MathOperation(Arrays.asList(new Integer[]{1,5,8,9,15,9,7,9}), x -> {
            Integer total = 1;
           for(Integer i : x) {
               total = total * i;
           }
           return total;
        });

        System.out.println(resultNum);
    }

    /**
     * 函数型接口示例
     * 接收一个T类型的参数，返回一个R类型的结果
     * @Author: zhuangf
     * @Date: 2018/9/28 
    */ 
    private static void functionDemo() {
        Function<String,Integer> convert = x -> Integer.valueOf(x);
        System.out.println(convert.apply("534"));

        BiFunction<Integer,Integer,String> addFunc = (x, y) -> { return "合计:"+x + y; };
        System.out.println(addFunc.apply(6,9));
    }

    /**
     * 工厂方法
     * 不接受参数，返回一个T类型的结果
     * @Author: zhuangf
     * @Date: 2018/9/28
    */
    private static void supplierDemo() {
        Supplier<String> sendPrompt = () -> "请注意非法操作!!!";
        System.out.println(sendPrompt.get());
    }

    /**
    * 消费者接口:
    * Consumer<T>
    * 接收一个T类型的参数，不返回值
    * BiConsumer<T , U>
    * 接收T类型和U类型的两个参数，不返回值
    * @Author: zhuangf
    * @Date: 2018/9/28 
   */ 
    private static void consumerDemo() {
        Consumer<String> sendMsg = x -> System.out.println(x);
        sendMsg.accept("Hello World!!");

        BiConsumer<String,Integer> sendMsgs = (x, y) -> System.out.println(x+"拥有"+y+"资产");
        sendMsgs.accept("Tom",50000);
    }

    /**
    *  断言型接口:
     * Predicate<T>
     * 接收一个T类型的参数，返回一个boolean类型的结果
     * BiPredicate<T, U>
     * 接收T类型和U类型的两个参数，返回一个boolean类型的结果
    * @Author: zhuangf
    * @Date: 2018/9/28 
    */ 
    private static void predicateDemo() {
        Predicate<Integer> isVIP = x -> x>=500;
        System.out.println(isVIP.test(60));

        BiPredicate<Integer,Integer> isFlag = (x,y) -> x>y;
        System.out.println(isFlag.test(100,90));
    }


}
