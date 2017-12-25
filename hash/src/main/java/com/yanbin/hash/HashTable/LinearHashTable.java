package com.yanbin.hash.HashTable;

import com.yanbin.hash.Hash;
import com.yanbin.hash.model.DataItem;

/**
 *  线性探测 添加新元素
 *      添加新元素时，如果要插入的位置已经被占，则数组下标一值递增，知道找到空位
 * @author yanbin
 * @date 2017/12/25 16:27
 */
public class LinearHashTable implements Hash<DataItem>{

    private DataItem[] array;
    private int capacity;
    private DataItem nonItem;

    public LinearHashTable(int initCapacity) {
        this.capacity = initCapacity;
        this.array = new DataItem[this.capacity];
    }

    @Override
    public DataItem find(int hashCode) {
        int haveVal = hashCode % capacity;

        while (array[haveVal] != null) {
            if (array[haveVal].getHashCode() == hashCode) {
                return array[haveVal];
            }
            haveVal++;
            haveVal = haveVal % capacity;
        }
        return null;
    }

    @Override
    public void insert(DataItem item) {
        int hashVal = item.getHashCode() % capacity;
        while (array[hashVal] != null && array[hashVal].getHashCode() != -1) {
            hashVal++;
            hashVal %= capacity;
        }
        array[hashVal] = item;

    }

    @Override
    public DataItem delete(int hashCode) {
        int hashVal = hashCode % capacity;
        while (array[hashVal] != null) {
            if (array[hashVal].getHashCode() == hashCode) {
                DataItem delItem = array[hashVal];
                array[hashVal] = new DataItem(-1);
                return delItem;
            }
            hashVal++;
            hashVal = hashVal % capacity;
        }
        return null;
    }

    @Override
    public void displayTable() {
        StringBuilder sb = new StringBuilder();
        for (DataItem item : array) {
            if (item != null) {
                sb.append(item.getHashCode());
            } else {
                sb.append("**");
            }
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}
