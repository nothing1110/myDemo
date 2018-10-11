package com.example.demo.execute;

import com.example.demo.entity.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型擦除用例
 * Java的泛型是伪泛型.在编译期间，所有的泛型信息都会被擦除掉。
 * @Author: zhuangf
 * @Date: 2018/10/10
 */
public class GenericityDemo2 {
    /**
     * 1.泛型实参只会在类、字段及方法参数内保存其签名，无法通过反射动态获取泛型实例的具体实参。
     * 2.需要获取泛型实参的情况下，方法有三；(1)通过传递实参类型 (2)明确定义泛型实参类型，通过反射获取签名 (3)通过匿名类捕获相关的泛型实参
     */
    public static void main(String[] args) throws Exception {
//        getGenric();
        //获得不了实参Apply,泛型在字节码中类型会被擦除到上限
        FruitSellers<Apple> fruitSellers = new FruitSellers<Apple>();
        System.out.println(fruitSellers.getClass().getGenericSuperclass());

        //通过内部类来获得泛型实参
        fruitSellers = new FruitSellers<Apple>(){};
        ParameterizedType parameType = (ParameterizedType)fruitSellers.getClass().getGenericSuperclass();
        System.out.println("参数类型:"+parameType);
        System.out.println("泛型实参:"+parameType.getActualTypeArguments()[0]);
    }

    /**
     * 获得泛型变量，方法，类的类型
     * 获得泛型实参的方法:
     *      如果是继承基类而来的泛型，就用 getGenericSuperclass() , 转型为 ParameterizedType 来获得实际类
     *      如果是实现接口而来的泛型，就用 getGenericInterfaces() , 针对其中的元素转型为 ParameterizedType 来获得实际类型
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     */
    private static void getGenric() throws NoSuchFieldException, NoSuchMethodException {

        Class sellersClass = FruitSellers.class;
        //通过反射获得泛型实参的上限类型替代真实的泛型实参
            System.out.println("泛型类Sellers的泛型上限实参：" + sellersClass.getTypeParameters()[0].getBounds()[0].toString());
        //获得通过父类获得泛型子类实参
            //ParameterizedType参数化类型，即泛型,getGenericSuperclass()获得父类带有泛型
            ParameterizedType actualType = (ParameterizedType) AppleSellers.class.getGenericSuperclass();
            //getActualTypeArguments获取参数化类型的数组，泛型可能有多个
            Class c=(Class) actualType.getActualTypeArguments()[0];
            System.out.println("AppleSellers类的泛型实参:"+c);
        //获得泛型变量的类型
            Field AppleListField = sellersClass.getField("AppleList");
            //Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
            Type fieldType = AppleListField.getGenericType();
            actualType = (ParameterizedType) AppleListField.getGenericType();
            System.out.println("获得成员变量AppleList的类型:"+fieldType.toString());
            System.out.println("获得成员变量AppleList的泛型实参:"+actualType.getActualTypeArguments()[0]);
        //获得泛型方法中参数类型
            Method setAppleMethod = sellersClass.getMethod("setAppleList",new Class[]{List.class});
            ParameterizedType paramType = (ParameterizedType)setAppleMethod.getGenericParameterTypes()[0];
            System.out.println("获得方法的参数类型:"+paramType);
            System.out.println("获得方法的泛型的实参:"+paramType.getActualTypeArguments()[0]);
        //获得泛型方法中返回值类型
            Method getAppleMethod = sellersClass.getMethod("getAppleList");
            ParameterizedType returnType = (ParameterizedType)getAppleMethod.getGenericReturnType();
            System.out.println("获得方法的返回值类型:"+returnType);
            System.out.println("获得方法的返回值泛型的实参:"+paramType.getActualTypeArguments()[0]);
    }
}
