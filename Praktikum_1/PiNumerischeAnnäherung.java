import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoublePredicate;
import java.util.stream.Stream;
/**
An implementation of the Monte Carlo algorithm for PI approximation
@param diff abs(calculatePI-Math.PI)
@param rand random number generator
@param calculatePoint lambda expression to estimate the length to point(x,y)
@param calculatePI lambda expression to calculate PI approximation 
@param isInCircle  a predicate to check if point is inside the circle
@param counter keeps track of iterations number
@param approx the approximation value of PI
*/
public class PiNumerischeAnnäherung {
    public static void main(String[] args) {
        final var diff = 1e-5; // 10^-5
        var rand = new Random();
        DoubleBinaryOperator calculatePoint = (double x, double y) -> Math.sqrt(x * x + y * y);
        DoubleBinaryOperator calculatePI = (double N_Sq, double N_Ci) -> 4.0*N_Ci/N_Sq;
        DoublePredicate isInCircle = (length) -> length <= 1d;
        var counter = new AtomicInteger(0);
        final var approx = Stream.iterate(new int[] { 1, 1 },
                n -> (Math.abs(calculatePI.applyAsDouble(n[0], n[1]) - Math.PI) > diff), (n) -> {
                    double point = calculatePoint.applyAsDouble(rand.nextDouble(), rand.nextDouble());
                    counter.getAndIncrement();
                    return new int[] { ++n[0], (isInCircle.test(point)) ? ++n[1] : n[1] };
                })
                .reduce((first, second) -> second) // reduce to only last stream element
                .map(n->calculatePI.applyAsDouble(n[0],n[1]))
                .get();
        System.out.println(String.format("Iteration %s: %s", counter.get(), approx));
    }
}
