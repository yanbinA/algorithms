package com.yanbin.graph;

import java.util.*;
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
            vertexList[currentVertex].setWasVisited(true);
            nTree++;
            int start = currentVertex;
            IntStream.range(0, MAX_VERTEXS).forEach(i -> {
                if (adjMate[start][i] != Integer.MAX_VALUE && !vertexList[i].isWasVisited()) {
                    queue.add(new Edge(start, i, adjMate[start][i]));
                }
            });
            //删除无用边
            deleteEdge(queue);
            if (queue.isEmpty()) {
                System.out.println("No connection！");
            }
            //获取权值最小边
            Edge edge = queue.poll();
            int srcVertex = edge.getSrcVertex();
            currentVertex = edge.getDestVertex();
            System.out.print(vertexList[srcVertex].getLabel() + " to " + vertexList[currentVertex] + "\n");

        }
        IntStream.range(0, nVertex).forEach(i -> vertexList[i].setWasVisited(false));
    }

    /**
     * 删除queue中的无用边
     * @param queue 列队
     */
    private void deleteEdge(PriorityQueue<Edge> queue) {
        Iterator<Edge> iterator = queue.iterator();
        for (; iterator.hasNext();) {
            Edge edge = iterator.next();
            if (vertexList[edge.getSrcVertex()].isWasVisited() && vertexList[edge.getDestVertex()].isWasVisited()) {
                iterator.remove();
            }
        }

    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v]);
    }

    public static void main(String[] args) {
        RightsGraph graph = new RightsGraph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addAdjMate(0,1,6);
        graph.addAdjMate(0,3,4);
        graph.addAdjMate(1,2,10);
        graph.addAdjMate(1,3,7);
        graph.addAdjMate(1,4,7);
        graph.addAdjMate(2,3,8);
        graph.addAdjMate(2,4,5);
        graph.addAdjMate(2,5,6);
        graph.addAdjMate(3,4,12);
        graph.addAdjMate(4,5,7);
        graph.mstw(0);
        System.out.println();
        graph.mstw(2);
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
