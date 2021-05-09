package com.example.metopt.nmethods;

/**
 * Метод градиентного спуска.
 * <p>
 * Основная идея заключается в том, чтобы осуществлять оптимизацию
 * в направлении наискорейшего спуска, а это направление задаётся
 * антиградиентом (-grad)
 */
public class GradientMethod extends AbstractNMethod {

    /**
     * Изначальное значение константы, задающей скорость градиентного спуска
     */
    double ALPHA = 1.0;

    /**
     * Норма градиента функции
     */
    double gradNorm;

    public GradientMethod(QuadraticFunction func, String name, Vector start) {
        super(func, name, start);
    }

    public GradientMethod(QuadraticFunction func, Vector start) {
        this(func, "Gradient descent", start);
    }

    public GradientMethod(QuadraticFunction func, Vector start, double eps) {
        super(func, "Gradient descent", start, eps);
    }

    @Override
    Value<Vector, Double> iterate(Value<Vector, Double> x) {
        while (true) {
            Value<Vector, Double> y = new Value<>(
                    x.getVal().add(grad.multiply(-ALPHA / gradNorm)),
                    getFunction());
            if (y.getFVal() < x.getFVal()) {
                return y;
            } else {
                ALPHA /= 2;
            }
        }
    }

    @Override
    boolean cycleCondition() {
        grad = getFunction().getGrad(x.getVal());
        gradNorm = grad.norm();
        return !(gradNorm < EPS);
    }
}
