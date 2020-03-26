package com.atguigu.search;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {

        int[] arr = new int[100];
        for (int i=0;i<100;i++){
            arr[i] =i+1;
        }
        int index= insertValueSearch(arr,0,arr.length-1,32);
//        int index= binarySearch(arr,0,arr.length-1,32);

        System.out.println("index="+index);
    }

    //编写插值查找算法
    //说明：插值查找算法，也要求数组是有序的
    /**
     *
     * @param arr 数组
     * @param left 左索引
     * @param right 右索引
     * @param findVal 查找值
     * @return
     */
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){

        System.out.println("插值查找次数~");
        //注意：findVal < arr[0] || findVal >arr[arr.length-1]必须需要
        //否则mid可能越界
        if (left > right || findVal < arr[0] || findVal >arr[arr.length-1]){
              return -1;
        }

        //求出mid
        int mid = left + (right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if (findVal>midVal){ //说明应该向右边递归
             return insertValueSearch(arr, mid+1, right, findVal);
        }else if (findVal<midVal){
            return insertValueSearch(arr, left, mid-1, findVal);
        }else{
            return mid;
        }
    }


    public static int binarySearch(int[] arr,int left,int right,int findVal){
        System.out.println("二分查找次数~");
        //当left>right 时 说明递归整个数组，但是没有找到
        if (left>right){
            return -1;
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if (findVal>midVal){
            return  binarySearch(arr,mid+1,right,findVal);
        }else if(findVal<midVal){//向左递归
            return binarySearch(arr,left,mid-1,findVal);
        }else{
            return mid;
        }
    }
}
