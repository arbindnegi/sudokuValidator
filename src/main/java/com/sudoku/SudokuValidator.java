package com.sudoku;

import com.sudoku.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

public class SudokuValidator {
      public static boolean validate(List<List<Integer>> solution) throws CustomException {
        // Validate rows
        for (int i = 0; i < solution.size(); i++) {
            List<Integer> row = solution.get(i);
            if (row.size() != row.stream().distinct().count()) {
                throw new CustomException("Row " + (i + 1) + " contains duplicates: " + row);
            }
        }
        // Validate columns
        for (int col = 0; col < 9; col++) {
            List<Integer> column = new ArrayList<>();
            for (int row = 0; row < 9; row++) {
                column.add(solution.get(row).get(col));
            }
            if (column.size() != column.stream().distinct().count()) {
                throw new CustomException("Column " + (col + 1) + " contains duplicates: " + column);
            }
        }
        // Validate 3x3 squares
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                List<Integer> square = new ArrayList<>();
                for (int row = i; row < i + 3; row++) {
                    for (int column = j; column < j + 3; column++) {
                        square.add(solution.get(row).get(column));
                    }
                }
                if (square.size() != square.stream().distinct().count()) {
                    throw new CustomException("One of the 3x3 squares contains duplicates: " + square);
                }
            }
        }

        return true;
    }
}

