package com.om.service;

import org.springframework.stereotype.Service;

/**
 * @Author: luohua
 * @Date: 2019/8/29 11:32
 * @Version 1.0
 * 功能模块：
 */
@Service
public class ThreadAB {

    public static void main(String[] args)throws InterruptedException {
        turning();
    }
    private static int count = 0;
    private static final  Object lock = new Object();

    public static void  turning() throws InterruptedException{
        Thread even = new Thread(()->{
               while (count <= 100){
                   synchronized (lock){
                       System.out.println("偶数："+ count++ +"qw");
                       lock.notifyAll();
                    try{
                        if (count <= 100){
                            lock.wait();
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                   }
            }
        });
        Thread odd = new Thread(()->{
            while (count <= 100){
                synchronized (lock){
                    System.out.println("奇数："+count++);
                    lock.notifyAll();
                    try{
                        if (count <= 100){
                            lock.wait();
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        even.start();
        Thread.sleep(10);
        odd.start();

    }

}

