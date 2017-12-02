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

        while (true) {
            System.out.print("Enter first letter of show('quit'),\ninsert, find, delete:");
            String choice = getString();
            switch (choice) {
                
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
