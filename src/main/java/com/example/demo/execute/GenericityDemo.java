package com.example.demo.execute;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.entity.VIPUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型用例
 * @Author: zhuangf
 * @Date: 2018/10/10
 */
public class GenericityDemo {


    public void outList(List<? extends User> userList) {//? 代替具体的实参
        System.out.println(userList);
    }

//    public void getList(List<T> fooList) {//报错
//    }

    public static void main(String[] args) {
        demoMethod1();
        demoMethod2();
    }

    /**
     * 泛型继承问题
     * @Author: zhuangf
     * @Date: 2018/10/10
    */
    private static void demoMethod2() {
        //        List<User> fooList = new ArrayList<VIPUser>();//报错，在泛型使用中，User与VIPUser他们之间唯一的关系就是Object的子类
        //<? extends T>：上界通配符 只能取数据而不能写入数据
        List<VIPUser> vipList = new ArrayList<VIPUser>();
        vipList.add(new VIPUser("a","123",new Address("中国","浙江","杭州")));
        List<? extends User> userList = vipList;
//        userList.add(new VIPUser("a","123",new Address("中国","浙江","杭州")));//报错，不可写入数据
//        userList.add(new User("a","123",new Address("中国","浙江","杭州")));
        System.out.println(userList);
        //<? super T> 下界通配符 只能写入数据 取数据只能赋值给Object
//        List<? super VIPUser> userList2 = userList;//报错
        List<? super VIPUser> userList2 = vipList;
        userList2.add(new VIPUser("b","564",new Address("中国","浙江","舟山")));
        System.out.println(userList2);
    }

    /**
     * 泛型T,?以及与Object之间的区别
     * @Author: zhuangf
     * @Date: 2018/10/10
     */
    private static void demoMethod1() {
        Foo<Integer> strFoo = new Foo<Integer>();
//        strFoo.setData("ffda");//类型不匹配，报错
        strFoo.setData(3278);

        //无限制通配符 <?>;表示申明的时候还不确定类型
//       Foo<?> Foo1 = new Foo<?>();//报错,?不能复制给T
        Foo<?> Foo1 = new Foo<Object>();
        Foo1 = new Foo<Integer>();
        Foo1 = new Foo<String>();

//        Foo<Object> Foo2 = new Foo<Object>();
//        Foo2 =  new Foo<Integer>();//报错，申明Object类型不能赋值其它类型

        List<Foo<?>> fooList = new ArrayList<Foo<?>>();
        fooList.add(new Foo<Integer>(123));
        fooList.add(new Foo<String>("fjkdjk"));
        System.out.println(fooList);
    }

    /**
     * T 为形参，具体命名可以任意指定
     * */
    private static class Foo<T> {
        private T data;

//        private static T data2;// 泛型不能使用在静态属性上

        public Foo() {
        }

        public Foo(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "data=" + data +
                    '}';
        }
    }

}
