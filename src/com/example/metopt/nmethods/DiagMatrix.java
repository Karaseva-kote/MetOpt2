package com.example.metopt.nmethods;

import java.util.ArrayList;
import java.util.List;

public class DiagMatrix implements Matrix {
    private List<Double> a;

    DiagMatrix(List<Double> a) {
        this.a = a;
    }

    @Override
    public Vector multiply(Vector vector) {
        List<Double> v = new ArrayList<>();
        for(int i = 0; i < vector.size(); i++) {
            v.add(vector.get(i)*a.get(i));
        }
        return new Vector(v);
    }

    @Override
    public double get(int r, int c) {
        return (r == c)? a.get(r) : 0;
    }

    @Override
    public int size() {
        return a.size();
    }
}
