package com.yanbin.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * 带权图
 * @author yanbin
 * @date 2018/1/8 15:46
 */
public class RightsGraph {

    private final int MAX_VERTEXS = 20;
    private Vertex vertexList[];
    private int adjMate[][];
    private int nVertex;

    public RightsGraph() {
        this.nVertex = 0;
        vertexList = new Vertex[MAX_VERTEXS];
        this.adjMate = new int[MAX_VERTEXS][MAX_VERTEXS];
        Arrays.stream(this.adjMate).forEach(arr -> IntStream.range(0, MAX_VERTEXS).forEach(i -> arr[i] = Integer.MAX_VALUE));
    }

    public void addVertex(char lab) {
        vertexList[nVertex++] = new Vertex(lab);
    }

    public void addAdjMate(int start, int end, int weight) {
        adjMate[start][end] = weight;
        adjMate[end][start] = weight;
    }

    /**
     * 最小生成树
     * 1.找到最新顶点到其他顶点的所有边，并且这些顶点不在树中。将所有边放入优先级列队，weight值 由小-大
     * 2.在列队中找出权值最小的边，将其和他的顶点放入树集合中。
     *      2.1删除无用边
     * @param index 指定顶点
     */
    public void mstw(int index) {
        if (index >= nVertex) {
            System.out.println("超出范围！");
        }
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(Edge::getDistance));
        int currentVertex = index;
        //树中节点数
        int nTree = 0;
        while (nTree < nVertex - 1) {
            //将当前节点的所有可用边放入列队
            
        }
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v]);
    }

}

class Edge {
    private int srcVertex;
    private int destVertex;
    private int distance;

    public Edge() {}

    public Edge(int srcVertex, int destVertex, int distance) {
        this.srcVertex = srcVertex;
        this.destVertex = destVertex;
        this.distance = distance;
    }

    public int getSrcVertex() {
        return srcVertex;
    }

    public void setSrcVertex(int srcVertex) {
        this.srcVertex = srcVertex;
    }

    public int getDestVertex() {
        return destVertex;
    }

    public void setDestVertex(int destVertex) {
        this.destVertex = destVertex;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
