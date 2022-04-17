import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoublePredicate;
import java.util.stream.Stream;

public class PiNumerischeAnnäherung {
    public static void main(String[] args) {
        final var diff = 1e-5; // 10^-5
        var rand = new  Random();
        DoubleBinaryOperator calculatePoint = (double x, double y) -> Math.sqrt(x * x + y * y);
        DoublePredicate isInCircle = (length) -> length <= 1d;
        var counter = new AtomicInteger(0);
        final var approx = Stream.iterate(new int[] { 1, 1 }, (n) -> {
            double point = calculatePoint.applyAsDouble(rand.nextDouble(), rand.nextDouble());
            counter.getAndIncrement();
            return new int[] { ++n[0], (isInCircle.test(point)) ? ++n[1] : n[1] };
        }).takeWhile(
                n -> (Math.abs(4.0 * (double) n[1] / (double) n[0] - Math.PI) > diff))
                .reduce((first, second) -> second) // reduce to only last stream element
                .map(n -> (4.0 * (double) n[1] / (double) n[0]))
                .get();
        System.out.println(String.format("Iteration %s: %s", counter.get(), approx));
    }
}