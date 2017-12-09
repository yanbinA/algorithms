package com.yanbin.tree.tree234;

import java.util.Arrays;

/**
 * 2-3-4树的节点对象
 * @author yanbin
 * @date 2017/12/7 15:21
 */
public class Node {
    private static final int ORDER = 4;

    /**
     * 子节点的引用数组
     */
    private Node[] childNodes = new Node[ORDER];
    /**
     * 节点的数据项数组，需要保持数组有序，key值从小到大排序
     */
    private DataItem[] itemArray = new DataItem[ORDER - 1];
    /**
     * 父节点引用
     */
    private Node parent;
    /**
     * 节点数据项个数
     */
    private int numItems;

    public Node(){

    }

    /**
     * 插入数据项，
     * @param item  待插入的数据项
     * @return  插入位置
     */
    public int insertItem(DataItem item) {
        //itemArray从后往前遍历，找到合适插入点
        int i = ORDER - 2;
        for (; i >= 0; i--) {
            //跳过空元素
            if (itemArray[i] == null) {
                continue;
            }
            DataItem temp = itemArray[i];
            if (item.getKey() >= temp.getKey()) {
                //找到插入点跳出循环
                break;
            } else {
                //将元素后移
                itemArray[i + 1] = temp;
            }

        }
        itemArray[++i] = item;
        numItems++;
        return i;
    }

    /**
     * 查找数据项
     * @param key   指定key
     * @return  数据项在数组itemArray中的index,不存在返回 -1
     */
    public int findItem(int key) {

        for (int i = 0; i < ORDER - 1; i++) {
            if (itemArray[i] == null) {
                break;
            } else if (itemArray[i].getKey() == key){
                return i;
            }
        }
        return -1;
    }

    /**
     *  移除itemArray数组中最后的数据项
     * @return  被移除的数据项
     */
    public DataItem removeItem() {
        DataItem dataItem = itemArray[--numItems];
        itemArray[numItems] = null;
        return dataItem;
    }

    /**
     * 关联子节点，
     * @param childNum 子节点在childNodes数组中的index
     * @param node  子节点
     */
    public void connectChild(int childNum, Node node) {
        childNodes[childNum] = node;
        //设置父节点
        if (node != null) {
            node.setParent(this);
        }
    }

    /**
     * 取消关联
     * @param childNum 取消关联的子节点在childNodes中的index
     * @return 被取消的子节点
     */
    public Node disConnectChild(int childNum) {
        Node childNode = childNodes[childNum];
        childNodes[childNum] = null;
        return childNode;
    }

    /**
     * 当前节点是否已满
     * @return  true:已满；false：未满
     */
    public boolean isFull() {
        return numItems == ORDER - 1;
    }

    /**
     * 当前节点是否叶节点
     * @return true:叶节点;false:非叶节点
     */
    public boolean isLeaf() {
        return childNodes[0] == null;
    }

    /**
     * 通过key查找下一个子节点，当前节点找不到该key
     * @param key   指定的key
     * @return  值范围包含key的子节点
     */
    public Node getNextChild(int key) {
        int numItems = this.getNumItems();

        for (int i = 0; i < numItems; i++) {
            if (itemArray[i].getKey() > key) {
                return childNodes[i];
            }
        }
        return childNodes[numItems];
    }


    public Node getChildNode(int childNum) {
        return childNodes[childNum];
    }

    public Node[] getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Node[] childNodes) {
        this.childNodes = childNodes;
    }

    public DataItem[] getItemArray() {
        return itemArray;
    }

    public void setItemArray(DataItem[] itemArray) {
        this.itemArray = itemArray;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public String disPlay() {
        StringBuilder sb = new StringBuilder();
        for (DataItem item : itemArray) {
            sb.append(item != null ? item.toString() : "");
        }
        sb.append("/");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Node{" +
                "childNodes=" + Arrays.toString(childNodes) +
                ", itemArray=" + Arrays.toString(itemArray) +
                ", numItems=" + numItems +
                '}';
    }
}
