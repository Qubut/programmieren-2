
package Sudoku;

import java.util.Arrays;

import org.checkerframework.checker.units.qual.s;

public class Sudoku {
    final static int GRID_SIZE = 9;
    public static void main(String[] args) {
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
    public static void printSudoku(int[][] sudoku){
        for(int row=0; row<GRID_SIZE;++row){
            if(row%3==0){
                System.out.println("+ - - - - - - -+ - - - - - - -+ - - - - - - -+");
            }
            for(int column=0;column<GRID_SIZE;++column){
                if(column%3==0){
                    System.out.print("|");
                }
                System.out.print(sudoku[row][column]);
            }
        }
    }
    public static boolean checkIfValidSudokuMatrix(int [][] sudoku){
        if(sudoku==null) 
            throw new IllegalArgumentException("can not be null");
        if(sudoku[0].length!=GRID_SIZE) 
            throw new IllegalArgumentException("must be 9x9 array") ;
        if(Arrays.stream(sudoku).anyMatch(zeile->zeile==null||zeile.length!=9)) 
            throw new IllegalArgumentException("no row can be null or empty");
        if(Arrays.stream(sudoku).allMatch(zeile->Arrays.stream(zeile).allMatch(num->num>=0 && num<=9))) 
            return true;
        throw new IllegalArgumentException("entries must be between [0,9]");      

    }
    public static boolean isValidEntry(int [][] sudoku,int row, int column, int entry){
        if(checkValidSudokuMatrix(sudoku)){
            if(row>=0 && column>=0 && entry >=0 && entry <=9){
            // checking the row
            Arrays.stream(sudoku[row])
            }
        }
        return false;
    }
}