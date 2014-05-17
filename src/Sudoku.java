import java.io.File;
import java.util.Scanner;

public class Sudoku {

	static final int SIZE = 9; // total number of rows & total number of columns
	static final int SUBSIZE = (int) Math.sqrt(SIZE); // total number of subsquares
	static long startTime = 0;// to keep track of total runtime
	static long endTime = 0;// to keep track of total runtime
	static int totalSudokus = 0; // total number of Sudoku's to solve at once
	static double totalTime = 0.0; // keep track of total runtime to solve all Sudoku's
	static int grid[][]; // holds entire Sudoku grid

	static int xVar, yVar; // indexes to keep track of current row & column

	static Scanner reader;

	public static void main(String[] args) {

		initialize();

		while (reader.hasNext()) { // While: repeat until all Sudokus solved

			readInput(); // read a Sudoku grid

			startTime = System.nanoTime(); // start timer

			if (solveSudoku(grid)) // if puzzle is solvable, print
				printGrid();
			else
				// if puzzle cannot be solved
				System.out.println("No solution exists");

			endTime = System.nanoTime(); // end timer

			// calculate time to complete a Sudoku
			totalTime += ((endTime - startTime) / 1000000);

			// print time to complete a Sudoku
			System.out.println("\nTime: " + ((endTime - startTime) / 1000000) + "ms\n");

		}// While: repeat until all Sudokus solved

		// print totalTime to complete all Sudokus
		System.out.println("Total Time: " + totalTime + "ms  Total Average: " + totalTime / totalSudokus
				+ "ms");

	}

	/**
	 * Just initializes scanner to read data file
	 */
	private static void initialize() {
		reader = new Scanner(System.in);
		int dataSet = 0;
		System.out.println("Backtracking Search Algorithm");
		System.out.println("Select set of puzzles:  (1: 18 puzzles) , (2: 2 hardest puzzles)");
		dataSet = reader.nextInt();

		try {
			if (dataSet == 1)
				reader = new Scanner(new File("data1.txt"));
			if (dataSet == 2)
				reader = new Scanner(new File("data2.txt"));
		} catch (Exception e) {

		}
	}

	/**
	 * Reads data file and fills in grid array appropriately. Also prints
	 * starting grid to console
	 */
	private static void readInput() {
		totalSudokus++;
		grid = new int[SIZE][SIZE];
		System.out.println("Puzzle #" + totalSudokus + ":");
		try {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					grid[i][j] = reader.nextInt();
					System.out.print(grid[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("\nResult:");

		} catch (Exception e) {

		}

	}

	/**
	 * Prints solution to a Sudoku
	 */
	private static void printGrid() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}

	}

	/**
	 * Main loop for backtracking search algorithm. takes starting grid and
	 * returns true if grid is solved or false if grid cannot be solved.
	 * 
	 */
	private static boolean solveSudoku(int[][] grid) {

		int row = 0, col = 0;

		// if no more variables, success, finished!
		if (!getVariable())
			return true;

		row = xVar;
		col = yVar;

		// get values for a variable
		int[] domain = getValues();

		for (int i = 0; i < domain.length; i++) {// for (all values)

			if (isSafe(row, col, domain[i])) {// if (value can fit in variable)

				grid[row][col] = domain[i];

				// recursion & returning if successful
				if (solveSudoku(grid))
					return true;

				// branch failed; reset to zero, then try another value
				grid[row][col] = 0;

			}// if (value can fit in variable)

		}// for (all values)

		return false; // all values tried without success: backtrack
	}

	private static boolean isSafe(int row, int col, int value) {

		// return true, if value is not used yet
		return !UsedInRow(row, value) && !UsedInCol(col, value)
				&& !UsedInBox(row - (row % SUBSIZE), col - (col % SUBSIZE), value);
	}

	private static boolean UsedInRow(int row, int value) {
		// return true if value used in row
		for (int c = 0; c < SIZE; c++)
			if (grid[row][c] == value)
				return true;

		return false;
	}

	private static boolean UsedInCol(int col, int value) {
		// return true if value used in column
		for (int r = 0; r < SIZE; r++)
			if (grid[r][col] == value)
				return true;

		return false;
	}

	private static boolean UsedInBox(int startRow, int startCol, int value) {
		for (int r = 0; r < SUBSIZE; r++)
			for (int c = 0; c < SUBSIZE; c++)
				if (grid[r + startRow][c + startCol] == value)
					return true;

		return false;
	}

	/**
	 * Returns a array of values to try out. In this default case, this method
	 * just returns digits 1-9. This method would change if using
	 * least-constraining-value heuristic or other technique.
	 */
	private static int[] getValues() {
		return new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }; // default

	}

	/**
	 * This method finds the first possible square that has not been filled with
	 * a value yet. Returns false if the Sudoku is complete. In alternative
	 * cases, for example when using minimum-remaining-values heuristic, this
	 * method would then find the square with least possible values and return
	 * false if the Sudoku is complete
	 * 
	 * @return true: there is a square that has not been filled yet
	 * 
	 *         false: all square filled. Sudoku complete
	 */
	private static boolean getVariable() {// default
		// automatically set row & col to best attribute
		for (int r = 0; r < SIZE; r++)
			for (int c = 0; c < SIZE; c++)
				if (grid[r][c] == 0) {
					xVar = r;
					yVar = c;
					return true;

				}

		return false;
	}
}
