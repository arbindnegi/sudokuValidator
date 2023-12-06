package com.sudoku;

import com.sudoku.exception.CustomException;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuValidatorTest {

    @Test
    void testValidSudokuSolution() {
        assertDoesNotThrow(() -> {
            List<List<Integer>> validSolution = createValidSudokuSolution();
            assertTrue(SudokuValidator.validate(validSolution));
        });
    }

    @Test
    void testValidSudokuSolutionWithEmptyCells() {
        assertDoesNotThrow(() -> {
            List<List<Integer>> validSolution = createValidSudokuSolutionWithEmptyCells();
            assertTrue(SudokuValidator.validate(validSolution));
        });
    }

    @Test
    void testDuplicateValueInRow() {
        assertThrows(CustomException.class, () -> {
            List<List<Integer>> solution = createValidSudokuSolution();
            solution.get(0).set(1, solution.get(0).get(0)); // Introduce a duplicate value in the first row
            SudokuValidator.validate(solution);
        });
    }

    @Test
    void testDuplicateValueInColumn() {
        assertThrows(CustomException.class, () -> {
            List<List<Integer>> solution = createValidSudokuSolution();
            solution.get(1).set(0, solution.get(0).get(0)); // Introduce a duplicate value in the first column
            SudokuValidator.validate(solution);
        });
    }

    @Test
    void testDuplicateValueInSquare() {
        assertThrows(CustomException.class, () -> {
            List<List<Integer>> solution = createValidSudokuSolution();
            solution.get(1).set(1, solution.get(0).get(0)); // Introduce a duplicate value in the top-left square
            SudokuValidator.validate(solution);
        });
    }

    @Test
    void testIncompleteSudokuSolution() {
        assertDoesNotThrow(() -> {
            List<List<Integer>> solution = createValidSudokuSolution();
            solution.get(0).set(0, 0); // Make the first cell empty
            assertTrue(SudokuValidator.validate(solution));
        });
    }

    @Test
    void testInvalidSudokuSolutionNonIntegerValues() {
        assertThrows(CustomException.class, () -> {
            List<List<Integer>> invalidSolution = createValidSudokuSolution();
            invalidSolution.get(0).set(0, 3); // Introduce a non-integer value
            SudokuValidator.validate(invalidSolution);
        });
    }

    @Test
    void testInvalidSudokuSolutionNullInput() {
        assertThrows(NullPointerException.class, () -> SudokuValidator.validate(null));
    }

    private List<List<Integer>> createValidSudokuSolution() {
        // Replace this with an actual valid 9x9 Sudoku solution
        return Arrays.asList(
                Arrays.asList(5, 3, 4, 6, 7, 8, 9, 1, 2),
                Arrays.asList(6, 7, 2, 1, 9, 5, 3, 4, 8),
                Arrays.asList(1, 9, 8, 3, 4, 2, 5, 6, 7),
                Arrays.asList(8, 5, 9, 7, 6, 1, 4, 2, 3),
                Arrays.asList(4, 2, 6, 8, 5, 3, 7, 9, 1),
                Arrays.asList(7, 1, 3, 9, 2, 4, 8, 5, 6),
                Arrays.asList(9, 6, 1, 5, 3, 7, 2, 8, 4),
                Arrays.asList(2, 8, 7, 4, 1, 9, 6, 3, 5),
                Arrays.asList(3, 4, 5, 2, 8, 6, 1, 7, 9)
        );
    }

    private List<List<Integer>> createValidSudokuSolutionWithEmptyCells() {
        // Replace this with an actual valid 9x9 Sudoku solution with empty cells (0)
        return Arrays.asList(
                Arrays.asList(5, 3, 4, 6, 7, 8, 9, 1, 2),
                Arrays.asList(6, 7, 2, 1, 9, 5, 3, 4, 8),
                Arrays.asList(1, 9, 8, 3, 4, 2, 5, 6, 7),
                Arrays.asList(8, 5, 9, 7, 6, 1, 4, 2, 3),
                Arrays.asList(4, 2, 6, 8, 5, 3, 7, 9, 1),
                Arrays.asList(7, 1, 3, 9, 2, 4, 8, 5, 6),
                Arrays.asList(9, 6, 1, 5, 3, 7, 2, 8, 4),
                Arrays.asList(2, 8, 7, 4, 1, 9, 6, 3, 5),
                Arrays.asList(3, 4, 5, 2, 8, 6, 1, 7, 0) // One empty cell
        );
    }
}


