package com.example.metopt.nmethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Абстрактный класс для градиентных методов многомерной оптимизации
 */
public abstract class AbstractNMethod {

    /**
     * Заданная точность
     */
    final double EPS;

    private final QuadraticFunction function;

    /**
     * Градиент данной квадратичной функции
     */
    Vector grad;

    String name;

    Value<Vector, Double> x;

    protected AbstractNMethod(QuadraticFunction function, String name, Vector start) {
        this(function, name, start, 1e-3);
    }

    protected AbstractNMethod(QuadraticFunction function, String name, Vector start, double eps) {
        this.function = function;
        this.name = name;
        this.EPS = eps;
        x = new Value<>(start, function);
    }

    public QuadraticFunction getFunction() {
        return function;
    }

    public int computeMin() {
        int cnt = 0;
        while (cycleCondition()) {
            x = iterate(x);
            cnt++;
        }
        return cnt;
    }

    public List<Value<Vector, Double>> getAllIteration() {
        List<Value<Vector, Double>> res = new ArrayList<>();

        while (cycleCondition()) {
            x = iterate(x);
            res.add(x);
        }

        return res;
    }

    abstract Value<Vector, Double> iterate(Value<Vector, Double> x);

    abstract boolean cycleCondition();

    Vector getAns() {
        return x.getVal();
    }
}
