package com.sudoku;

import com.sudoku.exception.CustomException;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolutionTest {
    // Mock implementation of CustomException for testing purposes
    private static class MockCustomException extends CustomException {
        public MockCustomException(String message) {
            super(message);
        }
    }

    @Test
    void testValidSudokuFile() {
        try {

            String validFilePath = SudokuSolutionTest.class.getClassLoader().getResource("valid.txt").getPath();
            List<List<Integer>> solution = SudokuSolution.getInputFromFile(validFilePath);

            // Add assertions to validate the content of the solution
            assertNotNull(solution);
            assertEquals(9, solution.size());

            for (List<Integer> row : solution) {
                assertNotNull(row);
                assertEquals(9, row.size());
                for (Integer value : row) {
                    assertNotNull(value);
                    assertTrue(value >= 1 && value <= 9);
                }
            }

        } catch (MockCustomException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testInvalidSudokuFileIncorrectNumberOfLines() {
        assertThrows(CustomException.class, () -> {
            String incorrectRows = SudokuSolutionTest.class.getClassLoader().getResource("incorrect-line.txt").getPath();
            SudokuSolution.getInputFromFile(incorrectRows);
        });
    }
}
