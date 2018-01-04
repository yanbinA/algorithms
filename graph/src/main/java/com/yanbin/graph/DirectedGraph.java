package com.yanbin.graph;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 有向图
 * @author yanbin
 * @date 2018/1/3 15:55
 */
public class DirectedGraph {

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
    private char[] sortedArray;

    public DirectedGraph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        //初始化
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
        nVerts = 0;
        sortedArray = new char[MAX_VERTS];
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
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v]);
    }

    /**
     * 有向图 拓扑排序
     * 1.需找无后继顶点的顶点，
     * 2.将1找到的顶点，放入列队，从图中删除顶点
     * 3.如果1没找到顶点，结束
     */
    public void topo() {
        int orig_nVertex = nVerts;

        while (nVerts > 0) {
            int currentVertex = noSuccessor();
            if (currentVertex == -1) {
                //存在顶点，但是找不到无后继顶点的顶点，说明存在环
                System.out.println("ERROR！");
                return;
            }
            //将顶点放入列队
            sortedArray[nVerts - 1] = vertexList[currentVertex].getLabel();
            //在图中删除该定点
            removeVertex(currentVertex);
        }
        System.out.println("Sorted order:");
        Stream.of(sortedArray).forEach(item -> {
            if (item != null) {
                System.out.print(item);
            }
        });
    }

    /**
     * 删除指定顶点
     *  从vertexList中删除，后顶点往前移
     *  在adjMat中删除顶点的行和列，其他顶点信息前移
     * @param vertexIndex 顶点下标
     */
    private void removeVertex(int vertexIndex) {
        Vertex currentVertex = vertexList[vertexIndex];
        vertexList = Stream.of(vertexList).filter(vertex -> vertex != currentVertex).collect(Collectors.toList()).toArray(new Vertex[MAX_VERTS]);
        for (int i = vertexIndex; i < nVerts - 1; i++) {
            for (int j = 0; j < nVerts; j++) {
                adjMat[i][j] = adjMat[i + 1][j];
            }
        }
        for (int i = vertexIndex; i < nVerts - 1; i++) {
            for (int j = 0; j < nVerts; j++) {
                adjMat[j][i] = adjMat[j][i + 1];
            }
        }
        nVerts--;
    }

    /**
     * 寻找后继顶点在vertexList中的下标
     * @return  顶点在vertexList中的下标,
     */
    private int noSuccessor() {
        rowLable:for (int row = 0; row < nVerts; row++) {
            for (int col = 0; col < nVerts; col++) {
                if (adjMat[row][col] > 0) {
                    continue rowLable;
                }
            }
            return row;
        }
        return -1;
    }

    /**
     * 修改邻接矩阵 使其表示从顶点与顶点间是否连通
     *  如果A到B,B到C,那么A可以到C;
     *  1.寻找该顶点的后顶点；
     *  2.寻找该顶点的前顶点；
     *  3.连通前后顶点
     */
    public void warshall() {
        for (int y = 0; y < nVerts; y++) {
            //遍历每一行，
            for (int x = 0; x < nVerts; x++) {
                //遍历每行中的单元，寻找是否存在后顶点
                if (adjMat[y][x] == 1) {
                    //找到一个后顶点，寻找前顶点
                    for (int z = 0; z < nVerts; z++) {
                        if (adjMat[z][y] == 1) {
                            //连通两个顶点
                            adjMat[z][x] = 1;
                        }
                    }
                }
            }
        }
    }

    public void display() {
        for (int i = 0; i < nVerts; i++) {
            System.out.print(vertexList[i] + " line to:");
            for (int j = 0; j < nVerts; j++) {
                if (adjMat[i][j] == 1) {
                    System.out.print(vertexList[j] + " ");
                }
            }
            System.out.println();
        }
    }
}
