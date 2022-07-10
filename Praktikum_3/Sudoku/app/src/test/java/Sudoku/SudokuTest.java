package Sudoku;

import static org.junit.Assert.*;
import org.junit.*;

public class SudokuTest {

  @Test(expected = IllegalArgumentException.class)
  public void testCheckValidSudokuMatrixNull(){
    Sudoku.checkValidSudokuMatrix(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckValidSudokuMatrix0(){
    Sudoku.checkValidSudokuMatrix(new int[0][0]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckValidSudokuMatrix10(){
    Sudoku.checkValidSudokuMatrix(new int[10][10]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckValidSudokuMatrixNullRow(){
    var s = new int[10][10];
    s[4] = null;
    Sudoku.checkValidSudokuMatrix(s);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckValidSudokuMatrixInvalidColumns(){
    var s = new int[10][10];
    s[2] = new int[8];
    Sudoku.checkValidSudokuMatrix(s);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckValidSudokuMatrixInvalidEntry1(){
    var s = new int[10][10];
    s[2][5] = -1;
    Sudoku.checkValidSudokuMatrix(s);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckValidSudokuMatrixInvalidEntry2(){
    var s = new int[10][10];
    s[7][0] = 10;
    Sudoku.checkValidSudokuMatrix(s);
  }

  @Test
  public void testIsValidEntry(){

    int[][] s = new int[9][9];

    s[1][1] = 4;
    s[0][5] = 2;
    s[6][4] = 1;

    assertTrue("Entry of 1 at 1 7 possible", Sudoku.isValidEntry(s, 1, 7, 1));
    assertFalse("Entry of 4 at 1 7 is not possible", Sudoku.isValidEntry(s, 1, 7, 4));

    s[1][7] = 1;

    assertTrue("Entry of 9 at 5 7 possible", Sudoku.isValidEntry(s, 5, 7, 9));
    assertFalse("Entry of 1 at 6 7 is not possible", Sudoku.isValidEntry(s, 6, 7, 1));

    s[6][7] = 9;

    assertTrue("Entry of 2 at 7 6 possible", Sudoku.isValidEntry(s, 7, 6, 2));
    assertFalse("Entry of 9 at 8 8 is not possible", Sudoku.isValidEntry(s, 8, 8, 9));


  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsValidEntryInvalidRow(){
    Sudoku.isValidEntry(new int[9][9], -1, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsValidEntryInvalidRow2(){
    Sudoku.isValidEntry(new int[9][9], 9, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsValidEntryInvalidColumn(){
    Sudoku.isValidEntry(new int[9][9], 2, -1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsValidEntryInvalidColumn2(){
    Sudoku.isValidEntry(new int[9][9], 2, 9, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsValidEntryInvalidEntry(){
    Sudoku.isValidEntry(new int[9][9], 2, 8, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsValidEntryInvalidEntry2(){
    Sudoku.isValidEntry(new int[9][9], 2, 8, 10);
  }

  @Test
  public void testIsSolution(){
    // --- 8< ---
    int[][] s = new int[9][9];
    s[0][0]=4; s[0][1]=8; s[0][2]=3; s[0][3]=1; s[0][4]=5; s[0][5]=2; s[0][6]=9; s[0][7]=6; s[0][8]=7;
    s[1][0]=6; s[1][1]=1; s[1][2]=5; s[1][3]=9; s[1][4]=7; s[1][5]=8; s[1][6]=4; s[1][7]=3; s[1][8]=2;
    s[2][0]=7; s[2][1]=2; s[2][2]=9; s[2][3]=6; s[2][4]=3; s[2][5]=4; s[2][6]=1; s[2][7]=8; s[2][8]=5;
    s[3][0]=5; s[3][1]=6; s[3][2]=4; s[3][3]=8; s[3][4]=1; s[3][5]=9; s[3][6]=2; s[3][7]=7; s[3][8]=3;
    s[4][0]=2; s[4][1]=9; s[4][2]=1; s[4][3]=7; s[4][4]=6; s[4][5]=3; s[4][6]=5; s[4][7]=4; s[4][8]=8;
    s[5][0]=8; s[5][1]=3; s[5][2]=7; s[5][3]=2; s[5][4]=4; s[5][5]=5; s[5][6]=6; s[5][7]=1; s[5][8]=9;
    s[6][0]=1; s[6][1]=5; s[6][2]=8; s[6][3]=4; s[6][4]=2; s[6][5]=7; s[6][6]=3; s[6][7]=9; s[6][8]=6;
    s[7][0]=9; s[7][1]=4; s[7][2]=2; s[7][3]=3; s[7][4]=8; s[7][5]=6; s[7][6]=7; s[7][7]=5; s[7][8]=1;
    s[8][0]=3; s[8][1]=7; s[8][2]=6; s[8][3]=5; s[8][4]=9; s[8][5]=1; s[8][6]=8; s[8][7]=2; s[8][8]=4;
    // --- >8 ---
    
    assertTrue("must return true on solution of sudoku", Sudoku.isSolution(s));

    s[4][3] = 0;
    assertFalse("must return false on non-solution of sudoku", Sudoku.isSolution(s));

    s[4][3] = 8;
    assertFalse("must return false on non-solution of sudoku", Sudoku.isSolution(s));

  }

  @Test
  public void testGetExample(){
    
    int[][] s = Sudoku.getExample();

    assertNotNull("getExample must not return null", s);

    assertEquals("invalid number of rows", 9, s.length);

    assertEquals("invalid number of columns in row 0", 9, s[0].length);
    assertEquals("invalid number of columns in row 0", 9, s[1].length);
    assertEquals("invalid number of columns in row 0", 9, s[2].length);
    assertEquals("invalid number of columns in row 0", 9, s[3].length);
    assertEquals("invalid number of columns in row 0", 9, s[4].length);
    assertEquals("invalid number of columns in row 0", 9, s[5].length);
    assertEquals("invalid number of columns in row 0", 9, s[6].length);
    assertEquals("invalid number of columns in row 0", 9, s[7].length);
    assertEquals("invalid number of columns in row 0", 9, s[8].length);

    assertEquals("invalid entry in row 0/col 0", 5, s[0][0]);
    assertEquals("invalid entry in row 0/col 1", 3, s[0][1]);
    assertEquals("invalid entry in row 0/col 2", 0, s[0][2]);
    assertEquals("invalid entry in row 0/col 3", 0, s[0][3]);
    assertEquals("invalid entry in row 0/col 4", 7, s[0][4]);
    assertEquals("invalid entry in row 0/col 5", 0, s[0][5]);
    assertEquals("invalid entry in row 0/col 6", 0, s[0][6]);
    assertEquals("invalid entry in row 0/col 7", 0, s[0][7]);
    assertEquals("invalid entry in row 0/col 9", 0, s[0][8]);
                                                 
    assertEquals("invalid entry in row 1/col 0", 6, s[1][0]);
    assertEquals("invalid entry in row 1/col 1", 0, s[1][1]);
    assertEquals("invalid entry in row 1/col 2", 0, s[1][2]);
    assertEquals("invalid entry in row 1/col 3", 1, s[1][3]);
    assertEquals("invalid entry in row 1/col 4", 9, s[1][4]);
    assertEquals("invalid entry in row 1/col 5", 5, s[1][5]);
    assertEquals("invalid entry in row 1/col 6", 0, s[1][6]);
    assertEquals("invalid entry in row 1/col 7", 0, s[1][7]);
    assertEquals("invalid entry in row 1/col 9", 0, s[1][8]);
                                                 
    assertEquals("invalid entry in row 2/col 0", 0, s[2][0]);
    assertEquals("invalid entry in row 2/col 1", 9, s[2][1]);
    assertEquals("invalid entry in row 2/col 2", 8, s[2][2]);
    assertEquals("invalid entry in row 2/col 3", 0, s[2][3]);
    assertEquals("invalid entry in row 2/col 4", 0, s[2][4]);
    assertEquals("invalid entry in row 2/col 5", 0, s[2][5]);
    assertEquals("invalid entry in row 2/col 6", 0, s[2][6]);
    assertEquals("invalid entry in row 2/col 7", 6, s[2][7]);
    assertEquals("invalid entry in row 2/col 9", 0, s[2][8]);
                                                 
    assertEquals("invalid entry in row 3/col 0", 8, s[3][0]);
    assertEquals("invalid entry in row 3/col 1", 0, s[3][1]);
    assertEquals("invalid entry in row 3/col 2", 0, s[3][2]);
    assertEquals("invalid entry in row 3/col 3", 0, s[3][3]);
    assertEquals("invalid entry in row 3/col 4", 6, s[3][4]);
    assertEquals("invalid entry in row 3/col 5", 0, s[3][5]);
    assertEquals("invalid entry in row 3/col 6", 0, s[3][6]);
    assertEquals("invalid entry in row 3/col 7", 0, s[3][7]);
    assertEquals("invalid entry in row 3/col 9", 3, s[3][8]);
                                                 
    assertEquals("invalid entry in row 4/col 0", 4, s[4][0]);
    assertEquals("invalid entry in row 4/col 1", 0, s[4][1]);
    assertEquals("invalid entry in row 4/col 2", 0, s[4][2]);
    assertEquals("invalid entry in row 4/col 3", 8, s[4][3]);
    assertEquals("invalid entry in row 4/col 4", 0, s[4][4]);
    assertEquals("invalid entry in row 4/col 5", 3, s[4][5]);
    assertEquals("invalid entry in row 4/col 6", 0, s[4][6]);
    assertEquals("invalid entry in row 4/col 7", 0, s[4][7]);
    assertEquals("invalid entry in row 4/col 9", 1, s[4][8]);
                                                 
    assertEquals("invalid entry in row 5/col 0", 7, s[5][0]);
    assertEquals("invalid entry in row 5/col 1", 0, s[5][1]);
    assertEquals("invalid entry in row 5/col 2", 0, s[5][2]);
    assertEquals("invalid entry in row 5/col 3", 0, s[5][3]);
    assertEquals("invalid entry in row 5/col 4", 2, s[5][4]);
    assertEquals("invalid entry in row 5/col 5", 0, s[5][5]);
    assertEquals("invalid entry in row 5/col 6", 0, s[5][6]);
    assertEquals("invalid entry in row 5/col 7", 0, s[5][7]);
    assertEquals("invalid entry in row 5/col 9", 6, s[5][8]);
                                                 
    assertEquals("invalid entry in row 6/col 0", 0, s[6][0]);
    assertEquals("invalid entry in row 6/col 1", 6, s[6][1]);
    assertEquals("invalid entry in row 6/col 2", 0, s[6][2]);
    assertEquals("invalid entry in row 6/col 3", 0, s[6][3]);
    assertEquals("invalid entry in row 6/col 4", 0, s[6][4]);
    assertEquals("invalid entry in row 6/col 5", 0, s[6][5]);
    assertEquals("invalid entry in row 6/col 6", 2, s[6][6]);
    assertEquals("invalid entry in row 6/col 7", 8, s[6][7]);
    assertEquals("invalid entry in row 6/col 9", 0, s[6][8]);
                                                 
    assertEquals("invalid entry in row 7/col 0", 0, s[7][0]);
    assertEquals("invalid entry in row 7/col 1", 0, s[7][1]);
    assertEquals("invalid entry in row 7/col 2", 0, s[7][2]);
    assertEquals("invalid entry in row 7/col 3", 4, s[7][3]);
    assertEquals("invalid entry in row 7/col 4", 1, s[7][4]);
    assertEquals("invalid entry in row 7/col 5", 9, s[7][5]);
    assertEquals("invalid entry in row 7/col 6", 0, s[7][6]);
    assertEquals("invalid entry in row 7/col 7", 0, s[7][7]);
    assertEquals("invalid entry in row 7/col 9", 5, s[7][8]);
                                                 
    assertEquals("invalid entry in row 8/col 0", 0, s[8][0]);
    assertEquals("invalid entry in row 8/col 1", 0, s[8][1]);
    assertEquals("invalid entry in row 8/col 2", 0, s[8][2]);
    assertEquals("invalid entry in row 8/col 3", 0, s[8][3]);
    assertEquals("invalid entry in row 8/col 4", 8, s[8][4]);
    assertEquals("invalid entry in row 8/col 5", 0, s[8][5]);
    assertEquals("invalid entry in row 8/col 6", 0, s[8][6]);
    assertEquals("invalid entry in row 8/col 7", 7, s[8][7]);
    assertEquals("invalid entry in row 8/col 9", 9, s[8][8]);


  }




  
}
