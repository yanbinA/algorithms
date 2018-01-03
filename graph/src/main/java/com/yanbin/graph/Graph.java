package com.yanbin.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 图结构
 * @author yanbin
 * @date 2017/12/30 16:22
 */
public class Graph {
    /**
     * 图最大顶点
     */
    private final int MAX_VERTS = 20;
    /**
     * 顶点集合
     */
    private Vertex vertexList[];
    /**
     * 邻接矩阵 表示图
     * 二维数组表示顶点之间是否存在 边
     * 1：表示存在边 0：表示不存在边
     */
    private int adjMat[][];
    /**
     * 当前含有的顶点数
     */
    private int nVerts;

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        //初始化
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
        nVerts = 0;
    }

    /**
     * 添加顶点
     * @param vertex 新顶点
     */
    public void addVertex(Vertex vertex) {
        this.vertexList[this.nVerts++] = vertex;
    }

    /**
     * 添加边
     * @param start 边 的开始顶点下标
     * @param end   边 的结束顶点的下标
     */
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    /**
     * 深度优先搜索 遍历整个图
     * 1.任取一个顶点作为当前顶点(选用vertexList[0]),标记已读，放入栈
     *      2.1.如果可能，访问一个邻接且未访问的顶点，标记，并放入栈。
     *      2.2.当不能执行2.1时，如果栈非空，则栈中弹出一个顶点。
     *      2.3.如果2.1和2.2都不能执行，则完成整个搜索过程。
     */
    public void depthFirstSearch() {
        //栈存放顶点下标
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        vertexList[0].setWasVisited(true);
        displayVertex(0);

        while (!stack.empty()) {
            int index = getAdjUnvisitedVertex(stack.peek());
            if (index == -1) {
                stack.pop();
            } else {
                vertexList[index].setWasVisited(true);
                stack.push(index);
                displayVertex(index);
            }
        }
        Arrays.stream(vertexList).filter(vertex -> vertex != null).forEach(vertex -> vertex.setWasVisited(false));
    }

    /**
     * 寻找邻接且未访问的顶点
     * @param index 当前顶点的下标
     * @return  目标顶点的小标，-1表示没有符合条件的顶点
     */
    private int getAdjUnvisitedVertex(Integer index) {
        for (int i = 0; i < nVerts; i++) {
            if (adjMat[index][i] == 1 && !vertexList[i].isWasVisited()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 广度优先搜索
     *  1.任取一个顶点作为当前顶点(选用vertexList[0]),标记已读，放入列队
     *      2.1.如果可能，访问当前顶点的所有邻接且未访问的顶点，标记，并放入列队。
     *      2.2.当不能执行2.1时，如果列队非空，则列队中弹出一个顶点作为当前顶点。
     *      2.3.如果2.1和2.2都不能执行，则完成整个搜索过程。
     */
    public void breadthFirstSearch() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        displayVertex(0);
        vertexList[0].setWasVisited(true);

        while (!queue.isEmpty()) {
            for (int i = 0; i < nVerts; i++) {
                if (adjMat[queue.element()][i] == 1 && !vertexList[i].isWasVisited()) {
                    vertexList[i].setWasVisited(true);
                    queue.offer(i);
                    displayVertex(i);
                }
            }
            queue.poll();
        }
        Arrays.stream(vertexList).filter(vertex -> vertex != null).forEach(vertex -> vertex.setWasVisited(false));
    }

    /**
     * 最小生成树
     */
    public Graph minSpanningTree() {
        Graph mst = new Graph();
        //利用深度优先搜索 遍历整个图
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        vertexList[0].setWasVisited(true);
        mst.addVertex(vertexList[0]);

        while (!stack.empty()) {
            int index = getAdjUnvisitedVertex(stack.peek());
            if (index == -1) {
                Integer end = stack.pop();
                if (!stack.empty()) {
                    mst.addEdge(stack.peek(), end);
                }
            } else {
                vertexList[index].setWasVisited(true);
                stack.push(index);
                mst.addVertex(vertexList[index]);
            }
        }
        Arrays.stream(vertexList).filter(vertex -> vertex != null).forEach(vertex -> vertex.setWasVisited(false));
        return mst;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v]);
    }
}
