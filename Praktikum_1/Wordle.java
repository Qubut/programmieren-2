import java.util.Scanner;
import java.util.stream.Stream;

public class Wordle {
    public static void main(String[] args) {
        final String lösungswort = "TASSE";
        var scanner = new Scanner(System.in);
        var input = "";
        var versuche = 0;
        while (versuche < 6) {

            System.out.println("Bitte geben Sie nur 5 Buchstaben ein");
            input = scanner.nextLine().toUpperCase();
            var comparison = lösungswort.equalsIgnoreCase(input);
            if (comparison) {
                System.out.println(lösungswort);
                System.out.println("You guessed right!");
                System.exit(0);
            } else {
                if (input.length() == lösungswort.length()) {
                    var result = wordle(input, lösungswort);
                    System.out.println(result);
                } else {
                    System.out.println("Fehler: Bitte geben Sie nur 5 Buchstaben ein");
                    --versuche;
                }
            }
        }

        scanner.close();

    }

    static String wordle(String vermutung, String lösung) {
        StringBuffer result = new StringBuffer();
        for (var i = 0; i < lösung.length(); ++i) {
            if (lösung.charAt(i) == vermutung.charAt(i)) {
                result.append(lösung.charAt(i));
            } else if (!(lösung.indexOf(vermutung.charAt(i)) < 0)) {
                result.append("?");
            } else {
                result.append("!");
            }

        }
        return new String(result);
    }

}