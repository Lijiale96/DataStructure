package com.atguigu.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("前序");
        arrBinaryTree.preOrder();//1,2,4,5,3,6,7
        System.out.println();
        System.out.println("中序");
        arrBinaryTree.infixOrder();//4,2,5,1,6,3,7
        System.out.println();
        System.out.println("后序");
        arrBinaryTree.postOrder();//4,5,2,6,7,3,1
    }
}

class ArrBinaryTree{
       private int[] arr;//存储数据节点的数据
        public ArrBinaryTree(int[] arr){
            this.arr=arr;
        }

        //重载preOrder
    public void preOrder(){
            this.preOrder(0);
    }
    //重载preOrder
    public void infixOrder(){
        this.infixOrder(0);
    }
    //重载preOrder
    public void postOrder(){
        this.postOrder(0);
    }
        //编写一个方法，完成顺序存储二叉树的前序遍历
        /**
         *
         * @param index 数组的下标
         */
        public void preOrder(int index){
            //如果数组为空，或者arr.length=0
            if(arr==null||arr.length==0){
                System.out.println("数组为空，不能按照二叉树的前序遍历");
            }
            //输出当前这个元素
            System.out.print(arr[index]);
            //向左递归遍历
            if ((index*2+1)<arr.length){
                preOrder(2*index+1);
            }
            //向右递归遍历
            if((index*2+2)<arr.length){
                preOrder(2*index+2);
            }
        }

    /**
     *
     * @param index 数组的下标
     */
    public void infixOrder(int index){
        //如果数组为空，或者arr.length=0
        if(arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的中序遍历");
        }
        //输出当前这个元素
        //向左递归遍历
        if ((index*2+1)<arr.length){
            infixOrder(2*index+1);
        }
        System.out.print(arr[index]);
        //向右递归遍历
        if((index*2+2)<arr.length){
            infixOrder(2*index+2);
        }
    }
    /**
     *
     * @param index 数组的下标
     */
    public void postOrder(int index){
        //如果数组为空，或者arr.length=0
        if(arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的后序遍历");
        }
        //输出当前这个元素
        //向左递归遍历
        if ((index*2+1)<arr.length){
            postOrder(2*index+1);
        }
        //向右递归遍历
        if((index*2+2)<arr.length){
            postOrder(2*index+2);
        }
        System.out.print(arr[index]);
    }
}



