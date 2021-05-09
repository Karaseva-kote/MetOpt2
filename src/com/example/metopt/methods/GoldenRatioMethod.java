package com.example.metopt.methods;

import java.util.function.Function;

public class GoldenRatioMethod extends AbstractMethod {

    public GoldenRatioMethod(Function<Double, Double> fun) {
        super(fun, "Golden Ratio");
    }

    double x1, x2, f1, f2;

    final double PHI = (1 + Math.sqrt(5)) / 2;
    final double RES_PHI = 2 - PHI;

    @Override
    void initialize(double left, double right) {
        l = left;
        r = right;

        x1 = l + RES_PHI * (r - l);
        x2 = r - RES_PHI * (r - l);
        f1 = function.apply(x1);
        f2 = function.apply(x2);
    }

    @Override
    void iterate() {
        if (f1 < f2) {
            r = x2;
            x2 = x1;
            f2 = f1;
            x1 = l + RES_PHI * (r - l);
            f1 = function.apply(x1);
        } else {
            l = x1;
            x1 = x2;
            f1 = f2;
            x2 = r - RES_PHI * (r - l);
            f2 = function.apply(x2);
        }
    }

    @Override
    boolean cycleCondition() {
        return (r - l) > EPSILON;
    }

    @Override
    double getAns() {
        return (x1 + x2) / 2;
    }
}
