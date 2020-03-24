package com.atguigu.linkedlist;

import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;


public class 反转部分单向链表 {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        反转部分单向链表 m = new 反转部分单向链表();
        //单链表
        int length = Integer.valueOf(bf.readLine());
        String[] s1 = bf.readLine().split(" ");
        String[] s2 = bf.readLine().split(" ");
        int L = Integer.valueOf(s2[0]);
        int R = Integer.valueOf(s2[1]);
        ListNode h = new ListNode(Integer.valueOf(s1[0]));
        ListNode cur = h;
        for (int i = 1; i < length; i++) {
            cur.next = new ListNode(Integer.valueOf(s1[i]));
            cur = cur.next;
        }
        ListNode fz = m.ReversepartList(h, L, R);
        m.printRes(fz);
    }

    public ListNode ReversepartList(ListNode head,int L,int R){
            if(head==null){
                return null;}
            ListNode cur=head;
            ListNode pre=null;
            while(L>1){
                pre=cur;
                cur=cur.next;
                L--;
                R--;
            }
        ListNode con=pre, tail=cur;
        ListNode next=null;
            while(R>0){
                next=cur.next;
                cur.next=pre;
                pre=cur;
                cur=next;
                R--;
            }

            if(con!=null){
                con.next=pre;
            }else{
                head=pre;
            }
            tail.next=cur;
            return head;
        }

    public void printRes(ListNode head) {
        ListNode cur = head;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.val + " ");
            cur = cur.next;
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }
}
 class ListNode{
    public int val;
    public ListNode next;
    public ListNode(int data){
        this.val = data;
    }
}