package com.yanbin.graph;

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


}
