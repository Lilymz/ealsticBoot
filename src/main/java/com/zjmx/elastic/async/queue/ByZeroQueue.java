package com.zjmx.elastic.async.queue;

import com.zjmx.elastic.async.taskImpl.LongTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ByZeroQueue implements Runnable{

    private final ThreadPoolExecutor poolExecutor;

    /**
     *    当前可活动的最大线程数
     */
    private final static int MAX_THREAD_ACCOUNT = 10;

    public ByZeroQueue(ThreadPoolExecutor poolExecutor){
        this.poolExecutor=poolExecutor;
    }
    @Override
    public void run() {
        CountDownLatch countDownLatch = new CountDownLatch(12);
        while (countDownLatch.getCount()>0){
            LongTask longTask = new LongTask((int) (countDownLatch.getCount()));
            try {
                //判断是否还有空闲线程
                if (poolExecutor.getActiveCount()>=MAX_THREAD_ACCOUNT){
                    System.out.println("任务："+(countDownLatch.getCount())+"正在排队执行中.....");
                    TimeUnit.SECONDS.sleep(10);
                    retry(longTask);
                }
                poolExecutor.execute(longTask);
            }catch (Exception e){
                System.out.println(e.getMessage());
                retry(longTask);
            }finally {
                countDownLatch.countDown();
                System.out.println("任务："+countDownLatch.getCount());
            }
        }
    }

    /**
     * 重试
     * @param longTask
     */
    public void retry(LongTask longTask){
      System.out.println("正在重新执行任务.....");
      poolExecutor.execute(longTask);
    }
}
