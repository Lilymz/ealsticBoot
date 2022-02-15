package com.zjmx.jpa.practice;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Component
public class Demo {
    @Resource
    @Lazy
    private Calculator calculator;
    @Resource
    @Lazy
    private Divide divide;

    private final Map<String, BiFunction<Double,Double,Double>> functionMap =new HashMap<>();

    public Map<String, BiFunction<Double,Double,Double>> getFunctionMap() {
        return functionMap;
    }


    @PostConstruct
    void init(){
        functionMap.put("*",calculator);
        functionMap.put("/",divide);
    }

    /** //PersonRepository-----之下方法
     *
     * findByAddressZipCode(jpa如何解析成jpql-SQL：属性表达式)
     *
     * 假设 Person有Address之下有ZipCode。在这种情况下，
     * 该方法会创建x.address.zipCode属性遍历。解析算法首先将整个部分 ( AddressZipCode) 解释为属性，
     * findByAddressZipCode拆分为AddressZipCode
     * 并检查域类中具有该名称（未大写）的属性。如果算法成功，它将使用该属性。如果不是，
     * 该算法将沿在驼峰部分从右侧拆分为头部和尾部，并尝试找到相应的属性——在我们的示例中，AddressZip和Code。
     * 如果算法找到具有该头部的属性，它将获取尾部并继续从那里向下构建树，以刚才描述的方式将尾部拆分。
     * 如果第一个分割不匹配，算法将分割点向左移动 ( Address,ZipCode) 并继续。
     *
     */
    public static class Person{
        private Address address;
    }
    public static class Address{
        private ZipCode zipCode;
    }
    public static class ZipCode{
        private String code;

        private String zip;
    }
}
