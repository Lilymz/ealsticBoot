package com.zjmx.elastic.async;

import lombok.SneakyThrows;

public abstract class Task implements Runnable{
    private Integer task;
    public Task(Integer task){
        this.task=task;
    }
    /**
     * 重写父类run方法
     */
    @SneakyThrows(value = Exception.class)
    @Override
    public void run() {
        executeTask(task);
    }

    /**
     * 具体任务实现
     */
    public abstract void executeTask(Integer task) throws Exception;

}
