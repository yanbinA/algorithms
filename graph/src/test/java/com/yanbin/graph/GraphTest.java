package com.yanbin.graph;

/**
 * @author yanbin
 * @date 2018/1/2 17:59
 */
public class GraphTest {



    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex(new Vertex('A'));
        graph.addVertex(new Vertex('B'));
        graph.addVertex(new Vertex('C'));
        graph.addVertex(new Vertex('D'));
        graph.addVertex(new Vertex('E'));
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        System.out.println("深度优先搜索---------");
        graph.depthFirstSearch();
        System.out.println("--------------------");
        System.out.println("广度优先搜索---------");
        graph.breadthFirstSearch();
        System.out.println("--------------------");

    }

}
