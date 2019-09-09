package com.nateratest.graph;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraph<E> implements Graph<E> {
    protected List<E> nodes = new ArrayList<>();
    protected List<List<Boolean>> matrix = new ArrayList<>();

    @Override
    public synchronized void addVertex(E value) {
        if (value != null && !nodes.contains(value)) {
            nodes.add(value);

            matrix.forEach(edges -> edges.add(false));

            List<Boolean> list = new ArrayList<>();
            int i = matrix.size();
            while (i >= 0) {
                list.add(false);
                i--;
            }
            matrix.add(list);
        }
    }

    @Override
    public synchronized List<E> getPath(E value1, E value2) {
        int i = nodes.indexOf(value1);
        int j = nodes.indexOf(value2);
        if (i != -1 && j != -1) {
            List<E> path = new ArrayList<>();
            path.add(value1);
            getPath(path, i, j);
            return path;
        }
        return null;
    }

    private void getPath(List<E> path, int pos, int end) {
        int i = 0;
        while (i < matrix.size()) {
            if (matrix.get(pos).get(i) && !path.contains(nodes.get(i))) {
                path.add(nodes.get(i));
                if (i != end) {
                    getPath(path, i, end);
                }
            }
            i++;
        }
        if (nodes.indexOf(path.get(path.size() - 1)) != end) {
            path.remove(path.size() - 1);
        }
    }

    @Override
    public synchronized List<E> getVertex() {
        return new ArrayList<>(nodes);
    }

    @Override
    public synchronized void printMatrix() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < matrix.size(); i++) {
            sb.append(i + 1).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < matrix.size(); i++) {
            sb.append(i + 1).append(" ");
            for (Boolean value : matrix.get(i)) {
                sb.append(value ? 1 : 0);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
