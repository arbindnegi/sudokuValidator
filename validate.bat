@echo off
set "defaultFileName=puzzleName.txt"
set /p userFileName="Enter file name [%defaultFileName%]: "
if "%userFileName%"=="" set "userFileName=%defaultFileName%"
echo Using file name: %userFileName%

java -cp "target/SudokuValidator-1.0-SNAPSHOT.jar" com.sudoku.main.Main %userFileName%
pause