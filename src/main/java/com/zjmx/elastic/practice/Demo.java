package com.zjmx.elastic.practice;

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

}
