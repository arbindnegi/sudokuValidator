package com.sudoku;

import com.sudoku.exception.CustomException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SudokuSolution {
    private static final Pattern ROW_PATTERN = Pattern.compile("([1-9],){8}[1-9]");

    public static List<List<Integer>> getInputFromFile(String filepath) throws CustomException {
        File file = new File(filepath);
        if (!file.exists() || file.isDirectory()) {
            throw new CustomException("File " + file.getName() + " doesn't exist or it's a directory");
        }
        List<List<Integer>> solution = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            if (lines.size() != 9) {
                throw new CustomException("Input data has invalid number of lines: " + lines.size() + " (should be 9)");
            }
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                Matcher matcher = ROW_PATTERN.matcher(line);
                if (!matcher.matches()) {
                    throw new CustomException("Line " + (i + 1) + " should match the regex \"" + ROW_PATTERN.pattern() + "\", but it didn't: " + line);
                }
                String[] values = line.split(",");
                List<Integer> row = new ArrayList<>();
                for (String value : values) {
                    row.add(Integer.parseInt(value));
                }
                solution.add(row);
            }
        } catch (IOException e) {
            throw new CustomException("Failed to read file: " + e.getMessage());
        }
        return solution;
    }

    public static void writeSolutionToExcel(List<List<Integer>> solution, String outputFileName) throws CustomException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("SudokuSolution");

            int rowNum = 0;
            for (List<Integer> row : solution) {
                Row excelRow = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Integer value : row) {
                    Cell cell = excelRow.createCell(colNum++);
                    cell.setCellValue(value);
                }
            }

            // Get the project's root directory
            Path rootPath = Paths.get("").toAbsolutePath();
            String outputPath = rootPath.resolve(outputFileName).toString();

            try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            throw new CustomException("Failed to write Excel file: " + e.getMessage());
        }
    }
}
