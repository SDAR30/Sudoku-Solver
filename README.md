Sudoku-Solver
=============

This program uses a backtracking search algorithm to solve several different Sudoku puzzles at once. The recursive program also uses arc consistency as a pre-processing step to prevent unnecessary computations and can be adjusted with other heuristics to possibly improve efficiency. 

COMPILATION & EXECUTION

To compile and run the backtracking search algorithm, type "javac Sudoku.java" and press enter to produce a .class file in your directory. Then type "java Sudoku" and press enter to run the program. Select the set of puzzles to solve.  Press "1" to select 18 puzzles to solve of various difficulty, or press "2" to  solve 2 of the world's hardest puzzles. 

The program reads a data set of puzzles and displays them along with their solutions.  The runtime for each puzzle is displayed right below each solution and the average runtime of all puzzles is shown at the end. 

data1.txt holds the 18 puzzles of various difficulty. 
data2.txt holds 2 difficult puzzles considered to be the world's hardest Sudoku puzzles formulated by Finnish Mathematician, Arto Ankala.
data3.txt specifically holds Sudokus that generated the longest run times. 
