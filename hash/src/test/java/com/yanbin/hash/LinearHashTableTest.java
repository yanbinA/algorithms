package com.yanbin.hash;

import com.yanbin.hash.HashTable.LinearHashTable;
import com.yanbin.hash.model.DataItem;

/**
 * @author yanbin
 * @date 2017/12/25 17:17
 */
public class LinearHashTableTest {

    public static void main(String[] args) {

        LinearHashTable hashTable = new LinearHashTable(20);
        hashTable.insert(new DataItem(23));
        hashTable.insert(new DataItem(25));
        hashTable.insert(new DataItem(43));
        hashTable.insert(new DataItem(63));
        hashTable.insert(new DataItem(83));
        hashTable.insert(new DataItem(93));
        hashTable.displayTable();
        System.out.println(hashTable.find(23));
        hashTable.delete(23);
        hashTable.displayTable();

    }

}
