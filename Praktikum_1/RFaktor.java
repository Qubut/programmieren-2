import java.util.InputMismatchException;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntConsumer;

public class RFaktor {
    public static void main(String[] args) {
        assert args.length == 3 : "falsche Parameteranzahl";
        try {
            final var iterations = Integer.parseInt(args[2]);
            DoubleBinaryOperator calculateInfected = (infected, rfactor) -> infected * rfactor;
            IntConsumer c = (var it) -> {
                var infected = 0d;
                var rfactor = 0d;
                try {
                    infected = Double.parseDouble(args[0]);
                    rfactor = Double.parseDouble(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("number of infected ist eine Zahl!\n rfactor ist eine Zahl!\n");
                    System.exit(1);
                }
                System.out.println(String.format("Initial %s", infected));
                for (var i = 1; i <= it; ++i) {

                    infected = calculateInfected.applyAsDouble(infected, rfactor);
                    System.out.println(String.format("Iteration %s: %s", i, infected));
                    if (infected < 0.1) {
                        System.out.println("Pandemic is over!");
                        break;
                    }
                }
            };
            c.accept(iterations);
        } catch (InputMismatchException e) {
            System.out.println("Iterations ist eine Zahl!");
            System.exit(1);
        }

    }
}