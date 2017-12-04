package com.yanbin.tree.exercise;

import com.yanbin.tree.searchTree.Node;
import com.yanbin.tree.searchTree.Tree;

import java.util.Scanner;

/**
 * 输入字母字符串，将每个字母作为节点创建满树：除了底层最右边可能为空，其他层节点完全满的树；
 * 字母要从上到下从左到右有序
 * @author yanbin
 * @date 2017/12/4 14:07
 */
public class Exercise2 {

    public static void main(String[] args) {
        System.out.print("Enter String:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.next();
            if ("quit".equalsIgnoreCase(line)) {
                break;
            }

            Node[] nodes = new Node[line.length()];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new Node<Character>(line.charAt(i), line.charAt(i));
            }
            //childNode(0, nodes);
            for (int i = 0; i < nodes.length / 2; i++) {
                if (nodes.length > i * 2 + 1) {
                    nodes[i].setLeftChild(nodes[i * 2 + 1]);
                }
                if (nodes.length > i * 2 + 2) {
                    nodes[i].setRightChild(nodes[i * 2 + 2]);
                }
            }
            Tree<Character> tree = new Tree<Character>(nodes[0]);
            tree.displayTree();
            scanner = new Scanner(System.in);
        }


    }


    /**
     * 利用数组表示树，某节点的下标为n，其左右子节点的下标分别为2n+1,2n+2
     * @param index 节点下标
     * @param arr 数组
     */
    public static void childNode(int index, Node<Character>[] arr) {
        if (arr.length <= index) {
            return;
        }
        if (arr.length > index * 2 + 1) {
            arr[index].setLeftChild(arr[index * 2 + 1]);
            childNode(index * 2 + 1, arr);
        }
        if (arr.length > index * 2 + 2) {
            arr[index].setRightChild(arr[index * 2 + 2]);

            childNode(index * 2 + 2, arr);
        }


    }

}
