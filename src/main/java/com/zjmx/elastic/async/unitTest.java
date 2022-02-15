package com.zjmx.elastic.async;

import com.zjmx.elastic.async.queue.ByZeroQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class unitTest {
    public static void main(String[] args) {
        final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 10, 10000,
                TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.DiscardOldestPolicy());
        ByZeroQueue byZeroQueue =new ByZeroQueue(poolExecutor);
        Thread thread = new Thread(byZeroQueue);
        thread.start();
    }
}
