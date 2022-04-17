import java.util.Scanner;
import java.util.stream.IntStream;

public class Wordle {
    public static void main(String[] args) {
        final String lösungswort = "TASSE";
        var scanner = new Scanner(System.in);
        var input = "";
        var versuche = 6;
        while (versuche > 0) {
            System.out.println("Bitte geben Sie nur 5 Buchstaben ein");
            input = scanner.nextLine().toUpperCase();
            if (input.length() == lösungswort.length()) {
                var result = wordle(input, lösungswort);
                System.out.println(result);
                --versuche;
            } else {
                System.out.println("Fehler: Bitte geben Sie nur 5 Buchstaben ein");
            }
        }

        scanner.close();

    }

    static String wordle(String vermutung, String lösung) {
        var isEqual = lösung.equalsIgnoreCase(vermutung);
        if (isEqual) {
            System.out.println(lösung);
            System.out.println("You guessed right!");
            System.exit(0);
        }
        var sb = new StringBuilder();
        IntStream.range(0, vermutung.length())
                .map(i -> (lösung.charAt(i) == vermutung.charAt(i)) ? vermutung.charAt(i)
                        : (!(lösung.indexOf(vermutung.charAt(i)) < 0) ? '?' : '!'))
                .forEach(sb::appendCodePoint);
        return sb.toString();
    }

}