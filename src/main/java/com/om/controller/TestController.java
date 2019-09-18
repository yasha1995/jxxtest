package com.om.controller;

import com.om.service.TestService;
import org.springframework.stereotype.Controller;
import sun.net.www.ApplicationLaunchException;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: luohua
 * @Date: 2019/3/13 10:53
 * @Version 1.0
 * 功能模块：
 */
@Controller
public class TestController {

    //冒泡
    public static void bubbleSort(double [] arr){
        if(arr == null || arr.length == 0)
            return ;
        for(int i=0; i<arr.length-1; i++) {
            for(int j=arr.length-1; j>i; j--) {
                if(arr[j] < arr[j-1]) {
                    swap(arr, j-1, j);
                }
            }
        }
//        for (int i = 0;i<arr.length;i++)
//        System.out.println(arr[i]);
    }
    public static void swap(double[] arr ,int i,int j){
        double t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;

    }
    //选择
    public static void selectSort(double[] arr) {
        Long date1 = System.currentTimeMillis();
        if(arr == null || arr.length == 0)
            return ;
        int indexMin = 0;
        for (int i= 0;i<arr.length;i++){
            for (int j= i+1;j<arr.length;j++){
                if (arr[j]<arr[indexMin]){
                   indexMin = j;
                }
            }
            if (indexMin !=i){
                swap(arr,i,indexMin);
            }
        }
        Long date2 = System.currentTimeMillis();
        System.out.println(date2-date1);
//        for (int i=0;i<arr.length;i++){
//            System.out.println(arr[i]);
//        }
    }



    public static void sort(double [] arr){
        if (arr.length == 0){
            return;
        }
         quickSort(arr,0,arr.length-1);
//        for (int i=0; i<arr.length;i++){
//            System.out.println(arr[i]);
//        }
    }
    public static void quickSort(double[] arr,int left,int right){
        if (left>=right)
            return;
        int key = dycSort(arr,left,right);
        quickSort(arr,left,key-1);
        quickSort(arr,key+1,right);
    }
    public static int dycSort(double[] arr,int left,int right){
        double point = arr[left];
        while (left<right) {
            while (left < right && arr[right] >= point)
                right--;
            arr[left] = arr[right];
            while (left < right && arr[left] <= point)
                left++;
            arr[right] = arr[left];
        }
        arr[left] = point;
        return left;
    }
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime dates = now.plusDays(1);
//        LocalDateTime weeks = now.plusWeeks(1);
//        LocalDateTime months = now.plusMonths(1);
//        LocalDateTime years = now.plusYears(1);
        System.out.println(now);
//        //2018-12-05T10:01:51.024
//        System.out.println(dates);
//        //2018-12-11T10:01:51.024
//        System.out.println(weeks);
//        //2019-01-04T10:01:51.024
//        System.out.println(months);
//        //2019-12-04T10:01:51.024
//        System.out.println(years);
//        int [] a = {6,7,4,3,5,343,32,123,1,4,3,21,32,3};
//        double [] a1 =new double[100000];
//        for(int i=0;i<100000;i++) {
//
//            a1[i] = Math.random() * 41 + 60;//random()[0,1),
//        }
////
////
//
//        Long dd = System.currentTimeMillis();
//        //bubbleSort(a1);
//        Long dd1 = System.currentTimeMillis();
//        selectSort(a1);
//        Long dd2 = System.currentTimeMillis();
//        //sort(a1);
//        Long dd3 = System.currentTimeMillis();
//        System.out.println(dd1-dd);
//        System.out.println(dd2-dd1);
//        System.out.println(dd3-dd2);
        addTest();

    }
    public static void addTest() {
        long   l     = Long.MAX_VALUE;
        double d     = l / 10000.0;
        double clone = d;

        System.out.println(d);
        for (int i = 0; i < 1000000000; i++) {
            clone += 1;
        }
        System.out.println(clone);
        System.out.println(clone == d);
    }

}
