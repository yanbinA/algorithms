package com.yanbin.tree.tree234;

import com.yanbin.algorithms.structure.Stacks;
import com.yanbin.tree.searchTree.Tree;

import javax.lang.model.element.VariableElement;

/**
 * 2-3-4树
 * @author yanbin
 * @date 2017/12/7 15:21
 */
public class Tree234 {

    private Node root;

    public Tree234() {
        this.root = new Node();
    }

    /**
     * 根据指定key查找数据项
     * @param key   指定key
     * @return  查找道德数据项，null:数据项不存在
     */
    public DataItem find(int key) {
        Node current = root;

        while(true) {
            int childNum = current.findItem(key);
            if (childNum != -1) {
                //在当前节点找到该key
                return current.getItemArray()[childNum];
            } else {
                //当前节点没有该key
                if (!current.isLeaf()) {
                    current = current.getNextChild(key);
                } else {
                    return null;
                }
            }
        }
    }

    /**
     * 插入新元素
     * 1.找到合适的叶节点
     * 2.下行查找过程，遇到满节点分裂该节点，继续查找
     *
     * @param key 新元素值
     */
    public void insert(int key) {
        DataItem item = new DataItem(key);
        Node current = root;

        while (true) {
            if (current.isFull()) {
                split(current);
                current = current.getParent();
                current = current.getNextChild(key);
            }
            if (current.isLeaf()) {
                break;
            } else {
                current = current.getNextChild(key);
            }
        }
        current.insertItem(item);
    }

    /**
     * 分裂指定节点，可以肯定该节点是满节点，存在A B C三个数据项
     *  1.该节点为root时，创建一个节点(nodeParent)，该节点是node的父节点,并将root指向nodeParent
     *  2.创建一个节点(newNode),该节点是node的兄弟节点，
     *  3.将C插入newNode, B插入node的父节点
     *  4.将node的右端的两个子节点移到newNode中
     * @param node 指定分裂的节点
     */
    public void split(Node node) {
        DataItem itemC = node.removeItem();
        DataItem itemB = node.removeItem();
        Node child3 = node.disConnectChild(3);
        Node child2 = node.disConnectChild(2);
        if (root == node) {
            //该节点为root时，创建一个节点(nodeParent)，该节点是node的父节点,并将root指向nodeParent
            Node nodeParent = new Node();
            node.setParent(nodeParent);
            root = nodeParent;
            nodeParent.connectChild(0, node);
        }
        //创建一个节点(newNode),该节点是node的兄弟节点，
        Node newNode = new Node();
        newNode.setParent(node.getParent());
        // 将C插入newNode,
        newNode.insertItem(itemC);
        // 将node的右端的两个子节点移到newNode中
        newNode.connectChild(0, child2);
        newNode.connectChild(1, child3);
        // B插入node的父节点
        int insertIndex = node.getParent().insertItem(itemB);
        for (int i = node.getParent().getNumItems() - 1; i > insertIndex; i--) {
            node.getParent().connectChild(i + 1, node.getParent().disConnectChild(i));
        }
        node.getParent().connectChild(insertIndex + 1, newNode);
    }

    /**
     * 展示树
     */
    public void displayTree() {
        Stacks<Node> globalStack  = new Stacks<>();
        globalStack.push(root);
        //定义空白位置，
        int nBlanks = 64;
        boolean isRowEmpty = false;
        System.out.println("-------------------------------------------");

        while (!isRowEmpty) {
            Stacks<Node> localStack  = new Stacks<>();
            isRowEmpty = true;
            //打印空白
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }
            //打印一层
            while (!globalStack.isEmpty()) {
                Node temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.disPlay());
                    //将子节点放入本地栈
                    localStack.push(temp.getChildNodes()[0]);
                    localStack.push(temp.getChildNodes()[1]);
                    localStack.push(temp.getChildNodes()[2]);
                    localStack.push(temp.getChildNodes()[3]);
                    if (temp.getChildNodes()[0] != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("-");
                }
                for (int i = 0; i < nBlanks -1; i++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            nBlanks /= 2;
            //将本地栈放入全局栈
            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println("----------------------------");
    }


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
