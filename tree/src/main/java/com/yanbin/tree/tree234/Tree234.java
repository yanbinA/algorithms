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
     * 删除指定key：
     * 方法一：
     *      1.如果删除的key不在叶节点，用后继key替代，并在后继key所在子树中删除后继key
     *      2.如果删除的key在叶节点：
     *          2.1如果该节点不是2节点，直接删除，结束；
     *          2.2如果该节点是2节点：
     *              2.2.1如果该节点的兄弟节点不是2节点，则父节点的key下移到该节点，兄弟节点中的key上移到父节点；
     *              2.2.2如果该节点的兄弟节点也是2节点，且该节点的父节点不是2节点，则父节点中的key与兄弟节点合并(父节点中的key下移到兄弟节点)
     *              2.2.3如果该节点的兄弟节点也是2节点，且该节点的父节点也是2节点，则父节点与兄弟节点合并形成一个3节点并看成当前节点，重复执行2.2.1和2.2.2
     *预合并删除：同插入，在下行过程中合并节点
     *      1.当前节点为2节点，且兄弟节点为2节点时合并节点(父节点中的key与兄弟节点还有自身合并)
     *      2.如果当前节点为2节点，但兄弟节点不是2节点时，将父节点中的key移到当前节点，兄弟节点中的key移到父节点；
     *这样可以保证找到删除key所在节点一定不是2节点，可以直接删除
     *
     * @param key   删除的key
     */
    public void delete(int key) {
        Node current = this.root;
        if (current != root && current.isTwoNode()) {
            //合并节点
            merge(current);
        }
        
        
        int itemIndex = current.findItem(key);
        if (itemIndex != -1) {
            
        }
    }

    /**
     * 当前节点在父节点的index位置，
     * 1.当前节点为2节点，且兄弟节点为2节点时合并节点(父节点中的key与兄弟节点还有自身合并)
     *      1.1兄弟节点是当前节点的右节点(index+1),父节点中的key值位于index
     *          1.1.1将父节点中的key插入当前节点
     *          1.1.2将兄弟节点中的key(只有一个)插入当前节点
     *          1.1.3将兄弟节点的两个子节点(0,1)分别放入当前节点的(2,3)
     *          1.1.4将父节点中的key值和和key值的右子节(兄弟节点)点从父节点中移除
     *      1.2兄弟节点是当前节点的左节点(index-1 ),父节点中的key值位于index-1
     *          1.2.1将key插入兄弟节点
     *          1.2.2将当前节点中的key(只有一个)插入当前节点
     *          1.2.3将当前节点的两个子节点(0,1)分别放入当前节点的(2,3)
     *          1.1.4将父节点中的key值和和key值的右子节(当前节点)点从父节点中移除
     *      1.3：归纳1.1和1.2：
     *          1.3.1兄弟节点是当前节点的右节点(index+1),父节点中的key值位于index
     *          1.3.2兄弟节点是当前节点的左节点(index-1),父节点中的key值位于index-1
     *              1.3.2.1将兄弟节点和当前节点调换位置，当前节点=兄弟节点，兄弟节点=当前节点；
     *          1.3.3将父节点中的key插入当前节点
     *          1.3.4将兄弟节点中的key(只有一个)插入当前节点
     *          1.3.5将兄弟节点的两个子节点(0,1)分别放入当前节点的(2,3)
     *          1.3.6将父节点中的key值和和key值的右子节(兄弟节点)点从父节点中移除
     *
     * 2.当前节点为2节点，但兄弟节点不是2节点时，将父节点中的key移到当前节点，兄弟节点中的key移到父节点；
     *      2.1兄弟节点是当前节点的右节点(index+1),父节点中的key值位于index，兄弟节点的key取该节点最小值
     *          2.1.1将父节点中的key插入当前节点
     *          2.1.2将兄弟节点中的key代替父节点中原先的key
     *          2.1.3将兄弟节点的子节点(0，最左)放入当前节点的(2)
     *          2.1.4将兄弟节点的其他值往前(往左)移一位，子节点往前(往左)移一位，末位置null
     *      2.2兄弟节点是当前节点的左节点(index-1 ),父节点中的key值位于index-1，兄弟节点的key取该节点最大值
     *          2.1.1将父节点中的key插入当前节点
     *          2.1.2将兄弟节点中的key代替父节点中原先的key，并移除兄弟节点中的key
     *          2.1.3将当前节点的子节点往后(往右)移一位，
     *          2.1.4将兄弟节点的子节点(最右)放入当前节点的(0)
     *          2.1.5将兄弟节点的子节点(最右)移除
     * @param current 当前节点
     */
    private void merge(Node current) {

        Node parent = current.getParent();
        Node brother = null;
        DataItem parentItem, brotherItem, currentItem = current.getItemArray()[0];
        //current子节点在父节点parent的位置
        int i = 0;
        for (; i < parent.getNumItems(); i++) {
            if (parent.getItemArray()[i].getKey() > currentItem.getKey()) {
                //i可能为0，1，2，3
                break;
            }
        }
        //得到当前2节点的前兄弟节点（兄弟节点的brotherKey最小值），如果没有前兄弟节点则用后兄弟节点(brotherItem=最大值)
        if (i == parent.getNumItems()) {
            //说明没有前兄弟节点,用后兄弟节点
            brother = parent.getChildNode(i - 1);
            brotherItem = brother.getItemArray()[brother.getNumItems()];
            parentItem = parent.getItemArray()[i - 1];
            if (brother.isTwoNode()) {

            }
        } else {
            brother = parent.getChildNode(i + 1);
            brotherItem = brother.getItemArray()[0];
            parentItem = parent.getItemArray()[i];
        }


    }

    /**
     * 得到当前2节点的一个兄弟节点
     *
     * @param current   当前节点
     * @return 返回当前节点的前兄弟节点，如果没有前兄弟节点则返回后兄弟节点
     */
    private Node getBrotherNode(Node current) {
        Node parent = current.getParent();

        return null;
    }

    /**
     * 获得当前节点的父节点中key的index
     * @param current   当前节点
     * @return  父节点key的index(该key值为取当前节点和兄弟节点的key)
     */
    private int getParentIndex(Node current) {
        return 0;
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
