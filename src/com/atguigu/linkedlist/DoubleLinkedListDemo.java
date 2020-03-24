package com.atguigu.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
         //测试
        System.out.println("双向链表的测试");
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");
        HeroNode2 hero5 = new HeroNode2(5,"朱冲","户子头");
        HeroNode2 hero6 = new HeroNode2(6,"李冲","狗子头");
        HeroNode2 hero7 = new HeroNode2(7,"张冲","猪子头");
        HeroNode2 hero8 = new HeroNode2(8,"王冲","猫子头");

        //创建一个双向链表
        DoubleLinkedList doubleLinkedList =new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);


        doubleLinkedList.addOder(hero4);
        doubleLinkedList.addOder(hero1);
        doubleLinkedList.addOder(hero2);
        doubleLinkedList.addOder(hero3);
        doubleLinkedList.addOder(hero8);
        doubleLinkedList.addOder(hero4);
//        doubleLinkedList.addOder(hero5);
        doubleLinkedList.list();

//        //修改
//        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
//        doubleLinkedList.update(newHeroNode);
//        System.out.println("修改后的链表情况");
//        doubleLinkedList.list();
//
//        //删除
//        doubleLinkedList.del(3);
//        System.out.println("删除后的链表情况");
//        doubleLinkedList.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头节点，头结点不要动
    private HeroNode2 head = new HeroNode2(0,"","");
    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法
    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("双向链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while(true){
            //判断是否到链表最后
            if (temp ==null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp =temp.next;
        }
    }
    //添加一个节点到双向链表的最后。
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，因此我们需要一个辅助temp
        HeroNode2 temp = head;
        //遍历链表，扎到最后
        while(true){
            //找到链表的最后
            if (temp.next==null){//
                break;
            }
            //如果没有找到最后，最后temp后移
            temp=temp.next;
        }
        //当退出循环时，temp就指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre=temp;
    }

    //按编号顺序添加
    public void addOder(HeroNode2 heroNode){
        HeroNode2 temp = head;
       boolean flag1 =false;
       boolean flag2 =false;
       boolean flag3 =false;
        while(true){
            if (temp.next==null){ //最后一个
                flag1=true;
                break;
            }if (temp.next.no > heroNode.no){ //没到最后
                flag2=true;
                break;
            }else if (temp.no == heroNode.no){
                flag3=true;
                break;
            }
            temp =temp.next;
        }
        if (flag3) {
            System.out.printf("该编号 %d 已存在 \n", heroNode.no);
        }if (flag1){
            temp.next=heroNode;
            heroNode.pre=temp;
        }if (flag2){
            heroNode.next = temp.next;
            heroNode.pre = temp;
            temp.next.pre = heroNode;
            temp.next = heroNode;
        }
    }

    //修改一个节点内容，可以看到双向链表的节点内容修改和单向链表一样
    //只是
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while(true){
            if (temp==null){
                break;//已经遍历完链表
            }
            if(temp.no==newHeroNode.no){
                //找到
                flag = true;
                break;
            }
            temp=temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if (flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else{ //没有找到
            System.out.printf("没有找到 编号%d的节点，不能修改\n",newHeroNode.no);
        }
    }

    //从双向链表中删除一个节点
    //说明
    //1、对于双向链表，我们可以直接找到要删除的这个节点
    //2、找到后，自我删除即可
    public void del(int no){

        //判断当前链表是否为空
        if (head.next==null){ //空链表
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next; //辅助变量（指针）
        boolean flag = false;//标志是否找到待删除节点的
        while(true){
            if (temp==null){ //已经到链表的最后
                break;
            }
            if (temp.next.no == no ){
                //找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp =temp.next; //temp后移，遍历
        }
        //判断flag
        if (flag){//找到
            //可以删除
            //temp.next = temp.next.next;[单向链表]
            temp.pre.next = temp.next;
            //如果是最后一个节点，不需要执行下面，否则出现空指针异常
            if (temp.next!=null) {
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("要删除的%d 节点不存在\n",no);
        }
    }
}

//定义HeroNode2，每个HeroNode 对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点，默认为null
    public HeroNode2 pre;//指向前一个节点，默认为null
    //构造器
    public HeroNode2(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }
    //为了显示方便，我们重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}