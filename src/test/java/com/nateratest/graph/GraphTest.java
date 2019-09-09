package com.nateratest.graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphTest {

    @Test
    public void directedGraphTest() {
        Graph<String> graph = new DirectedGraph<>();
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");

        graph.addEdge("1", "3");
        graph.addEdge("3", "2");
        graph.addEdge("3", "4");
        graph.addEdge("4", "5");

        List<String> path = graph.getPath("1", "5");

        assertEquals(path.size(), 4);
        assertEquals(path.get(0), "1");
        assertEquals(path.get(1), "3");
        assertEquals(path.get(2), "4");
        assertEquals(path.get(3), "5");
    }

    @Test
    public void undirectedGraphTest() {
        Graph<Airport> graph = new UndirectedGraph<>();

        Airport dme = new Airport("Domodedovo", "Moscow");
        Airport vnu = new Airport("Vnukovo", "Moscow");
        Airport she = new Airport("Sheremetyevo", "Moscow");
        Airport nar = new Airport("Narita", "Tokyo");
        Airport kur = new Airport("Kurumoch", "Samara");
        Airport ath = new Airport("Athens International Airport", "Athens");
        Airport pra = new Airport("Prague Airport", "Prague");

        graph.addVertex(dme);
        graph.addVertex(vnu);
        graph.addVertex(she);
        graph.addVertex(nar);
        graph.addVertex(kur);
        graph.addVertex(ath);
        graph.addVertex(pra);

        graph.addEdge(dme, nar);
        graph.addEdge(dme, ath);
        graph.addEdge(vnu, kur);
        graph.addEdge(she, pra);

        graph.addVertex(null);
        assertEquals(graph.getVertex().size(), 7);

        graph.addVertex(dme);
        assertEquals(graph.getVertex().size(), 7);

        graph.addVertex(new Airport("Domodedovo", "Moscow"));
        assertEquals(graph.getVertex().size(), 7);

        List<Airport> path = graph.getPath(dme, ath);
        assertEquals(path.size(), 2);
        assertEquals(path.get(0), dme);
        assertEquals(path.get(1), ath);

        path = graph.getPath(ath, dme);
        assertEquals(path.size(), 2);
        assertEquals(path.get(0), ath);
        assertEquals(path.get(1), dme);

        path = graph.getPath(nar, pra);
        assertEquals(path.size(), 0);
    }

    @Test
    public void threadSafeTest() {
        Graph<String> graph = new DirectedGraph<>();
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");

        graph.addEdge("1", "3");
        graph.addEdge("3", "2");

        Thread thread1 = new Thread(new AddVertexThread(Arrays.asList("4", "5", "6"), graph));
        Thread thread2 = new Thread(new AddVertexThread(Arrays.asList("7", "8", "9"), graph));
        thread1.start();
        thread2.start();
    }

    private class AddVertexThread implements Runnable {
        private List<String> nodes;
        private Graph<String> graph;

        public AddVertexThread(List<String> nodes, Graph<String> graph) {
            this.nodes = nodes;
            this.graph = graph;
        }

        @Override
        public void run() {
            nodes.forEach(node -> {
                graph.addVertex(node);
                graph.printMatrix();
            });
        }
    }
}
