package com.yanbin.graph;

import java.util.Arrays;

/**
 * @author yanbin
 * @date 2018/1/2 17:59
 */
public class GraphTest {



    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph();
        graph.addVertex(new Vertex('A'));
        graph.addVertex(new Vertex('B'));
        graph.addVertex(new Vertex('C'));
        graph.addVertex(new Vertex('D'));
        graph.addVertex(new Vertex('E'));
        graph.addVertex(new Vertex('F'));
        graph.addVertex(new Vertex('G'));
        graph.addVertex(new Vertex('H'));
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        System.out.println("--------------------");
        graph.display();
        System.out.println("--------------------");
        graph.warshall();
        graph.display();
    }

}
