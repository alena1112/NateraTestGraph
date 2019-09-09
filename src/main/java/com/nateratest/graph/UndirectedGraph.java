package com.nateratest.graph;

public class UndirectedGraph<E> extends AbstractGraph<E> {

    @Override
    public synchronized void addEdge(E value1, E value2) {
        int i = nodes.indexOf(value1);
        int j = nodes.indexOf(value2);

        if (i != -1 && j != -1) {
            matrix.get(i).set(j, true);
            matrix.get(j).set(i, true);
        }
    }
}
