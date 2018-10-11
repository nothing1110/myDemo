package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 水果售卖员
 *
 * @Author: zhuangf
 * @Date: 2018/10/11
 */
public class FruitSellers<T extends Fruit> {
    //售卖的苹果品种
    public List<Apple> AppleList ;

    public Class<?> getFruitType(List<T> fruitList){
        return null;
    }

    public List<Apple> getAppleList() {
        return AppleList;
    }

    public void setAppleList(List<Apple> appleList) {
        AppleList = appleList;
    }

}
