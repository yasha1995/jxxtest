package com.om.service;

import java.util.concurrent.*;

/**
 * @Author: luohua
 * @Date: 2019/4/1 16:54
 * @Version 1.0
 * 功能模块：自定义FutureTask
 */
public class EnjiyFutureTask<V> implements Runnable, Future<V> {
    Callable<V> callable;
    V result = null;

    public EnjiyFutureTask(Callable<V> callable){
        this.callable = callable;
    }
    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        synchronized (this){
            this.notifyAll();
        }
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        if (result != null){
            return result;
        }
        synchronized (this){
            this.wait();
        }
        return result;
    }
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }



    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
