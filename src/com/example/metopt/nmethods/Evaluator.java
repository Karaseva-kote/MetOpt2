package com.example.metopt.nmethods;

import java.io.PrintWriter;
import java.util.*;

/**
 * Вычисляет минимум квадратичных функций, используя три метода:
 * градиентного спуска, наискорейшего спуска, сопряженных градиентов
 */
public class Evaluator {

    Set<AbstractNMethod> methods;

    public Evaluator(QuadraticFunction fun, Vector start) {
        methods = new HashSet<>(List.of(
                new GradientMethod(fun, "Gradient descent", start),
                new FastGradientMethod(fun, "Fast gradient descent", start),
                new ConjugateGradientMethod(fun, "Conjugate gradient descent", start)));
    }

//    private void print(AbstractNMethod method, PrintWriter pw) {
//        pw.println(method.name + " method");
//        pw.printf("%.4f%n", method.computeMin());
//    }

//    public void evaluate() {
//        PrintWriter pw = new PrintWriter(System.out);
//        for (AbstractNMethod method : methods) {
//            print(method, pw);
//        }
//        pw.close();
//
//    }

    /**
     * Генерирует типовую квадратичную функцию для тестирования программы
     */
    private static QuadraticFunction getQuadraticFunction(int n, int k) {
//        // 20x^2 + y^2 -7x + 3y + 2
//        List<List<Double>> a = Arrays.asList(Arrays.asList(40.0, 0.0), Arrays.asList(0.0, 2.0));
//        List<Double> b = Arrays.asList(-7.0, 3.0);
//        double c = 2.0;
//        return new QuadraticFunction(a, b, c);
        List<Double> diagonal = new ArrayList<>(n);
        diagonal.add(1.0);
        Random random = new Random();
        for (int i = 1; i < n - 1; ++i) {
            diagonal.add(1.0 + (k - 1.0) * random.nextDouble());
        }
        diagonal.add((double) k);
        Collections.sort(diagonal);
        return new QuadraticFunction(Collections.singletonList(diagonal), Collections.nCopies(n, 0.0), 0.0, true);
    }

    public static void main(String[] args) {
        for (int n = 10; n <= 10000; n *= 10) {
            for (int k : Arrays.asList(10, 100, 300, 700, 1000, 1200)) {
                int sum = 0;
                for (int i = 0; i < 5; i++) {
                    QuadraticFunction fun = getQuadraticFunction(n, k);
                    Vector start = Vector.getRandomVector(n, 6);
                    AbstractNMethod method = new ConjugateGradientMethod(fun, "Conjugate gradient descent", start);
                    sum += method.computeMin();
                }
                System.out.println("n = " + n + " k = " + k + " iter = " + sum/5);
            }
        }
//        evaluator.evaluate();
    }
}
