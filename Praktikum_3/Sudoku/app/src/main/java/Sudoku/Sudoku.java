package Sudoku;

import java.util.function.IntUnaryOperator;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

public class Sudoku {
  final static int GRID_SIZE = 9;

  public static void main(String[] args) {
    var sudoku = getExample();
    var scanner = new Scanner(System.in);
    var emptyFields = getEmptyFields(sudoku);
    System.out.println("Hello world");
    while (!emptyFields.isEmpty()) {
      printSudoku(sudoku);

      System.out.print("wählen Sie eine Reihe aus: ");
      var row = scanner.nextInt();
      scanner.nextLine();

      System.out.print("wählen Sie eine Spalte aus: ");
      var column = scanner.nextInt();
      scanner.nextLine();

      System.out.print("Geben Sie einen Eintrag zwischen [1-9] ein: ");
      var entry = scanner.nextInt();
      scanner.nextLine();

      if (sudoku[row][column] == 0 && isValidEntry(sudoku, row, column, entry)) {
        sudoku[row][column] = entry;
        emptyFields.pop();
      } else
        System.out.println("Ungültig!");

    }
    scanner.close();

  }

  public static int[][] getExample() {
    return new int[][] {
        { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
        { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
        { 0, 9, 8, 0, 0, 0, 0, 6, 0 },

        { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
        { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
        { 7, 0, 0, 0, 2, 0, 0, 0, 6 },

        { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
        { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
        { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
    };
  }

  public static void printSudoku(int[][] sudoku) {

    final String separator = "  +-------+-------+-------+%n";

    System.out.printf("    0 1 2   3 4 5   6 7 8%n");

    for (int row = 0; row < sudoku.length; row++) {

      if (row % 3 == 0)
        System.out.printf(separator);

      System.out.printf("%d", row);

      for (int column = 0; column < sudoku[row].length; column++) {

        if (column % 3 == 0)
          System.out.printf(" |");

        if (sudoku[row][column] == 0)
          System.out.printf("  ");
        else
          System.out.printf("%2d", sudoku[row][column]);

      }

      System.out.printf(" |\n");

    }

    System.out.printf(separator);

  }

  public static boolean checkValidSudokuMatrix(int[][] sudoku) {
    if (sudoku == null)
      throw new IllegalArgumentException("sudoku can not be null");
    if (sudoku.length != 9)
      throw new IllegalArgumentException("sudoku must have 9 rows");
    if (Arrays.stream(sudoku).anyMatch(zeile -> zeile == null || zeile.length != 9))
      throw new IllegalArgumentException("a row cannot be null and it's length must be " + 9);
    if (Arrays.stream(sudoku).anyMatch(zeile -> Arrays.stream(zeile).anyMatch(num -> num < 0 || num > 9)))
      throw new IllegalArgumentException("entries cannot be negative or more than " + 9);
    return true;

  }

  public static boolean isValidEntry(int[][] sudoku, int row, int column, int entry) {
    IntUnaryOperator getSeed = (n) -> n >= 3 ? n - n % 3 : 0;
    var initialSubRow = getSeed.applyAsInt(row);
    var initialSubColumn = getSeed.applyAsInt(column);
    if (row < 0 || column < 0 || row > 8 || column > 8)
      throw new IllegalArgumentException("a row or a column cannot be negativ or more than" + (8));
    if (entry < 0 || entry > 9)
      throw new IllegalArgumentException("entry cannot be negative or more than 9");

    if (IntStream.rangeClosed(0, 8).anyMatch(n -> sudoku[row][n] == entry && n != column))
      return false;
    if (IntStream.rangeClosed(0, 8).anyMatch(n -> sudoku[n][column] == entry && n != row))
      return false;
    if (IntStream.rangeClosed(initialSubRow, initialSubRow + 2)
        .anyMatch(i -> IntStream.rangeClosed(initialSubColumn, initialSubColumn + 2)
            .anyMatch(j -> sudoku[i][j] == entry && i != row && j != column)))
      return false;
    return true;
  }

  public static boolean isSolution(int[][] solution) {
    checkValidSudokuMatrix(solution);
    var sudoku = getExample();
    for (int i = 0; i < sudoku.length; i++) {
      for (int j = 0; j < sudoku.length; j++) {
        if (solution[i][j] == 0)
          return false;
        if (sudoku[i][j] != 0 && (solution[i][j] != solution[i][j]))
          return false;
        if (!isValidEntry(solution, i, j, solution[i][j]))
          return false;

      }

    }
    return true;
  }

  private static Stack<int[]> getEmptyFields(int[][] sudoku) {
    var stack = new Stack<int[]>();
    IntStream.rangeClosed(0, 8)
        .forEach(i -> {
          IntStream.rangeClosed(0, 8).filter(j -> {
            return sudoku[i][j] == 0;
          })
              .forEach(j -> {
                stack.push(new int[] { i, j });
              });
        });
    return stack;
  }

}
