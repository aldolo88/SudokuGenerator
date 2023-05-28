package com.aldolo.sudoku.sudoku;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class SudokuTest {

    @Test
    @DisplayName("GetAsRowMap with full list")
    public void getAsRowMap() {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> rowResult = Arrays.asList(1,2,3,4,5,6,7,8,9);
        for (int i = 0; i < 9; i++) {
            numbers.addAll(rowResult);
        }
        Sudoku testSudoku = new Sudoku(numbers);
        Map<Integer, List<Integer>> mapResult = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            mapResult.put(i, rowResult);
        }
        Assertions.assertEquals(mapResult, testSudoku.getAsRowMap(), "RowMap generation error");
    }

    @Test
    @DisplayName("GetAsRowMap with partial list")
    public void getAsRowMap_partialList() {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> rowResult = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Integer> partialRowResult = Arrays.asList(1,2,3,4,5);
        numbers.addAll(rowResult);
        numbers.addAll(partialRowResult);

        Sudoku testSudoku = new Sudoku(numbers);
        Map<Integer, List<Integer>> mapResult = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            if (i < 1) {
                mapResult.put(i, rowResult);
            } else if (i == 1) {
                mapResult.put(i, partialRowResult);
            } else {
                mapResult.put(i, new ArrayList<>());
            }
        }
        Assertions.assertEquals(mapResult, testSudoku.getAsRowMap(), "RowMap generation error");
    }

    @Test
    @DisplayName("GetAsColumnMap with full list")
    public void getAsColumnMap() {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> row = Arrays.asList(0,1,2,3,4,5,6,7,8);
        for (int i = 0; i < 9; i++) {
            numbers.addAll(row);
        }
        Sudoku testSudoku = new Sudoku(numbers);
        Map<Integer, List<Integer>> mapResult = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            mapResult.put(i, Arrays.asList(i,i,i,i,i,i,i,i,i));
        }
        Assertions.assertEquals(mapResult, testSudoku.getAsColumnMap(), "ColumnMap generation error");
    }

    @Test
    @DisplayName("GetAsColumnMap with partial list")
    public void getAsColumnMap_partialList() {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> row = Arrays.asList(0,1,2,3,4,5,6,7,8);
        List<Integer> partialRow = Arrays.asList(0,1,2,3,4);
        numbers.addAll(row);
        numbers.addAll(partialRow);

        Sudoku testSudoku = new Sudoku(numbers);
        Map<Integer, List<Integer>> mapResult = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            if (i < 5) {
                mapResult.put(i, Arrays.asList(i,i));
            } else {
                mapResult.put(i, List.of(i));
            }
        }
        Assertions.assertEquals(mapResult, testSudoku.getAsColumnMap(), "ColumnMap generation error");
    }

    @Test
    @DisplayName("GetAsColumnMap with full list")
    public void getAsSubMatrixMap() {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> row = Arrays.asList(0,1,2,3,4,5,6,7,8);
        for (int i = 0; i < 9; i++) {
            numbers.addAll(row);
        }
        Sudoku testSudoku = new Sudoku(numbers);
        Map<Integer, List<Integer>> mapResult = new HashMap<>();
        mapResult.put(0, Arrays.asList(0,1,2,0,1,2,0,1,2));
        mapResult.put(1, Arrays.asList(3,4,5,3,4,5,3,4,5));
        mapResult.put(2, Arrays.asList(6,7,8,6,7,8,6,7,8));
        mapResult.put(3, Arrays.asList(0,1,2,0,1,2,0,1,2));
        mapResult.put(4, Arrays.asList(3,4,5,3,4,5,3,4,5));
        mapResult.put(5, Arrays.asList(6,7,8,6,7,8,6,7,8));
        mapResult.put(6, Arrays.asList(0,1,2,0,1,2,0,1,2));
        mapResult.put(7, Arrays.asList(3,4,5,3,4,5,3,4,5));
        mapResult.put(8, Arrays.asList(6,7,8,6,7,8,6,7,8));
        Assertions.assertEquals(mapResult, testSudoku.getAsSubMatrixMap(), "SubMatrixMap generation error");
    }

    @Test
    @DisplayName("GetAsColumnMap with partial list")
    public void getAsSubMatrixMap_partialList() {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> row = Arrays.asList(0,1,2,3,4,5,6,7,8);
        List<Integer> partialRow = Arrays.asList(0,1,2,3,4);
        numbers.addAll(row);
        numbers.addAll(partialRow);

        Sudoku testSudoku = new Sudoku(numbers);
        Map<Integer, List<Integer>> mapResult = new HashMap<>();
        mapResult.put(0, Arrays.asList(0,1,2,0,1,2));
        mapResult.put(1, Arrays.asList(3,4,5,3,4));
        mapResult.put(2, Arrays.asList(6,7,8));
        mapResult.put(3, new ArrayList<>());
        mapResult.put(4, new ArrayList<>());
        mapResult.put(5, new ArrayList<>());
        mapResult.put(6, new ArrayList<>());
        mapResult.put(7, new ArrayList<>());
        mapResult.put(8, new ArrayList<>());
        Assertions.assertEquals(mapResult, testSudoku.getAsSubMatrixMap(), "SubMatrixMap generation error");
    }

    @Test
    @DisplayName("IsComplete with full list")
    void isComplete() {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> rowResult = Arrays.asList(1,2,3,4,5,6,7,8,9);
        for (int i = 0; i < 9; i++) {
            numbers.addAll(rowResult);
        }
        Sudoku testSudoku = new Sudoku(numbers);
        Assertions.assertTrue(testSudoku.isComplete(), "IsComplete error");
    }

    @Test
    @DisplayName("IsComplete with partial list")
    void isComplete_partialList() {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> row = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Integer> partialRow = Arrays.asList(1,2,3,4,5);
        numbers.addAll(row);
        numbers.addAll(partialRow);
        Sudoku testSudoku = new Sudoku(numbers);
        Assertions.assertFalse(testSudoku.isComplete(), "IsComplete error");
    }

    @Test
    @DisplayName("IsValid with valid option")
    void isValid() {
        Sudoku testSudoku = new Sudoku(Arrays.asList(1,2,3,4,5,6,7,8,9,4,5,6,7,8,9,1,2,3,7,8,9,1,2,3,4,5,6,2,3,4,5,6,7,8,9,1,5,6,7,8,9,1,2,3,4,8,9,1,2,3,4,5,6,7,3,4,5,6,7,8,9,1,2,6,7,8,9,1,2,3,4,5,9,1,2,3,4,5,6,7,8));
        Assertions.assertTrue(testSudoku.isValid(), "IsValid error");
    }

    @Test
    @DisplayName("IsValid with not valid option")
    void isValid_NotValidOption() {
        Sudoku testSudoku = new Sudoku(Arrays.asList(1,2,3,4,5,6,7,8,9,4,5,6,7,8,9,1,2,3,7,8,1,1,2,3,4,5,6,2,3,4,5,6,7,8,9,1,5,6,7,8,9,1,2,3,4,8,9,1,2,3,4,5,6,7,3,4,5,6,7,8,9,1,2,6,7,8,9,1,2,3,4,5,9,1,2,3,4,5,6,7,8));
        Assertions.assertFalse(testSudoku.isValid(), "IsValid error");
    }

    @Test
    @DisplayName("IsValid with incomplete option")
    void isValid_IncompleteOption() {
        Sudoku testSudoku = new Sudoku(Arrays.asList(1,2,3,4,5,6,7,8,9,4,5,6,7,8,9,1,2,3,7,8,9,1,2,3,4,5,6,2,3,4,5,6,7,8,9,1,5,6,7,8,9,1,2,3,4,8,9,1,2,3,4,5,6,7,3,4,5,6,7,8,9,1,2,6,7,8,9,1,2,3,4,5,9,1,2,3,4,5,6,7));
        Assertions.assertFalse(testSudoku.isValid(), "IsValid error");
    }
}
