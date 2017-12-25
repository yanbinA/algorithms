package com.yanbin.hash.model;

/**
 * @author yanbin
 * @date 2017/12/25 16:42
 */
public class DataItem {

    private int hashCode;

    public DataItem(int hashCode) {
        this.hashCode = hashCode;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }
}
