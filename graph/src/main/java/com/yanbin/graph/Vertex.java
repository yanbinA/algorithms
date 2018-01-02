package com.yanbin.graph;

/**
 * 图 顶点对象
 *
 * @author yanbin
 * @date 2017/12/30 16:16
 */
public class Vertex {
    /**
     * 顶点标签
     */
    private char label;
    /**
     * 该定点是否已被访问，false:未被访问，true已被访问
     */
    private boolean wasVisited;

    public Vertex(char label) {
        this.label = label;
        wasVisited = false;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public boolean isWasVisited() {
        return wasVisited;
    }

    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

    @Override
    public String toString() {
        return this.label + "";
    }
}
