package com.yanbin.tree.tree234;

import com.yanbin.tree.searchTree.Node;
import com.yanbin.tree.searchTree.Tree;

import java.util.Scanner;

/**
 * @author yanbin
 * @date 2017/12/9 13:42
 */
public class Test234 {
    public static void main(String[] args) {
        boolean isRunner = true;
        Tree234 tree = new Tree234();
        while (isRunner) {
            System.out.print("Enter first letter of show('quit'),\ninsert, find, delete:\n");
            String choice = getString();
            switch (choice) {
                case "i":
                    System.out.print("Enter value to insert:");
                    int value = getInt();
                    tree.insert(value);
                    tree.displayTree();
                    break;
                case "f":
                    System.out.print("Enter key to find:");
                    DataItem dataItem = tree.find(getInt());
                    System.out.println("found:" + dataItem);
                    break;
                case "quit":
                    isRunner = false;
                    System.out.println("Stop!!!");
                    break;
                default:
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
