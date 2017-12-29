package com.yanbin.heap;

/**
 * @author yanbin
 * @date 2017/12/29 11:51
 */
public class Node {

    private int iData;

    public Node(int key) {
        this.iData = key;
    }

    public int getiData() {
        return iData;
    }

    public void setiData(int iData) {
        this.iData = iData;
    }
}
