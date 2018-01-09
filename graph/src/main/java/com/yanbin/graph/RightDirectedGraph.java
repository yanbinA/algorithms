package com.yanbin.graph;

import jdk.nashorn.internal.ir.VarNode;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 有向带权图
 * @author yanbin
 * @date 2018/1/9 15:44
 */
public class RightDirectedGraph {
    private final int MAX_VERTEXS = 20;
    private Vertex vertexList[];
    private int adjMate[][];
    private DistPar minPath[][];
    private int nVertex;

    public RightDirectedGraph() {
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
    }

    /**
     *  计算个点之间最短路径
     */
    public void shortestPath() {
        minPath = new DistPar[nVertex][nVertex];
        //以每个节点原点
        for (int i = 0; i < nVertex; i++) {
            PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(Edge::getDistance));
            int currentVertex = i;
            vertexList[currentVertex].setWasVisited(true);
            while (!queue.isEmpty() || hasSuccessor(currentVertex)) {
                int start = currentVertex;
                //当前点到原点的距离
                int distance = adjMate[i][currentVertex] == Integer.MAX_VALUE ? 0 : adjMate[i][currentVertex];
                //将当前顶点到后顶点的所有路径放入queue，权值是目标顶点到原点的距离
                IntStream.range(0, nVertex).forEach(dist -> {
                    if (adjMate[start][dist] != Integer.MAX_VALUE && !vertexList[dist].isWasVisited()) {
                        queue.add(new Edge(start, dist, distance + adjMate[start][dist]));
                    }
                });
                //取出queue中权值最小路径,表示原点到目标顶点的路径
                Edge edge = queue.poll();
                DistPar newPath = new DistPar(edge.getDistance(), edge.getSrcVertex());
                //在minPath保存最小值
                minPath[i][edge.getDestVertex()] = (minPath[i][edge.getDestVertex()] != null && minPath[i][edge.getDestVertex()].getDistance() <= newPath.getDistance()) ?
                        minPath[i][edge.getDestVertex()] : newPath;
                //current=目标顶点
                currentVertex = edge.getDestVertex();
                vertexList[currentVertex].setWasVisited(true);
                //删除无用边
                deleteEdge(queue);
            }
        }
    }

    private boolean hasSuccessor(int currentVertex) {

        for (int i = 0; i < nVertex; i++) {
            if (adjMate[currentVertex][i] != Integer.MAX_VALUE && !vertexList[i].isWasVisited()) {
                return true;
            }
        }
        return false;
    }

    private void deleteEdge(PriorityQueue<Edge> queue) {
        Iterator<Edge> iterator = queue.iterator();
        for (; iterator.hasNext();) {
            Edge edge = iterator.next();
            if (vertexList[edge.getSrcVertex()].isWasVisited() && vertexList[edge.getDestVertex()].isWasVisited()) {
                iterator.remove();
            }
        }

    }

    public static void main(String args[]) {
        RightDirectedGraph graph = new RightDirectedGraph();
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
        graph.shortestPath();
    }
}

/**
 * 保存 顶点到原点的最短路径值，以及最短路径上的父节点
 */
class DistPar {
    private int distance;
    private int parentVertex;

    public DistPar(int distance, int parentVertex) {
        this.distance = distance;
        this.parentVertex = parentVertex;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getParentVertex() {
        return parentVertex;
    }

    public void setParentVertex(int parentVertex) {
        this.parentVertex = parentVertex;
    }
}
