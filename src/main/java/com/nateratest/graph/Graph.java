package com.nateratest.graph;

import java.util.List;

public interface Graph<E> {
    void addVertex(E value);
    void addEdge(E value1, E value2);
    List<E> getPath(E value1, E value2);

    List<E> getVertex();
    void printMatrix();
}
