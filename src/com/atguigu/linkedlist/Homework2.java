package com.atguigu.linkedlist;

public class Homework2 {

public static class LLNode {
    private Object data;//存放数据
    private LLNode next;//指向下一个节点
    public LLNode(){
    }
    public LLNode(Object data){
        this.data=data;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public LLNode getNext() {
        return next;
    }
    public void setNext(LLNode next) {
        this.next = next;
    }
}

public static class LLStack {
    LLNode headnode=null;
    public LLStack(){
        headnode=new LLNode(null);//先初始化
    }

    public boolean isEmpty(){//判断是否为空的
        return headnode==null;
    }

    public void push(Object data){//入栈
        if(headnode.getData()==null){//判断头结点的值为空的时候
            headnode.setData(data);
        }
        else if(headnode==null){
            headnode=new LLNode(data);
        }
        else {
            LLNode newnode=new LLNode(data);
            newnode.setNext(headnode);
            headnode=newnode;
        }
    }
    public Object pop(){//出栈(返回栈顶的值，并且删除)
        Object data=null;
        if(isEmpty()){
            System.out.println("栈为空，返回值为0");
            return 0;
        }
        data=headnode.getData();
        headnode=headnode.getNext();
        return data;
    }

    public Object top(){//返回栈顶的值，但是不删除
        Object data=null;
        if(isEmpty()){
            System.out.println("栈为空，返回值为0");
            return 0;
        }
        data=headnode.getData();
        return data;
    }

    public int getLength(){//得到栈里面值的个数
        int count=0;
        LLNode tempnode=headnode;
        if(isEmpty()||tempnode.getData()==null)//当头结点为空，并且值也为空的时候就返回0
        {
            count=0;
        }
        else
        {
            while(tempnode!=null)
            {
                count++;
                tempnode=tempnode.getNext();
            }
        }
        return count;
    }
}
    public static void main(String[] args) {
        LLStack llStack = new LLStack();
        llStack.push(1);
        llStack.push(2);
        llStack.push(3);
        llStack.push(4);
        llStack.push(5);
        llStack.push(6);
        System.out.println("栈里面值的个数为：" + llStack.getLength());
        llStack.pop();
        llStack.pop();
        llStack.pop();
        llStack.pop();
        System.out.println("pop4个之后，栈里面的个数 为 ：" + llStack.getLength());
        System.out.println("pop4个之后，栈顶的值为：" + llStack.top());

    }
}