package com.sudoku.main;

import com.sudoku.SudokuSolution;
import com.sudoku.SudokuValidator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("Please provide 1 argument which should be a file name");
            }
           List<List<Integer>> extractedData = SudokuSolution.getInputFromFile(args[0]);
            SudokuSolution.writeSolutionToExcel(extractedData, "sudoku-Output.xlsx");
            if(SudokuValidator.validate(extractedData)) {
                System.out.println("VALID");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("INVALID");
            System.out.println(e);
            System.exit(1);
        }
    }
}
