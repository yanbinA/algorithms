package com.yanbin.graph;

/**
 * 图 顶点对象
 *
 * @author yanbin
 * @date 2017/12/30 16:16
 */
public class Vertex {

    private char label;
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
}
