package com.atguigu.recursion;

import java.util.Collection;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map=new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右全部置为1
        for (int i=0;i<8;i++){
            map[i][0]=1;
           map[i][6]=1;
        }

        //设置挡板 1表示
        map[3][1]=1;
        map[3][2]=1;
       // map[1][2]=1;
       // map[2][2]=1;

        //输出地图
        System.out.println("地图的情况");
    for(int i =0;i<8;i++){
        for (int j=0;j<7;j++){
            System.out.print(map[i][j]+ " ");
        }
        System.out.println();
    }

    //把所有的策略用一个数组的方式表示出来，用for循坏遍历，将2保存到一个集合中，最少的则为最短
    //使用递归回溯
//        boolean[] arr = new boolean[]{setWay2(map,1,1),setWay8(map,1,1),setWay3(map,1,1),setWay4(map,1,1),
//               setWay5(map,1,1), setWay6(map,1,1),setWay7(map,1,1),setWay(map,1,1)};
        boolean[] arr = new boolean[]{setWay5(map,1,1),setWay2(map,1,1)};
//
    for (boolean a:arr){
        System.out.printf("是否找到出路："+a);
        //输出新的地图，小球走过，并标识过的递归
        long startTime=System.currentTimeMillis();            //获得当前时间
        System.out.println(", 则小球走过，并标识过的地图的情况");
        for(int i =0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
        long endTime=System.currentTimeMillis();                //获得当前时间
        System.out.println("该方法耗时"+(endTime-startTime)+"ms");
    }
    }
//    }
//        setWay(map,1,1);
//        setWay2(map,1,1);
//        setWay3(map,1,1);
//        setWay4(map,1,1);
//        setWay5(map,1,1);
//        setWay6(map,1,1);
//        setWay7(map,1,1);
//        setWay8(map,1,1);

//    //输出新的地图，小球走过，并标识过的递归
//        System.out.println("小球走过，并标识过的 地图的情况");
//        for(int i =0;i<8;i++){
//            for (int j=0;j<7;j++){
//                System.out.print(map[i][j]+ " ");
//            }
//            System.out.println();
//        }
//    }


    //使用递归回溯来给小球找路
    //说明
    //1.map 表示地图
    //2.i，j 表示从地图的哪个位置开始出发（1,1）
    //3.如果小球能到map【6】【5】位置，则说明通路找到
    //4.约定：当map【i】【j】 为0表示该点没有走过 当为1表示墙，为2表示通路可以走；3表示该点已经走过，但是走不通
    //5.走迷宫时，需要确定一个策略（方法) 下-》右-》上-》左，如果该点走不通，再回溯
    /**
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回true，否则返回false
     */
    public static  boolean setWay(int[][] map,int i,int j){
        int count=0;
        if (map[6][5]==2){ // 通路已经找到ok
            return true;
        }else{
         if (map[i][j]==0){//如果当前这个点还没有走过
             //按照策略 下-》右-》上-》左 走
             map[i][j]=2; //假定该点是可以走通
             if (setWay(map, i+1, j)){//向下走

                 return true;
             }else if (setWay(map, i, j+1)){//向右走
                 return true;
             }else if (setWay(map, i-1, j)){//向上走
                 return true;
             }else if (setWay(map, i, j-1)){//向左走
                 return true;
             }else{
                 //说明该点是走不通的，是死路
                 map[i][j]=3;
                 return false;
             }
         }else{ // 如果map[i][j]!=0,可能是1,2,3
             return false;
         }
        }
}

   //修改找路的策略，改成 上——》右——》下——》左
    public static  boolean setWay2(int[][] map,int i,int j){
        if (map[6][5]==2){ // 通路已经找到ok
            return true;
        }else{
            if (map[i][j]==0){//如果当前这个点还没有走过
                //按照策略 上——》右——》下——》左走
                map[i][j]=2; //假定该点是可以走通
                if (setWay2(map, i-1, j)){//向上 走
                    return true;
                }else if (setWay2(map, i, j+1)){//向右走
                    return true;
                }else if (setWay2(map, i+1, j)){//向下走
                    return true;
                }else if (setWay2(map, i, j-1)){//向左走
                    return true;
                }else{
                    //说明该点是走不通的，是死路
                    map[i][j]=3;
                    return false;
                }
            }else{ // 如果map[i][j]!=0,可能是1,2,3
                return false;
            }
        }
    }

    //修改找路的策略，改成 下——》左——》上——》右
    public static  boolean setWay3(int[][] map,int i,int j){
        if (map[6][5]==2){ // 通路已经找到ok
            return true;
        }else{
            if (map[i][j]==0){//如果当前这个点还没有走过
                //按照策略 下——》左——》上——》右走
                map[i][j]=2; //假定该点是可以走通
                if (setWay3(map, i+1, j)){//向下 走
                    return true;
                }else if (setWay3(map, i, j-1)){//向左走
                    return true;
                }else if (setWay3(map, i+1, j)){//向上走
                    return true;
                }else if (setWay3(map, i, j+1)){//向右走
                    return true;
                }else{
                    //说明该点是走不通的，是死路
                    map[i][j]=3;
                    return false;
                }
            }else{ // 如果map[i][j]!=0,可能是1,2,3
                return false;
            }
        }
    }

    //修改找路的策略，改成 上——》左——》下——》右
    public static  boolean setWay4(int[][] map,int i,int j){
        if (map[6][5]==2){ // 通路已经找到ok
            return true;
        }else{
            if (map[i][j]==0){//如果当前这个点还没有走过
                //按照策略 上——》左——》下——》右走
                map[i][j]=2; //假定该点是可以走通
                if (setWay4(map, i-1, j)){//向上 走
                    return true;
                }else if (setWay4(map, i, j-1)){//向左走
                    return true;
                }else if (setWay4(map, i+1, j)){//向下走
                    return true;
                }else if (setWay4(map, i, j+1)){//向右走
                    return true;
                }else{
                    //说明该点是走不通的，是死路
                    map[i][j]=3;
                    return false;
                }
            }else{ // 如果map[i][j]!=0,可能是1,2,3
                return false;
            }
        }
    }

    //修改找路的策略，改成 左——》上——》右——》下
    public static  boolean setWay5(int[][] map,int i,int j){
        if (map[6][5]==2){ // 通路已经找到ok
            return true;
        }else{
            if (map[i][j]==0){//如果当前这个点还没有走过
                //按照策略 左——》上——》右——》下走
                map[i][j]=2; //假定该点是可以走通
                if (setWay5(map, i, j-1)){//向左 走
                    return true;
                }else if (setWay5(map, i-1, j)){//向上走
                    return true;
                }else if (setWay5(map, i, j+1)){//向右走
                    return true;
                }else if (setWay5(map, i+1, j)){//向下走
                    return true;
                }else{
                    //说明该点是走不通的，是死路
                    map[i][j]=3;
                    return false;
                }
            }else{ // 如果map[i][j]!=0,可能是1,2,3
                return false;
            }
        }
    }

    //修改找路的策略，改成 左——》下——》右——》上
    public static  boolean setWay6(int[][] map,int i,int j){
        if (map[6][5]==2){ // 通路已经找到ok
            return true;
        }else{
            if (map[i][j]==0){//如果当前这个点还没有走过
                //按照策略 左——》下——》右——》上
                map[i][j]=2; //假定该点是可以走通
                if (setWay6(map, i, j-1)){//向左 走
                    return true;
                }else if (setWay6(map, i+1, j)){//向下走
                    return true;
                }else if (setWay6(map, i, j+1)){//向右走
                    return true;
                }else if (setWay6(map, i-1, j)){//向上走
                    return true;
                }else{
                    //说明该点是走不通的，是死路
                    map[i][j]=3;
                    return false;
                }
            }else{ // 如果map[i][j]!=0,可能是1,2,3
                return false;
            }
        }
    }

    //修改找路的策略，改成 右——》下——》左——》上
    public static  boolean setWay7(int[][] map,int i,int j){
        if (map[6][5]==2){ // 通路已经找到ok
            return true;
        }else{
            if (map[i][j]==0){//如果当前这个点还没有走过
                //按照策略 右——》下——》左——》上
                map[i][j]=2; //假定该点是可以走通
                if (setWay7(map, i, j+1)){//向右 走
                    return true;
                }else if (setWay7(map, i+1, j)){//向下走
                    return true;
                }else if (setWay7(map, i, j-1)){//向左走
                    return true;
                }else if (setWay7(map, i-1, j)){//向上走
                    return true;
                }else{
                    //说明该点是走不通的，是死路
                    map[i][j]=3;
                    return false;
                }
            }else{ // 如果map[i][j]!=0,可能是1,2,3
                return false;
            }
        }
    }

    //修改找路的策略，改成 右——》上——》左——》下
   public static  boolean setWay8(int[][] map,int i,int j){
        if (map[6][5]==2){
            return true;
        }else{
            if (map[i][j]==0){
                map[i][j]=2;
                if (setWay8(map, i, j+1)){
                    return  true;
                }else if (setWay8(map, i-1, j)){
                    return  true;
                }else if (setWay8(map, i, j-1)){
                    return  true;
                }else if (setWay8(map, i+1, j)){
                    return  true;
                }else{
                    map[i][j]=3;
                        return  false;
                }
            }else{
                return false;
            }
        }
   }

}
