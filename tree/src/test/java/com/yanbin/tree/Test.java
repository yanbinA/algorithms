package com.yanbin.tree;

import com.yanbin.tree.node.Node;
import com.yanbin.tree.node.Tree;

/**
 * @author yanbin
 * @date 2017/11/30 11:53
 */
public class Test {

    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        Node<Integer> node = new Node<>(0, 45);
        Node<Integer> node1 = new Node<>(1, 44);
        Node<Integer> node2 = new Node<>(2, 20);
        Node<Integer> node3 = new Node<>(3, 17);
        Node<Integer> node6 = new Node<>(6, 21);
        Node<Integer> node4 = new Node<>(4, 16);
        Node<Integer> node5 = new Node<>(5, 18);
        tree.insert(node);
        tree.insert(node1);
        tree.insert(node2);
        tree.insert(node3);
        tree.insert(node6);
        tree.insert(node4);
        tree.insert(node5);
        System.out.println(node);
        tree.delete(20);
        System.out.println("--------------------");
        System.out.println(node);
    }
}
