package wordle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Wordle {
    public static void main(String[] args) throws IOException {
        final String pfad = "./src/main/resources/words.txt";
        final var pattern = Pattern.compile("[\\t\\s]");
        final var wörter = getWords(15, pfad, pattern);
        System.out.println(wörter.size());
        //  beliebiges Wort
        final String lösungswort = wörter.get((int) Math.floor(Math.random() * wörter.size()));
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

    static List<String> getWords(int minfreq, String path, Pattern pattern) {
        try (var words = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
            return words.filter(line -> {
                var entries = pattern.split(line);
                var isANoun = entries[1].equals("NoC");
                if (!isANoun) return false;
                var isFrequent = Integer.parseInt(entries[2].strip()) >= minfreq;
                if (!isFrequent) return false;
                var word = entries[0];
                var len = word.length();
                if(len!=5) return false;    
                var lastLetter = word.charAt(len - 1);
                if(lastLetter =='s') return false;
                var isValid =  word.chars()
                        .allMatch(s -> {return Character.isLetter((char)s);});
            
            if (!isValid) return false;
                return true;
            }).map(line -> pattern.split(line)[0]).collect(Collectors.toList());
        } catch (IOException e) {
            throw e;
        }

    }

}