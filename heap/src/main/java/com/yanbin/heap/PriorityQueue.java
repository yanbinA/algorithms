package com.yanbin.heap;

/**
 * @author yanbin
 * @date 2017/12/29 11:48
 */
public class PriorityQueue {

    private Heap theHeap;

    public void insert(Node node) {
        theHeap.insert(node);
    }

    public Node remove() {
        return theHeap.remove();
    }
}
