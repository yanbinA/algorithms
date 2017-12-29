package com.yanbin.heap;

import java.util.stream.Stream;

/**
 * 数据结构：堆
 * 用数组实现的完全二叉树，
 * 堆中每个节点的关键字都大于或等于其子节点的关键字
 * @author yanbin
 * @date 2017/12/29 11:46
 */
public class Heap {

    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int capacity) {
        this.maxSize = capacity;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    /**
     *  堆中插入节点
     *  直接放入数组末位
     *  向上检测：堆中每个节点的关键字都大于或等于其子节点的关键字
     * @param node 指定出入节点
     */
    public void insert(Node node) {
        checkBound();
        heapArray[currentSize] = node;
        //向上检测：堆中每个节点的关键字都大于或等于其子节点的关键字
        trickleUp(currentSize++);
    }

    /**
     * 向上检测：堆中每个节点的关键字都大于或等于其子节点的关键字
     * @param index 开始节点下标
     */
    private void trickleUp(int index) {
        //父节点下标
        int parent = (index -1 ) / 2;
        //开始节点
        Node bottom = heapArray[index];

        while (index > 0 && heapArray[parent].getiData() < bottom.getiData()) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (index - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    /**
     * 数组扩容
     */
    private void checkBound() {
        if (currentSize >= maxSize) {
            maxSize = maxSize + maxSize >> 2;
            Node[] newArray = new Node[maxSize];
            System.arraycopy(heapArray, 0, newArray, 0, currentSize);
            heapArray = newArray;
        }

    }

    /**
     * 移除堆顶节点,
     * 将堆尾元素替换堆顶，
     * 向下检测
     * @return  被移除的节点
     */
    public Node remove() {
        Node node = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return node;
    }

    /**
     * 向下检测：堆中每个节点的关键字都大于或等于其子节点的关键字
     * @param index 开始下标
     */
    private void trickleDown(int index) {
        //将开始关键值和左右节点较大的关键值比较，并覆盖
        Node top = heapArray[index];

        //子节点中较大值下标
        int maxChild = index;

        while (index < currentSize / 2) {
            //左节点下标
            int left = index * 2 + 1;
            //右节点下标
            int right = index * 2 + 2;
            if (left < currentSize && right < currentSize) {
                maxChild = heapArray[left].getiData() < heapArray[right].getiData() ? right : left;
            } else if (left < currentSize) {
                maxChild = left;
            } else if (right < currentSize) {
                maxChild = right;
            }
            if (heapArray[maxChild].getiData() > top.getiData() ) {
                heapArray[index] = heapArray[maxChild];
                index = maxChild;
            } else {
                break;
            }
        }
        heapArray[index] = top;
    }

    /**
     * 改变指定节点的值
     * @param index 指定下标
     * @param value 新值
     * @return true:修改成功 false:修改失败
     */
    public boolean change(int index, int value) {
        if (index >= currentSize || index < 0) {
            return false;
        }
        int oldValue = heapArray[index].getiData();
        heapArray[index].setiData(value);
        if (oldValue > value) {
            trickleDown(index);
        } else {
            trickleUp(index);
        }
        return true;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void displayHeap() {
        System.out.print("HeapArray:");

        Stream.of(heapArray).forEach((node) -> {
           if (node != null) {
               System.out.print(node.getiData() + " ");
           } else {
               System.out.print("-- ");
           }
        });
        System.out.println();
        int nBlank = 32;
        int iteamsPreRow = 1;
        int j = 0;
        int column = 0;
        System.out.println("--------------------------------------------");

        while (currentSize > 0) {
            if (column == 0) {
                for (int k = 0; k < nBlank; k++) {
                    System.out.print(" ");
                }
            }
            System.out.print(heapArray[j].getiData());
            if (++j == currentSize) {
                break;
            }
            if (++column == iteamsPreRow) {
                nBlank /= 2;
                iteamsPreRow *= 2;
                column = 0;
                System.out.println();
            } else {
                for (int k = 0; k < nBlank * 2 - 2; k++) {
                    System.out.print(" ");
                }
            }

        }
        System.out.print("\n" + "---------------------------------------------");
    }

}
