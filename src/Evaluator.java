import java.io.PrintWriter;
import java.util.*;

public class Evaluator {

    Set<AbstractMethod> methods;

    public Evaluator(MyFunction fun, ArrayList<Double> x) {
        methods = new HashSet<>(List.of(new GradientMethod(fun, x)));
    }

    private void print(AbstractMethod method, PrintWriter pw) {
        pw.println(method.name + " method");
        pw.println(method.computeMin().toString());
    }

    public void evaluate() {
        PrintWriter pw = new PrintWriter(System.out);
        for (AbstractMethod method : methods) {
            print(method, pw);
        }
        pw.close();
    }

    public static void main(String[] args) {
        Evaluator evaluator = new Evaluator(new MyFunction(
                new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(64.0, 64.0)), new ArrayList<>(Arrays.asList(64.0, 64.0)))),
                new ArrayList<>(Arrays.asList(-10.0, 30.0)), 13.0, false), new ArrayList<>(Arrays.asList(1000.0, 1000.0)));
        evaluator.evaluate();
    }
}
