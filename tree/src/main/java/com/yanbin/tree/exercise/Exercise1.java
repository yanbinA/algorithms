package com.yanbin.tree.exercise;

import com.yanbin.tree.searchTree.Node;
import com.yanbin.tree.searchTree.Tree;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 用户输入字母的字符串，建立二叉树；每个字母在各自节点中显示。
 *  要求每个包含字母的节点是叶子节点，父节点可以用非字母标志如“+”。
 *  保证每个父节点都恰好有两个子节点；
 *  要求平衡树；
 *
 *  思路：
 *      建立一个树的数组，输入的每个字母作为节点，每个节点作为一棵树，节点就是根；
 *      假设有两个符合上述要求的树，将其和并成一个树：创建新节点为新树根，将两个树设置为左右子树
 *      给定一个树的数组，中间划分两端，假设前1/2和后1/2的元素已建立符合上述要求的树，将两树合并成一个树
 *      以此类推，1/4的数组创建树，直到子数组中只有一个元素
 * @author yanbin
 * @date 2017/12/4 10:57
 */
public class Exercise1 {

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
            Node root = merge(0, nodes.length - 1, nodes);
            Tree<Character> tree = new Tree<Character>(root);
            tree.displayTree();
            scanner = new Scanner(System.in);
        }


    }

    /**
     * 指定数组的范围创建树，返回树根节点
     * @param start 开始下标
     * @param end   结束下标
     * @return  树的根节点
     */
    public static Node<Character> merge(int start, int end, Node<Character>[] arr) {
        //只有一个元素
        if (start == end) {
            return arr[start];
        }
        int mid = (end + start) / 2;
        Node<Character> leftNode = merge(start, mid, arr);
        Node<Character> rightNode = merge(mid + 1, end, arr);
        Node<Character> root = new Node<>('+', '+');
        root.setLeftChild(leftNode);
        root.setRightChild(rightNode);
        return root;
    }

}
