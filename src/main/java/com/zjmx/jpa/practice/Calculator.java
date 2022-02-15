package com.zjmx.jpa.practice;

import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

/**
 * 条件过多得数据处理
 */
@Component
public class Calculator implements BiFunction<Double,Double,Double> {
    @Override
    public Double apply(Double multiplier, Double multiplied) {
        //计算两数相乘
        return multiplier*multiplied;
    }
}
@Component
class Divide implements BiFunction<Double,Double,Double>{

    @Override
    public Double apply(Double multiplier, Double multiplied) {
        return multiplier*1.0/multiplied;
    }
}
