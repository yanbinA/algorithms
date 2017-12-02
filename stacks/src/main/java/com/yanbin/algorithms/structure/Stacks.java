package com.yanbin.algorithms.structure;

import com.yanbin.algorithms.structure.Structure;

/**
 * @author yanbin
 * @date 2017/11/16 14:32
 */
public class Stacks<T> implements Structure<T>{

    private Object[] stackArray;
    private int maxSize;
    private int top;
    private final static Object[] EMPTY_STACK = {};

    public Stacks() {
        this(0);
    }

    public Stacks(int maxSize) {
        this.maxSize = maxSize;
        if (maxSize > 0) {
            this.stackArray = new Object[maxSize];
        } else {
            this.stackArray = EMPTY_STACK;
        }
        this.top = -1;
    }

    @Override
    public void push(T value) {
        if (isFull()) {
            expand();
        }
        stackArray[++top] = value;
    }

    private void expand() {
        maxSize = (int)((maxSize + 1) * 1.5);
        Object[] oldArray = stackArray;
        stackArray = new Object[maxSize];
        System.arraycopy(oldArray, 0, stackArray, 0, oldArray.length);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return (T) stackArray[top--];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) stackArray[top];

    }

    @Override
    public boolean isEmpty() {
        if (top == -1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isFull() {
        if ((top + 1) == maxSize) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public void empty() {
        top = -1;
    }
}
