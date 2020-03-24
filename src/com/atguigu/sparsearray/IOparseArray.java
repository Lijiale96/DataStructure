package com.atguigu.sparsearray;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class IOparseArray {
    public static void main(String[] args) {

        //创建一个原始的二维数组 11*11
        //0:表示没有棋子
        //1：表示黑子
        //2：表示白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][3] = 2;
        chessArr1[6][1] = 2;
        //遍历输出
        for(int[] row: chessArr1) {
            for(int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.print("\n");
        }

        //遍历原始获得非0数的总数sum
        int sum = 0;
        for(int i =0; i < 11; i++) {
            for(int j =0; j < 11; j++) {
                if(chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //行的数量是sum+1,因为第一行是保留原始数组的数据用的
        //列恒定为3
        int[][] sparseArr = new int[sum+1][3];
        //初始化稀疏数组的第一行
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;


      //遍历二维数组，将非0的值存放到稀疏数组中
        int count = 0;
        //count用来记录现在的值是第几个非零数据
        for(int i =0; i < 11; i++) {
            for(int j =0; j < 11; j++) {
                if(chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出一下稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为");
        for(int i =0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
            //格式化输出
        }

        //利用IO流将稀疏数组写入save1文件
        //选择流
        FileWriter writer = null;
        try {
            //创建源
            writer = new FileWriter("Save1.data");
            //操作写入
            for(int i =0; i < sparseArr.length; i++) {
                for(int j = 0; j < 3; j++) {
                    writer.write(sparseArr[i][j]);
                }
//			writer.write("\t");
//				写入的时候不需要换行！！我在这里摔倒了就不希望有人再在同一个地方摔倒。
//				如果你发现写入和读取的数字不对，第一件事情请看看你有没有把换行符之类的也写入了
//				导致reader把你的换行符也读取了。
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if(writer != null) {
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

       //利用IO流将save1文件读取成稀疏数组
        FileReader reader = null;
        int[][] sparseArr2 = new int[sum+1][3];
        int getNum = 0;
        try {
            reader = new FileReader("Save1.data");

            for(int i =0; i < sparseArr2.length; i++) {
                for(int j =0; j < 3; j++) {
                    getNum = reader.read();
                    sparseArr2[i][j] = getNum;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、释放资源
            try {
                if(reader != null) {
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

     //输出一下的稀疏数组
        System.out.println();
        System.out.println("读取后稀疏数组为");
        for(int i =0; i < sparseArr2.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArr2[i][0],sparseArr2[i][1],sparseArr2[i][2]);
            //格式化输出
        }
        System.out.println();

//首先把二维数组构建出来，利用稀疏数组的第一行
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];

//将稀疏数组恢复成原始的二维数组
        for(int i = 1; i < sparseArr2.length; i++) {
            //从稀疏数组的第二行开始
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }

//遍历输出恢复后的二维数组
        for(int[] row: chessArr2) {
            for(int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
