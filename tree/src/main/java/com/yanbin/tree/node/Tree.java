package com.yanbin.tree.node;

import com.sun.xml.internal.ws.util.Pool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 二叉树树结构,左节点小于父节点，
 * @author yanbin
 * @date 2017/11/30 15:16
 */
public class Tree<T> {
    /**
     * 根节点
     */
    private Node<T> root;
    private AtomicInteger size = new AtomicInteger(0);

    public Tree(){}

    public Tree( Node<T> root) {
        this.root = root;
        this.size.incrementAndGet();
    }

    /**
     *  根据关键字查找
     */
    public Node<T> find(int key) {
        if (root == null) {
            return null;
        }
        Node<T> current = root;
        while (current.getKey() != key) {
            //查找左节点
            if (current.getKey() > key) {
                current = current.getLeftChild();
            } else {
                //查找右节点
                current = current.getRightChild();
            }
            //找不到节点
            if (current == null) {
                return null;
            }

        }
        return current;
    }

    /**
     * 插入节点
     * 左子节点<父节点<=右子节点
     */
    public int insert(Node<T> node) {
        if (root == null) {
            root = node;
            return size.incrementAndGet();
        }

        Node<T> current = root;

        while (true) {
            if (current.getKey() > node.getKey()) {
                //右节点为null，直接插入；不为空，移动current指向右子节点
                if (current.getLeftChild() == null) {
                    current.setLeftChild(node);
                    size.incrementAndGet();
                    break;
                } else {
                    current = current.getLeftChild();
                }
            } else {
                if (current.getRightChild() == null) {
                    current.setRightChild(node);
                    size.incrementAndGet();
                    break;
                } else {
                    current = current.getRightChild();
                }

            }
        }
        return size.get();
    }

    /**
     * 根据关键值删除
     */
    public Node<T> delete(int key) {
        if (root == null) {
            return null;
        }
        Node<T> current = root;
        Node<T> parent = root;
        //判断删除节点是父节点的左子节点
        boolean isLeftChild = true;
        while (current.getKey() != key) {
            //记录父节点
            parent = current;
            //查找左节点
            if (current.getKey() > key) {
                current = current.getLeftChild();
                isLeftChild = true;
            } else {
                //查找右节点
                current = current.getRightChild();
                isLeftChild = false;
            }
            //找不到节点
            if (current == null) {
                return null;
            }
        }
        deleteNode(parent, current, isLeftChild);
        return null;
    }

    /**
     * 删除节点
     * 如果 节点为叶节点 直接移出树
     * 如果 节点只有一个子节点，将子节点 移到当前节点位置
     * 如果 节点有两个子节点，将左子节点 复制到 当前节点位置，(删除左子节点);设置右子节点为新节点的右节点，
     * @param parent    指定删除节点的父节点
     * @param current   指定删除的节点
     * @param isLeftChild   current和parent的关系
     */
    private void deleteNode(Node<T> parent, Node<T> current, boolean isLeftChild) {
        //如果 节点为叶节点 直接移出树

        if (current.getRightChild() == null && current.getLeftChild() == null) {
            if (isLeftChild) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }

        } else if (current.getLeftChild() == null) {
            // 如果 节点只有一个子节点，将子节点 移到当前节点位置
            if (isLeftChild) {
                parent.setLeftChild(current.getRightChild());
            } else {
                parent.setRightChild(current.getRightChild());
            }
        } else if (current.getRightChild() == null) {
            // 如果 节点只有一个子节点，将子节点 移到当前节点位置
            if (isLeftChild) {
                parent.setLeftChild(current.getLeftChild());
            } else {
                parent.setRightChild(current.getLeftChild());
            }
        } else {
            // 如果 节点有两个子节点，将左子节点 复制到 当前节点位置，(删除左子节点);设置右子节点为新节点的右节点，
            if (isLeftChild) {
                parent.setLeftChild(current.getLeftChild());
            } else {
                parent.setRightChild(current.getLeftChild());
            }
            deleteNode(current.getLeftChild(), current.getLeftChild(), true);
            current.getLeftChild().setRightChild(current.getRightChild());
        }



    }

    public int getSize() {
        return size.get();
    }
}
