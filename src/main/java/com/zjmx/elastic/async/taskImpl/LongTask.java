package com.zjmx.elastic.async.taskImpl;

import com.zjmx.elastic.async.Task;

import java.util.concurrent.TimeUnit;

public class LongTask extends Task {
    public LongTask(Integer task) {
        super(task);
    }

    @Override
    public void executeTask(Integer task) throws Exception {
        System.out.println("~~~正在执行任务："+task);
        //需要执行20s的大任务
        TimeUnit.SECONDS.sleep(10);
        System.out.println(task+"任务执行结束~~~");
    }


}
