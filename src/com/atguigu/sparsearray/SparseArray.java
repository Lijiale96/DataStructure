package com.atguigu.sparsearray;

import java.io.*;
import java.util.ArrayList;

public class SparseArray {
    public static void main(String[] args) throws FileNotFoundException {
        //创建一个原始的二维数组 11*11
        //0：表示满意棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 1;
        chessArr1[4][5] = 2;
        chessArr1[1][5] = 1;
        chessArr1[5][5] = 1;
        chessArr1[2][5] = 2;

        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //将二维数组 转 稀疏数组的思路
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);

        //2、创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0的值存放到sparseArr中
        int count = 0; //count 用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为~~~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        testo(sparseArr);
        testi(sum);


        /**
         *  稀疏数组转原始的二维数组的思路
         * 1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
         * 2、在读取稀疏数组后几行的数据，并赋给原始的二维数组
         */

        //1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组

        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2. 在读取稀数组后几行的数据(从第二行开始），并赋给原始的二维数组即可

        for (int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");

        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

     public  static void testo(int[][] sparseArr){
        /**
     * 将稀疏数组保存到磁盘,利用IO流将稀疏数组写入map文件
     */
         System.out.println("将稀疏数组保存到磁盘,利用IO流将稀疏数组写入map文件！！！！");
        FileWriter writer = null;
        try {
            writer = new FileWriter("map.txt");
            for(int i =0; i < sparseArr.length; i++) {
                for(int j = 0; j < 3; j++) {
                    writer.write(sparseArr[i][j]);
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) {
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private  static void testi(int sum){
        /**
         * 从磁盘读将稀疏数组
         */
        System.out.println();
        System.out.print("再从磁盘读将稀疏数组,");
        System.out.println("那么读取后稀疏数组为：");
        FileReader reader = null;
        int[][] sparseArr2 = new int[sum+1][3];
        int getNum = 0;
        try {
            reader = new FileReader("map.txt");

            for(int i =0; i < sparseArr2.length; i++) {
                for(int j =0; j < 3; j++) {
                    getNum = reader.read();
                    sparseArr2[i][j] = getNum;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        for(int i =0; i < sparseArr2.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArr2[i][0],sparseArr2[i][1],sparseArr2[i][2]);

        }
        //输出一下的稀疏数组
        System.out.println();

    }

    }

