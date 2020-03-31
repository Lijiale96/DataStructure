package com.atguigu.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
       int[] arr={7,3,10,12,5,1,9};
       BinarySortTree binarySortTree = new BinarySortTree();
       for (int i=0;i<arr.length;i++){
           binarySortTree.add(new Node(arr[i]));
       }

       //中序遍历
        System.out.println("中序遍历二叉树");
       binarySortTree.infixOrder();//1,3,5,7,9,10,12
    }
}

//创建二叉排序树
class BinarySortTree{
    private Node root;

    //添加节点的方法
    public void add(Node node){
        if (root==null){
            root=node;//如果root为空则直接让root指向node
        }else {
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder(){
        if (root!=null){
            root.infixOrder();
        }else{
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}

//创建Node节点
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加节点方法
    //递归的形式添加结点，注意需要满足二叉排序树的要求
    public void add(Node node){
        if (node==null){
            return;
        }

        //判断传入的节点的值，和当前子树的根节点的值的关系
        if (node.value<this.value){
            //如果当前结点左子节点为null
            if (this.left==null){
                this.left=node;
            }else{
                //递归的向左子树添加
                this.left.add(node);
            }
        }else{//添加的节点的值大于 当前节点的值
            if (this.right==null){
                this.right=node;
            }else{
                //递归的向右子树添加
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right!=null){
            this.right.infixOrder();
        }
    }
}
