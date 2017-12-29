package com.yanbin.hash.HashTable;

import com.yanbin.hash.Hash;
import com.yanbin.hash.model.DataItem;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * @author yanbin
 * @date 2017/12/28 16:09
 */
public class LinkHash implements Hash<LinkedList<DataItem>>{

    private LinkedList<DataItem>[] array;
    private int capacity;

    public LinkHash(int capacity) {
        this.capacity = capacity;
        this.array = new LinkedList[capacity];
    }

    @Override
    public LinkedList<DataItem> find(int hashCode) {
        return null;
    }

    @Override
    public void insert(LinkedList<DataItem> item) {

    }

    @Override
    public LinkedList<DataItem> delete(int hashCode) {
        return null;
    }

    @Override
    public void displayTable() {

    }
}
