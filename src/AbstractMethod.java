import java.util.ArrayList;

public abstract class AbstractMethod {

    final double EPSILON = 29;

    String name;

    final MyFunction function;
    ArrayList<Double> currentX;

    AbstractMethod(MyFunction function, ArrayList<Double> x, String name) {
        this.function = function;
        currentX = (ArrayList<Double>) x.clone();
        this.name = name;
    }

    public ArrayList<Double> computeMin() {
        while (cycleCondition()) {
            iterate();
            System.err.print(currentX.toString());
            System.err.printf("%.5f%n", function.getValue(currentX));
        }
        return currentX;
    }

    abstract void iterate();

    abstract boolean cycleCondition();
}
