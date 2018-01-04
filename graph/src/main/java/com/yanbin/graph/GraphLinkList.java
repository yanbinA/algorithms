package com.yanbin.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 图结构- 邻接表存储图结构
 * @author yanbin
 * @date 2018/1/4 15:22
 */
public class GraphLinkList {

    private Vertex[] vertexList;
    private final int MAX_VERTS = 20;
    private int nVerts;
    private LinkedList<Vertex>[] adjTable;

    public GraphLinkList() {
        vertexList = new Vertex[MAX_VERTS];
        nVerts = 0;
        adjTable = new LinkedList[MAX_VERTS];
    }

    /**
     * 添加顶点
     * @param vertex 新顶点
     */
    public void addVertex(Vertex vertex) {
        adjTable[nVerts] = new LinkedList<>();
        this.vertexList[this.nVerts++] = vertex;
    }

    public void addEdge(int start, int end) {
        adjTable[start].add(vertexList[end]);
        adjTable[end].add(vertexList[start]);
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
        Stack<Vertex> stack = new Stack<>();
        stack.push(vertexList[0]);
        vertexList[0].setWasVisited(true);
        System.out.print(vertexList[0]);

        while (!stack.empty()) {
            Vertex vertex = getAdjUnvisitedVertex(stack.peek());
            if (vertex == null) {
                stack.pop();
            } else {
               vertex.setWasVisited(true);
                stack.push(vertex);
                System.out.print(vertex);
            }
        }
        Arrays.stream(vertexList).filter(vertex -> vertex != null).forEach(vertex -> vertex.setWasVisited(false));
    }


    private Vertex getAdjUnvisitedVertex(Vertex peek) {
        int index = 0;
        for (; index < nVerts; index++) {
            if (peek == vertexList[index]) {
                break;
            }
        }
        return adjTable[index].stream().filter(vertex -> !vertex.isWasVisited()).findFirst().orElse(null);
    }

}
