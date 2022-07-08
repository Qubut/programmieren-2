import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;

/**
Eine kleine Simulation der Ausbreitung oder Verschwindung von einem Virus 
unter des Effekts eines @param rfaktor
@param infected Anzahl der Infizierten
@param iterations Anzahl der Iterationen
@param counter hält die Iterationenanzahl nach
*/
public class RFaktor {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("falsche Parameteranzahl");
            System.exit(1);
        }
        ;
        try {
            // infected und rfaktor als Zahlen überprüfen
            // Iterationen als Ganzzahl überprüfen
            final var iterations = Integer.parseInt(args[2]);
            final var infected = Double.parseDouble(args[0]);
            final var rfactor = Double.parseDouble(args[1]);
            var counter = new AtomicInteger(1);
            System.out.println(String.format("Initial %s", infected));
            DoubleStream.iterate(infected, inf -> !(inf < 0.1),inf -> rfactor * inf).limit(iterations)
                    .forEach(inf -> {
                        System.out.println(String.format("Iteration %s: %s", counter.getAndIncrement(),inf));
                    // wird nicht erreicht, eine mögliche Lösung ist Guava API zu benutzen
                    // denn es hat takeUntil Methode
                        // if (inf < 0.1)
                        //     System.out.println("Pandemic is over!");
                    });
        } catch (NumberFormatException e) {
            System.out.println("Number of infected, rfactor und iterations sind Zahlen!\n");
            System.exit(1);
        }
    }
}
