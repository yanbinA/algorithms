package com.yanbin.tree.tree234;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * @author yanbin
 * @date 2017/12/12 17:06
 */
public class Tree234Test {
    public static void main(String[] args){
        Logger logger = Logger.getLogger("Tree234Test");
        int[] arr = new int[100];
        Tree234 tree = new Tree234();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
            tree.insert(i + 1);
        }
        tree.displayTree();

        TreeSet<Object> objects = new TreeSet<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if ("exit".equalsIgnoreCase(line)) {
                break;
            } else {
                Integer key;
                try {

                    key = Integer.valueOf(line);
                } catch (Exception e) {
                    e.printStackTrace();
                    key = 0;
                }

                logger.info("delete :" + key);
                tree.delete(key);
                tree.displayTree();
            }


        }
    }

}
