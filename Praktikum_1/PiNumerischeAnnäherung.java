import java.util.function.DoubleBinaryOperator;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;

public class PiNumerischeAnnäherung {
    public static void main(String[] args) {
        final var difference = 1e-10; // 10^-5
        DoubleBinaryOperator calculatePoint = (double x, double y) -> Math.sqrt(x * x + y * y);
        DoublePredicate isInCircle = (length) -> length <= 1d;
        double[] values = monteCarl(calculatePoint, isInCircle, Math::random, difference);
        System.out.println(String.format("Iteration %s: %s", values[1], values[0]));
    }

    public static double[] monteCarl(DoubleBinaryOperator biOp, DoublePredicate p, DoubleSupplier s,
            final double diff) {
        var inSquare = 0d; // Anzahl der Punkte im Quadrat
        var inCircle = 0d; // Anzahle der Punkter im Kreis
        var approxPi = 0d; // angenährter Wert von PI
        var i = 0; // Anzahl der Iterationen
        do {// einen Punkt berechnen
            double point = biOp.applyAsDouble(s.getAsDouble(), s.getAsDouble());
            // liegt er innerhalb des Kreises
            if (p.test(point))
                ++inCircle;
            ++inSquare;
            if (inCircle != 0 && inSquare != 0)
                approxPi = 4 * inCircle / inSquare;
            ++i;
        } while (Math.abs(approxPi - Math.PI) > diff || (inSquare == 0 || inCircle == 0));
        double[] values = { approxPi, (double) i };
        return values;
    }
}