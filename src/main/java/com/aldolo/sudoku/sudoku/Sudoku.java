package com.aldolo.sudoku.sudoku;

import java.util.*;

public class Sudoku {
    
    public static final Map<Integer, List<Integer>> SUB_MATRIX_INDEXES = new HashMap<>();
    static {
        SUB_MATRIX_INDEXES.put(0, Arrays.asList(0,1,2,9,10,11,18,19,20));
        SUB_MATRIX_INDEXES.put(1, Arrays.asList(3,4,5,12,13,14,21,22,23));
        SUB_MATRIX_INDEXES.put(2, Arrays.asList(6,7,8,15,16,17,24,25,26));
        SUB_MATRIX_INDEXES.put(3, Arrays.asList(27,28,29,36,37,38,45,46,47));
        SUB_MATRIX_INDEXES.put(4, Arrays.asList(30,31,32,39,40,41,48,49,50));
        SUB_MATRIX_INDEXES.put(5, Arrays.asList(33,34,35,42,43,44,51,52,53));
        SUB_MATRIX_INDEXES.put(6, Arrays.asList(54,55,56,63,64,65,72,73,74));
        SUB_MATRIX_INDEXES.put(7, Arrays.asList(57,58,59,66,67,68,75,76,77));
        SUB_MATRIX_INDEXES.put(8, Arrays.asList(60,61,62,69,70,71,78,79,80));
    }
    private List<Integer> fullList;
    public Sudoku() {
    }

    public Sudoku(List<Integer> fullList) {
        this.fullList = new ArrayList<>(fullList);
    }

    public List<Integer> getFullList() {
        return fullList;
    }

    public void setFullList(List<Integer> fullList) {
        this.fullList = fullList;
    }

    private List<Integer> getRow(int rowNumber) {
        return new ArrayList<>(getFullList().subList(Math.min(rowNumber * 9, getFullList().size()), Math.min(rowNumber * 9 + 9, getFullList().size())));
    }

    private List<Integer> getColumn(int colNumber) {
        List<Integer> column = new ArrayList<>();
        for (int i = colNumber; i < getFullList().size(); i += 9) {
            column.add(getFullList().get(i));
        }
        return column;
    }

    public List<Integer> getSubMatrix(int subMatrixNumber) {
        List<Integer> subMatrix = new ArrayList<>();
        for (int i : SUB_MATRIX_INDEXES.get(subMatrixNumber)) {
            if (i < getFullList().size()) {
                subMatrix.add(getFullList().get(i));
            }
        }
        return subMatrix;
    }

    public Map<Integer, List<Integer>> getAsRowMap() {
        Map<Integer, List<Integer>> rowMap = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            rowMap.put(i, getRow(i));
        }
        return rowMap;
    }

    public Map<Integer, List<Integer>> getAsColumnMap() {
        Map<Integer, List<Integer>> colMap = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            colMap.put(i, getColumn(i));
        }
        return colMap;
    }

    public Map<Integer, List<Integer>> getAsSubMatrixMap() {
        Map<Integer, List<Integer>> subMatrixMap = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            subMatrixMap.put(i, getSubMatrix(i));
        }
        return subMatrixMap;
    }

    public boolean isComplete() {
        return getFullList().size() == 81;
    }

    public boolean isValid() {
        if (!isComplete()) {
            return false;
        } else if (Collections.max(getFullList()) > 9 || Collections.min(getFullList()) < 1) {
            return false;
        }

        Set<Integer> comparisonSet;
        for (List<Integer> row : getAsRowMap().values()) {
            comparisonSet = new HashSet<>(row);
            if (row.size() != comparisonSet.size()) {
                return false;
            }
        }
        for (List<Integer> column : getAsColumnMap().values()) {
            comparisonSet = new HashSet<>(column);
            if (column.size() != comparisonSet.size()) {
                return false;
            }
        }
        for (List<Integer> subMatrix : getAsSubMatrixMap().values()) {
            comparisonSet = new HashSet<>(subMatrix);
            if (subMatrix.size() != comparisonSet.size()) {
                return false;
            }
        }

        return true;
    }
}
