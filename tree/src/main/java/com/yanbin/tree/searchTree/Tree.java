package com.yanbin.tree.searchTree;

import com.yanbin.algorithms.structure.Stacks;

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
     * 如果 节点有两个子节点，寻找后继节点：在左子树寻找最大值的节点代替被删除的节点，或者在右子树中寻找最小值节点代替被删除节点
     * @param parent    指定删除节点的父节点
     * @param current   指定删除的节点
     * @param isLeftChild   current和parent的关系
     */
    private void deleteNode(Node<T> parent, Node<T> current, boolean isLeftChild) {
        //如果 节点为叶节点 直接移出树

        if (current.getRightChild() == null && current.getLeftChild() == null) {
            if (root == current) {
                root = null;
            } else if (isLeftChild) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }

        } else if (current.getLeftChild() == null) {
            // 如果 节点只有一个子节点，将子节点 移到当前节点位置
            if (root == current) {
                root = current.getRightChild();
            } else if (isLeftChild) {
                parent.setLeftChild(current.getRightChild());
            } else {
                parent.setRightChild(current.getRightChild());
            }
        } else if (current.getRightChild() == null) {
            // 如果 节点只有一个子节点，将子节点 移到当前节点位置
            if (root == current) {
                root = current.getLeftChild();
            } else if (isLeftChild) {
                parent.setLeftChild(current.getLeftChild());
            } else {
                parent.setRightChild(current.getLeftChild());
            }
        } else {
            // 寻找后继节点：右子树中寻找最小值节点代替被删除节点
            Node<T> successorParent = current.getRightChild();
            Node<T> successor = current.getRightChild();
            while (successor.getLeftChild() != null) {
                successorParent = successor;
                successor = successor.getLeftChild();
            }
            //如果后继节点不是删除节点的右子节点
            if (successor != current.getRightChild()) {
                successorParent.setLeftChild(successor.getRightChild());

                successor.setRightChild(current.getRightChild());
            }
            successor.setLeftChild(current.getLeftChild());
            if (root == current) {
                root = successor;
            } else if (isLeftChild) {
                parent.setLeftChild(successor);
            } else {
                parent.setRightChild(successor);
            }

        }



    }

    public int getSize() {
        return size.get();
    }

    /**
     * 中序遍历树：先左子节点 节点本身 右子节点
     * 后序遍历：先左子节点 右子节点 节点本身
     * 前序遍历：先节点本身 左子节点 右子节点
     * 这里采用中序遍历
     */
    public void display() {
        inOrder(root);
    }

    private void inOrder(Node<T> root) {

        if (root != null) {
            inOrder(root.getLeftChild());
            System.out.println(root);
            inOrder(root.getRightChild());
        }
    }

    public void show() {
        System.out.println(root);
        showTree(root);
    }

    private void showTree(Node<T> root) {
        if (root != null) {
            System.out.print("|" + root.getLeftChild() + "-" + root.getRightChild() + "|");
            showTree(root.getLeftChild());
            showTree(root.getRightChild());

        }
    }

    /**
     * 展示树
     */
    public void displayTree() {
        Stacks<Node<T>> globalStack  = new Stacks<>();
        globalStack.push(root);
        //定义空白位置，
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("-------------------------------------------");

        while (!isRowEmpty) {
            Stacks<Node<T>> localStack  = new Stacks<>();
            isRowEmpty = true;
            //打印空白
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }
            //打印一层
            while (!globalStack.isEmpty()) {
                Node<T> temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getData());
                    //将子节点放入本地栈
                    localStack.push(temp.getLeftChild());
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null || temp.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("-");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int i = 0; i < nBlanks * 2 -1; i++) {
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
        System.out.println("-------------------------------------------");
    }
}
