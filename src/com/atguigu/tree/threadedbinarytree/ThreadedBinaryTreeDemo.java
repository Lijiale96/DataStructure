package com.atguigu.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

        HeroNode root = new HeroNode(1, "kang");
        HeroNode node2 = new HeroNode(3, "tom");
        HeroNode node3 = new HeroNode(6, "jack");
        HeroNode node4 = new HeroNode(8, "smith");
        HeroNode node5 = new HeroNode(10, "dim");
        HeroNode node6 = new HeroNode(14, "bb");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //后序线索化
        node2.setParent(root);
        node3.setParent(root);
        node4.setParent(node2);
        node5.setParent(node2);
        node6.setParent(node3);
        //中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes2();

        //测试：以10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的前驱结点是= " + leftNode);
        System.out.println("10号结点的后继节点是= " + rightNode);

        //当线索化二叉树后，bu能在使用原来的遍历方法
//        threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList22();
    }
}
//线索化二叉树
//定义ThreadBinaryTree  实现了线索化功能的二叉树
class ThreadedBinaryTree{
    private HeroNode root;

    //为了实现线索化，需要创建要给指向当前结点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre =null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载threadedNodes1 前序
    public void threadedNodes1(){
        this.threadedNodes1(root);
    }

    //重载threadedNodes 中序
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //重载threadedNodes 后序
    public void threadedNodes2(){
        this.threadedNodes2(root);
    }

    //遍历前序线索化二叉树的方法
    public void threadedList1(){
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node =root;

        while(node != null){
            //打印当前这个节点
            System.out.println(node);
            //循坏的找到leftType ==1 的结点，第一个找到就是8节点
            //后面随着遍历而变化，因为当leftType==1时，说明该结点是按照线索化
            //处理后的节点
            while(node.getLeftType()==0){
                node = node.getLeft();
                System.out.println(node);
            }
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while(node.getRightType()==1){
                //获取到当前结点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }
    //编写对二叉树进行前序线索化的方法
    /**
     *
     * @param node 就是当前需要线索化的结点
     */
    public void threadedNodes1(HeroNode node){

        //如果 node==null，不能线索化
        if (node==null){
            return;
        }
        // (一) 线索化当前结点[有点难度]
        //处理当前节点的前驱节点
        //以8结点的left == null，8结点的leftType=1（相当于前驱节点，空）
        if (node.getLeft()==null){
            //让当前结点的左指针指向前驱节点(pre)
            node.setLeft(pre);
            //修改当前节点的左指针的类型，指向前驱节点
            node.setLeftType(1);//8结点的leftType=1（相当于前驱节点，空）
        }
        //处理后继节点
        if (pre != null && pre.getRight()==null){//如果本身有值就不能处理
            //让前驱结点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //!!!每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
        // (二) 先线索化左子树
        if(node.getLeftType()==0) {
            threadedNodes1(node.getLeft());
        }
        // (三) 再线索化右子树
        if(node.getRightType()==0) {
            threadedNodes1(node.getRight());
        }
    }


    //遍历中序线索化二叉树的方法
    public void threadedList(){
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node =root;
        while(node != null){
            //循坏的找到leftType ==1 的结点，第一个找到就是8节点
            //后面随着遍历而变化，因为当leftType==1时，说明该结点是按照线索化
            //处理后的节点
            while(node.getLeftType()==0){
                node = node.getLeft();
            }

            //打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while(node.getRightType()==1){
                //获取到当前结点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }
    //编写对二叉树进行中序线索化的方法
    /**
     *
     * @param node 就是当前需要线索化的结点
     */
    public void threadedNodes(HeroNode node){

        //如果 node==null，不能线索化
        if (node==null){
            return;
        }
        // (一) 先线索化左子树
        threadedNodes(node.getLeft());
        // (二) 线索化当前结点[有点难度]

        //处理当前节点的前驱节点
        //以8结点的left == null，8结点的leftType=1（相当于前驱节点，空）
        if (node.getLeft()==null){
            //让当前结点的左指针指向前驱节点(pre)
            node.setLeft(pre);
            //修改当前节点的左指针的类型，指向前驱节点
            node.setLeftType(1);//8结点的leftType=1（相当于前驱节点，空）
        }

        //处理后继节点
        if (pre != null && pre.getRight()==null){//如果本身有值就不能处理
         //让前驱结点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //!!!每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        // (三) 再线索化右子树
        threadedNodes(node.getRight());
    }


    //遍历后序线索化二叉树的方法
    public void threadedList2() {
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;

        while (node.getLeftType() == 0 && node != null) {
            node = node.getLeft();
        }
//        pre=null;
        while (node != null) {
            //如果当前节点的右指针指向的是后继节点，就一直输出
            if (node.getRightType() == 1) {
                System.out.println(node);
                pre = node;
                node = node.getRight();
            } else {
                if (node.getRight() == pre) {
                    System.out.println(node);
                    if (node == root) {
                        return;
                    }
                    pre = node;
                    node = node.getParent();
                } else {
                    node = node.getRight();
                    while (node != null && node.getLeftType() == 0) {
                        node = node.getLeft();
                    }
                }
            }
        }
    }
    public void threadedList22() {
        HeroNode node = root;
        pre = null; //保存上一次访问的节点
        while (node != null)
        {
            //找最左边的节点
            while (node.getLeftType() == 0 && node.getLeft() != pre) //防止陷入死循环
            {
                node = node.getLeft();
            } //跳出循环的条件：node为最左边的节点

            //访问节点的后继
            while ( node.getRightType() == 1) //
            {
                System.out.println(node);
                pre = node; //per记录已经访问过的节点
                node = node.getRight();
            }//跳出循环的条件：node为空（即左单支情况） 节点有右子树，节点为根节点

            //跳出循环，判断是否为根节点
            if (node == root && node.getRight() == pre)
            {
                System.out.println(node);
                return;
            }
            //不是根节点，访问当前节点的双亲节点
            while (  node.getRight() == pre) // 注意 右单支情况
            {
                System.out.println(node);
                pre = node;
                node = node.getParent();
            }

            // 判断根节点是否有右子树
            if ( node.getLeftType() == 0)
            {
                node = node.getRight();
            }
        }
    }

    //编写对二叉树进行后序线索化的方法
    /**
     *
     * @param node 就是当前需要线索化的结点
     */
    public void threadedNodes2(HeroNode node){

        //如果 node==null，不能线索化
        if (node==null){
            return;
        }
        // (一) 先线索化左子树
        threadedNodes2(node.getLeft());

        // (二) 再线索化右子树
        threadedNodes2(node.getRight());
        // (三) 线索化当前结点[有点难度]
        //处理当前节点的前驱节点
        //以8结点的left == null，8结点的leftType=1（相当于前驱节点，空）
        if (node.getLeft()==null){
            //让当前结点的左指针指向前驱节点(pre)
            node.setLeft(pre);
            //修改当前节点的左指针的类型，指向前驱节点
            node.setLeftType(1);//8结点的leftType=1（相当于前驱节点，空）
        }
        //处理后继节点
        if (pre != null && pre.getRight()==null){//如果本身有值就不能处理
            //让前驱结点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //!!!每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
    }

    //删除节点
    public void delNode(int no){
        if (root!=null) {
            //如果只有一个root节点，这里立即判断root是不是就是要删除节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树，不能删除");
        }
    }

    //前序遍历
    public void preOrder(){
        if (this.root!=null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.root!=null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.root!=null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //前序遍历
    public HeroNode preOrderSearch(int no){
        if (root!=null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序遍历
    public HeroNode infixOrderSearch(int no){
        if (root!=null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后序遍历
    public HeroNode postOrderSearch(int no){
        if (root!=null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }
}

//创建HeroNode
class HeroNode {
    private int no;
    private String name;
    private HeroNode left; //默认null
    private HeroNode right;//默认null
    private HeroNode parent;
    //说明
    //1. 如果leftType == 0 表示指向的是左子树，如果1则表示指向前驱节点
    //2. 如果rightType == 0 表示指向的是右子树，如果1表示指向后继节点
    private int LeftType;
    private int RightType;

    public HeroNode getParent() {
        return parent;
    }

    public void setParent(HeroNode parent) {
        this.parent = parent;
    }

    public int getLeftType() {
        return LeftType;
    }

    public void setLeftType(int leftType) {
        LeftType = leftType;
    }

    public int getRightType() {
        return RightType;
    }

    public void setRightType(int rightType) {
        RightType = rightType;
    }

    public HeroNode(int no, String name) {
        super();
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    //递归删除节点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no) {
        //思路
        /**
         * 1、单向，判断当前节点的子节点是否需要删除节点，而不能去判断当前这个节点是不是需要删除节点
         * 2、如果当前节点左子节点不为空，并且左子节点就是要删除节点，就将this.left=null,并且就返回（结束递归删除）
         * 3、如果当前节点右子节点不为空，并且右子节点就是要删除节点，就将this.right=null,并且就返回（结束递归删除）
         * 4、如果2和3没有删除，那么我们就需要向左子树进行递归删除
         * 5、如果第4步也没有删除节点，则应该向右子树进行删除
         */
        //2、如果当前节点左子节点不为空，并且左子节点就是要删除节点，就将this.left=null,并且就返回（结束递归删除）
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //3、如果当前节点右子节点不为空，并且右子节点就是要删除节点，就将this.right=null,并且就返回（结束递归删除）
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //4、我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //5、应该向右子树进行删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序
    public void infixOrder() {
        //递归左子树终须遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序
    public void postOrder() {
        //递归左子树终须遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找

    /**
     * @param no 查找no
     * @return 如果找到就返回该node，如果没有找到就返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        //1.则判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) { //说明我们左子树找到
            return resNode;
        }
        //1.左递归前序查找，找到结点，则返回，否继续判断
        //2.当前的节点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历");
        //如果找到，则返回，如果没有找到，就和当前节点比较，如果是则返回当前节点
        if (this.no == no) {
            return this;
        }
        //否则继续进行有递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) { //说明在左子树找到
            return resNode;
        }
        //如果右子树没有找到，则向右子树递归进行后序遍历查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序遍历");
        //如果左右子树都没有找到，就比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}