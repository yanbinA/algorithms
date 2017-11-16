package com.yanbin.algorithms.structure;

/**
 * 栈基本结构 接口
 * @author yanbin
 * @date 2017/11/16 11:44
 */
public interface Structure<T> {

    /**
     * 添加元素
     * @param value 指定的元素
     */
    void push(T value);

    /**
     * 移除栈顶元素
     * @return 被移除的元素
     */
    T pop();

    /**
     * @return 返回栈顶元素
     */
    T peek();

    /**
     * 判断栈是否空
     * @return if empty return true
     */
    boolean isEmpty();

    /**
     * 检查栈是否满载
     * @return if full return true
     */
    boolean isFull();

    int size();

    void empty();

}
