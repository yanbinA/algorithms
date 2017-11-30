package com.yanbin.tree.node;

/**
 * 二叉树 节点对象
 * @author yanbin
 * @date 2017/11/30 11:44
 */
public class Node<T> {

    private T data;
    private int key;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node() {

    }

    public Node(T data, int key) {
        this.data = data;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", key=" + key +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
