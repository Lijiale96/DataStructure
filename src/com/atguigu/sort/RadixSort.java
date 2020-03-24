package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
//        int arr[] ={53,3,542,748,14,214};

        //80000000*11*4/1024/1024/1024 =3.3G
        int[] arr =new int[80000];
        for (int i=0;i<80000;i++){
            arr[i] =(int)(Math.random()*8000000);//生成一个【0,8000000）数
        }
        System.out.println("排序前");
        Date data1 =new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data1Str = simpleDateFormat.format(data1);
        System.out.println("基数排序前的时间是="+data1Str);
        int temp[] = new int[arr.length];
        radixSort(arr);
        Date data2 = new Date();
        // SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd HH:");
        String data2Str = simpleDateFormat.format(data2);
        System.out.println("基数排序后的时间是="+data2Str);
    }

    public static void radixSort(int[] arr){

        //根据推导
        //1. 得到数组中最大的数的位数
        int max =arr[0]; //假设第一数就是最大数
        for (int i=1;i<arr.length;i++){
            if (arr[i]>max){
                max =arr[i];
            }
        }
        //得到最大数的几位数
        int maxLength =(max+"").length();

        //定义一个二维数组，表示10个通，每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定为arr，length
        //3.基数排序是使用空间换时间的经典算法
        int [][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //bucketElementCounts[0],记录的就是bucket【0】桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        //这里我们使用循坏将代码处理
        for (int i=0,n=1;i<maxLength;i++,n*=10) {
            //（针对每个元素的对应位进行排序处理）第一次个位，第二次十位，第三次是百位
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的值
                int digitofElement = arr[j] /n % 10;
                //放入到对应的桶中
                bucket[digitofElement][bucketElementCounts[digitofElement]] = arr[j];
                bucketElementCounts[digitofElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            //遍历每一桶，并将桶中数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中，有数据，我们才放到原数组
                if (bucketElementCounts[k] != 0) {
                    //循坏该桶即第k个桶（即第k个一维数组），放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个bucketElementCounts[k]=0！！！
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第"+(i+1)+"轮，对个位数的排序处理 arr =" + Arrays.toString(arr));
        }


//        //第一轮（针对每个元素的个位进行排序处理）
//        for (int j=0;j<arr.length;j++){
//            //取出每个元素的个位的值
//            int digitofElement= arr[j]%10;
//            //放入到对应的桶中
//            bucket[digitofElement][bucketElementCounts[digitofElement]] =arr[j];
//            bucketElementCounts[digitofElement]++;
//        }
//        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
//        int  index =0;
//        //遍历每一桶，并将桶中数据，放入到原数组
//        for (int k=0;k<bucketElementCounts.length;k++){
//            //如果桶中，有数据，我们才放到原数组
//            if (bucketElementCounts[k]!=0){
//                //循坏该桶即第k个桶（即第k个一维数组），放入
//                for (int l=0;l<bucketElementCounts[k];l++){
//                    //取出元素放入到arr
//                    arr[index++]=bucket[k][l];
//                }
//            }
//            //第一轮处理后，需要将每个bucketElementCounts[k]=0！！！
//            bucketElementCounts[k]=0;
//        }
//        System.out.println("第一轮，对个位数的排序处理 arr ="+ Arrays.toString(arr));
//
//
//        //第二轮（针对每个元素的十位进行排序处理）
//        for (int j=0;j<arr.length;j++){
//            //取出每个元素的十位的值
//            int digitofElement= arr[j]/10 %10;
//            //放入到对应的桶中
//            bucket[digitofElement][bucketElementCounts[digitofElement]] =arr[j];
//            bucketElementCounts[digitofElement]++;
//        }
//        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
//        index =0;
//        //遍历每一桶，并将桶中数据，放入到原数组
//        for (int k=0;k<bucketElementCounts.length;k++){
//            //如果桶中，有数据，我们才放到原数组
//            if (bucketElementCounts[k]!=0){
//                //循坏该桶即第k个桶（即第k个一维数组），放入
//                for (int l=0;l<bucketElementCounts[k];l++){
//                    //取出元素放入到arr
//                    arr[index++]=bucket[k][l];
//                }
//            }
//            //第二轮处理后，需要将每个bucketElementCounts[k]=0！！！
//            bucketElementCounts[k]=0;
//        }
//        System.out.println("第二轮，对十位数的排序处理 arr ="+ Arrays.toString(arr));
//
//        //第三轮（针对每个元素的百位进行排序处理）
//        for (int j=0;j<arr.length;j++){
//            //取出每个元素的百位的值
//            int digitofElement= arr[j]/100 %10;
//            //放入到对应的桶中
//            bucket[digitofElement][bucketElementCounts[digitofElement]] =arr[j];
//            bucketElementCounts[digitofElement]++;
//        }
//        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
//        index =0;
//        //遍历每一桶，并将桶中数据，放入到原数组
//        for (int k=0;k<bucketElementCounts.length;k++){
//            //如果桶中，有数据，我们才放到原数组
//            if (bucketElementCounts[k]!=0){
//                //循坏该桶即第k个桶（即第k个一维数组），放入
//                for (int l=0;l<bucketElementCounts[k];l++){
//                    //取出元素放入到arr
//                    arr[index++]=bucket[k][l];
//                }
//            }
//            //第三轮处理后，需要将每个bucketElementCounts[k]=0！！！
//            bucketElementCounts[k]=0;
//        }
//        System.out.println("第三轮，对百位数的排序处理 arr ="+ Arrays.toString(arr));
    }
}
