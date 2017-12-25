package com.yanbin.hash;

/**
 * 开放地址 哈希表
 * @author yanbin
 * @date 2017/12/25 15:51
 */
public interface Hash<T> {

    T find(int hashCode);

    void insert(T item);

    T delete(int hashCode);

    void displayTable();
}
