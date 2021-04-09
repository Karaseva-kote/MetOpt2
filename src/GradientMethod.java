import java.util.ArrayList;
import java.util.Collections;

public class GradientMethod extends AbstractMethod {
    private Double alpha = 0.0252621;
    private boolean cycleCondition = true;

    GradientMethod(MyFunction fun, ArrayList<Double> x) {
        super(fun, x, "Gradient");
    }

    public void iterate() {
        ArrayList<Double> gradient = function.gradient(currentX);
        if (function.modV(gradient) < EPSILON) {
            cycleCondition = false;
            return;
        }
        while (true) {
            ArrayList<Double> y = function.VminusV(currentX, function.Vxa(gradient, alpha));
            if (function.getValue(y) < function.getValue(currentX)) {
                Collections.copy(currentX, y);
                break;
            } else {
                alpha /= 2;
            }
        }
    }

    @Override
    boolean cycleCondition() {
        return cycleCondition;
    }
}
