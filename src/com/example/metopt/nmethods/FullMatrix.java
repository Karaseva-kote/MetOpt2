package com.example.metopt.nmethods;

import java.util.AbstractList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Квадратная матрица на основе {@link List<Vector>}
 */
public class FullMatrix implements Matrix {
    private final List<Vector> a;

    public FullMatrix(List<List<Double>> a) {
        this.a = a.stream().map(Vector::new).collect(Collectors.toList());
        if (a.stream().anyMatch(list -> list.size() != a.size())) {
            throw new IllegalArgumentException("Non-quadratic matrix");
        }
    }

    /**
     * Умножение на вектор
     */
    public Vector multiply(Vector vector) {
        Vector res = new Vector();
        for (Vector v : a) {
            res.add(v.scalarProduct(vector));
        }
        return res;
    }

    /**
     * Получение элемента матрицы
     */
    public double get(int r, int c) {
        return a.get(r).get(c);
    }

    public int size() {
        return a.size();
    }
}
