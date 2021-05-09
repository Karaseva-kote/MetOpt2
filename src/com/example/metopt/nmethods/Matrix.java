package com.example.metopt.nmethods;

public interface Matrix {
    Vector multiply(Vector vector);
    double get(int r, int c);
    int size();
}
