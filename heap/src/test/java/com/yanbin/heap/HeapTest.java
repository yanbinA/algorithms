package com.yanbin.heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author yanbin
 * @date 2017/12/29 17:17
 */
public class HeapTest {

    private static int aChar;
    private static int anInt;
    private static String string;

    public static void main(String[] args) throws Exception{
        int value, value2;
        Heap theHeap = new Heap(31);
        boolean success;
        theHeap.insert(new Node(70));
        theHeap.insert(new Node(100));
        theHeap.insert(new Node(80));
        theHeap.insert(new Node(90));
        theHeap.insert(new Node(60));
        theHeap.insert(new Node(50));
        while (true) {
            System.out.print("Enter first letter of show, insert, remove, change:");
            int choice = getChar();
            switch (choice) {
                case 's':
                    theHeap.displayHeap();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    theHeap.insert(new Node(value));
                    break;
                case 'r':
                    if (!theHeap.isEmpty()) {
                        theHeap.remove();
                    } else {
                        System.out.print("Can't remove;\n");
                    }
                    break;
                case 'c':
                    System.out.print("Enter current index of item: ");
                    value = getInt();
                    System.out.print("Enter new key: ");
                    value2 = getInt();
                    success = theHeap.change(value, value2);
                    if (!success) {
                        System.out.print("Invalid index\n");
                    }
                    break;
                default:
                    System.out.print("\nInvalid index\n");
            }
        }
    }


    public static int getChar() throws Exception{
        String s = getString();

        return s.charAt(0);
    }

    public static int getInt() throws Exception{
        String s = getString();
        return Integer.valueOf(s);
    }

    public static String getString() throws Exception{
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        return br.readLine();
    }
}
