package com.yanbin.tree;

import com.yanbin.tree.node.Node;
import com.yanbin.tree.node.Tree;

import java.util.Scanner;

/**
 * @author yanbin
 * @date 2017/11/30 11:53
 */
public class Test {

    public static void main(String[] args) {
        boolean isRunner = true;
        Tree<Integer> tree = new Tree<>();
        while (isRunner) {
            System.out.print("Enter first letter of show('quit'),\ninsert, find, delete:\n");
            String choice = getString();
            switch (choice) {
                case "i":
                    System.out.print("Enter value to insert:");
                    int value = getInt();
                    tree.insert(new Node<>(value, value));
                    tree.displayTree();
                    break;
                case "f":
                    System.out.print("Enter key to find:");
                    Node<Integer> node = tree.find(getInt());
                    System.out.println("found:" + node);
                    break;
                case "d":
                    System.out.print("Enter key to delete:");
                    Node<Integer> delete = tree.delete(getInt());
                    System.out.println("delete:" + delete);
                    tree.displayTree();
                    break;
                case "quit":
                    isRunner = false;
                    System.out.println("Stop!!!");
                    break;
            }
        }



    }

    public static String getString(){
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line.trim();
    }

    public static int getInt() {
        return Integer.parseInt(getString());
    }
}
