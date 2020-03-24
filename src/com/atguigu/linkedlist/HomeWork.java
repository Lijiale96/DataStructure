package com.atguigu.linkedlist;

import java.io.Serializable;

//public class HomeWork {

    //单链表


//双向链表
//    public ListNode mergeTwoList(ListNode l1,ListNode l2){
//        ListNode prehead = new ListNode(-1);
//
//        ListNode prev = prehead;
//        while(l1!=null&&l2!=null){
//            if (l1.no<=l2.no){
//                prev.next=l1;
//                l1=l1.next;
//            }else{
//                prev.next=l2;
//                l2=l2.next;
//            }
//            prev=prev.next;
//        }
//
//        prev.next=l1==null?l2:l1;
//        return prehead.next;
//    }
//}





public class HomeWork {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(5);

        Node node4 = new Node(2);
        Node node5 = new Node(4);
        Node node6 = new Node(6);

        node1.setNext(node2);
        node2.setNext(node3);

        node4.setNext(node5);
        node5.setNext(node6);

        Node result = merge(node1,node4);

        while(result != null) {
            System.out.print(result.getData() + "\t");
            result = result.getNext();
        }
        /*
        1	2	3	4	5	6*/
    }

    private static Node merge(Node node1, Node node2) {
        // 判断需要合并的链表是否为空
        if(node1 == null && node2 == null) {
            throw new RuntimeException("需要合并的两条链表都为空！");
        }
        // 一条链表为空，直接返回另一条链表
        if(node1 == null) {
            return node2;
        }
        if(node2 == null) {
            return node1;
        }
        // 合并后的新链表的头结点
        Node head = null;
        // 选出最小值作为合并后的新链表的头结点
        if(node1.getData() <= node2.getData()) {
            head = node1;
            // 后移节点
            node1 = node1.getNext();
        } else {
            head = node2;
            // 后移节点
            node2 = node2.getNext();
        }
        // 临时变量，用来链接合并链表的节点
        Node temp = head;
        // 循环条件
        while(node1 != null && node2 != null) {
            if(node2.getData() <= node2.getData()) {
                temp.setNext(node1);
                // 后移节点
                node1 = node1.getNext();
            } else {
                temp.setNext(node2);
                // 后移节点
                node2 = node2.getNext();
            }
            // 临时变量，用来链接合并链表的节点
            temp = temp.getNext();
        }
        if(node1 == null) {
            temp.setNext(node2);
        }
        if(node2 == null) {
            temp.setNext(node1);
        }
        return head;
    }
}

class Node implements Serializable {
    private static final long serialVersionUID = 6539573865089177060L;

    private int data;
    private Node next;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}



