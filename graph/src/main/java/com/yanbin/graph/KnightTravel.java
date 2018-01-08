package com.yanbin.graph;

import java.util.*;
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
    private static int MAX_VERTS;
    /**
     * 方块之间边关系
     */
    private int[][] adjMate;
    /**
     * 棋盘大小，
     */
    private int rows;
    private int cols;

    private Stack<Integer> stack = new Stack<>();
    private List<Stack<Integer>> stackList = new ArrayList<>();

    public KnightTravel(int length) {
        this(length, length);
    }

    public KnightTravel(int cols, int rows) {
        this.rows = rows;
        this.cols = cols;
        initialize();
    }

    /**
     * 初始化棋盘
     */
    private void initialize() {
        this.nVertex = rows * cols;
        MAX_VERTS = rows * cols;
        this.vertexList = new Vertex[MAX_VERTS];
        adjMate = new int[MAX_VERTS][MAX_VERTS];
        IntStream.range(0, MAX_VERTS).mapToObj(i -> new Vertex((char) (i + '1'))).collect(Collectors.toList()).toArray(vertexList);
        initAdjMate();

    }

    /**
     * 初始化方块连通关系
     */
    private void initAdjMate() {
        //设置默认值
        for (int i = 0; i < nVertex; i++) {
            for (int j = 0; j < nVertex; j++) {
                adjMate[i][j] = 0;
            }
        }

        for (int i = 0; i < nVertex; i++) {
            //添加当前方块的 右 和 下的关系
            if (i % cols != cols - 1) {
                //不是边
                addAdjMate(i, i + 1);
            }
            if (i + cols < nVertex) {
                addAdjMate(i, i + cols);
            }
        }


    }

    /**
     * 从指定位置开始执行
     * 1.将start point放入栈，标记，并设置为当前方块（current）
     * 2.在adjMate[current.index]中从下标startIndex(初始0)开始寻找下一个方块，
     * 3.将找到的方块放入栈，标记，并设置为当前方块，startIndex设置为0,执行2
     * 4.如果没有找到，将该方块从栈中弹出，取消标记，并将startIndex设置为 被弹方块的下标 + 1，执行2
     * 5.当stack.size与nVertex相等时，此时已走完所有方块；当stack.size为0时，表示不能完成；结束
     * @param row   行标号
     * @param col   列标号
     */
    public void run(int row, int col) {
        int index = row * cols + col;
        int startIndex = 0;
        stack.addElement(index);
        vertexList[index].setWasVisited(true);
        this.display();
        while (!stack.empty() && stack.size() != nVertex) {
            Integer current = stack.peek();
            boolean hasNext = false;
            int nexVertex = startIndex;
            for (; nexVertex < MAX_VERTS; nexVertex++) {
                if (adjMate[current][nexVertex] == 1 && !vertexList[nexVertex].isWasVisited()) {
                    hasNext = true;
                    break;
                }
            }
            if (hasNext) {
                vertexList[nexVertex].setWasVisited(true);
                stack.addElement(nexVertex);
                startIndex = 0;
            } else {
                Integer pop = stack.pop();
                startIndex = pop + 1;
                vertexList[pop].setWasVisited(false);
            }
            this.display();
        }
    }


    public void runAndSave(int row, int col) {
        int index = row * cols + col;
        int startIndex = 0;
        Stack<Integer> currentStack = new Stack<>();
        currentStack.addElement(index);
        vertexList[index].setWasVisited(true);
        while (!currentStack.empty()) {
            Integer current = currentStack.peek();
            boolean hasNext = false;
            int nexVertex = startIndex;
            for (; nexVertex < MAX_VERTS; nexVertex++) {
                if (adjMate[current][nexVertex] == 1 && !vertexList[nexVertex].isWasVisited()) {
                    hasNext = true;
                    break;
                }
            }
            if (hasNext) {
                vertexList[nexVertex].setWasVisited(true);
                currentStack.addElement(nexVertex);
                if (currentStack.size() == nVertex) {
                    stackList.add((Stack<Integer>) currentStack.clone());
                }
                startIndex = 0;
            } else {
                Integer pop = currentStack.pop();
                startIndex = pop + 1;
                vertexList[pop].setWasVisited(false);
            }
        }
        displayResult();
    }

    private void displayResult() {

        System.out.println("共" + stackList.size() + "种路径！");
        stackList.forEach(stack -> {
            stack.forEach(vertex -> {
                System.out.print(vertexList[vertex].getLabel() + "->");
            });
            System.out.println();
        });

    }

    /**
     * 添加边
     * @param start 开始方块下标
     * @param end   结束方块下标
     */
    private void addAdjMate(int start, int end) {
        adjMate[start][end] = 1;
        adjMate[end][start] = 1;
    }

    /**
     * 删除方块
     * @param row   行标号 [0, length)
     * @param col   列标号 [0, length)
     * @return
     */
    public boolean deleteVertex(int row, int col) {
        int index = row * this.cols + col;
        if (row >= 0 && row < this.rows) {
            if (row > 0) {
                removeAdjMate(index, index - this.cols);
            }
            if (row < this.rows - 1) {
                removeAdjMate(index, index + this.cols);
            }
        } else {
            return false;
        }
        if (index >= 0 && index < MAX_VERTS) {
            if (index > 0) {
                removeAdjMate(index, index - 1);
            }
            if (index < MAX_VERTS - 1) {
                removeAdjMate(index, index + 1);
            }
        } else {
            return false;
        }
        vertexList[index] = null;
        nVertex--;
        return true;
    }

    /**
     * 删除边
     * @param start 开始方块下标
     * @param end   结束方块下标
     */
    private void removeAdjMate(int start, int end) {
        adjMate[start][end] = 0;
        adjMate[end][start] = 0;
    }

    /**
     * 打印棋盘
     */
    public void display() {
        displayCol();
        for (int i = 0; i < MAX_VERTS; i++) {
            if (i % this.cols == 0) {
                System.out.print("|");
            }
            if (vertexList[i] == null) {
                System.out.print(" X");
            } else if (vertexList[i].isWasVisited()) {
                System.out.print(" *");
            } else {
                System.out.print("  ");
            }

            System.out.print(" |");
            if (i % cols == cols - 1) {
                displayCol();
            }
        }
    }

    private void displayCol() {
        System.out.print("\n ");
        for  (int i = 0; i < this.cols; i++) {
            System.out.print(" 一 ");
        }
        System.out.println();
    }
}
