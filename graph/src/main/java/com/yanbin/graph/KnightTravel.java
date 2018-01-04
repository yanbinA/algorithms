package com.yanbin.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 骑士旅行
 *  定义一个棋盘，要求不重复走遍所有方块
 * @author yanbin
 * @date 2018/1/4 17:23
 */
public class KnightTravel {
    /**
     * 方块列表
     */
    private Vertex[] vertexList;
    /**
     * 方块数
     */
    private int nVertex;
    /**
     * 方块之间边关系
     */
    private int[][] adjMate;
    /**
     * 棋盘长度，正方形棋盘
     */
    private int length;

    public KnightTravel(int length) {
        this.length = length;
        initialize();
    }

    /**
     * 初始化棋盘
     */
    private void initialize() {
        this.nVertex = length * length;
        this.vertexList = new Vertex[nVertex];
        IntStream.range(0, nVertex).mapToObj(i -> new Vertex((char) (i + 1))).collect(Collectors.toList()).toArray(vertexList);
        
    }



}
