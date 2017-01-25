/**
 * This class SudokuSolve is a sub-class of Sudoku. It is used to solve any sudoku puzzle using an iterative method.
 * The input sudoku must have inputs that meet the rules of sudoku.
 * @author axd638
 * @version 2016-11-29
 */

public class SudokuSolve extends Sudoku {

	/** CONSTRUCTOR:
	 *  This constructor creates an SudokuSolve object from the super class array field variable:
	 *  @param array The array of numbers in the sudoku.
	 */
	public SudokuSolve(int[][] array) {
		super(array);
	}

	/**
	 * This solve method is an iterative method that can solve any Sudoku using backtracking.
	 * A 'checkArray' is created initially in order to check for originally input values when backtracking.
	 * The method begins at the first available position in the sudoku that is not already filled and
	 * iterates through the rows and columns using a nested 'while' loop.
	 * 'addpotentialValue' is used to test whether an input can be inserted into the sudoku.
	 * 'originalInput' is used during back tracking to iterate further through k.
	 * addpotentialValue is set to true at the beginning of each check and turns false if it fails 
	 * any of the 3 conditional checks of compareRow, compareCol or compareSqu.
	 * If the input value satisfies all 3 conditionals then the value 
	 * is inserted into the sudoku and the loop continues to the next available empty value in 'checkArray'.
	 * If however all values up to 9 are checked and any conditional returns false, addpotentialValue is false and backtracking begins.
	 * A nested reverse while loop iterates back to the last position not in the original 'checkArray'.
	 * The original input is taken and fed back into k which then iterates from this, checking if a new value can be inserted.
	 * This process repeats until the whole sudoku is completed.
	 * @param sudoku The sudoku to be completed.
	 * @return The completed sudoku.
	 */
	public static Sudoku solve(Sudoku sudoku){
		int[][] checkArray = new int[SIZE][SIZE];
		for (int r=0; r<SIZE; r++){
			for (int c=0; c<SIZE; c++){
				checkArray[r][c] = sudoku.getArray()[r][c];
			}
		}
		boolean addPotentialValue = true;
		int originalInput = 0;
		int i=0;
		while (i<SIZE){
			int j=0;
			while (j<SIZE){
				if (checkArray[i][j] == 0){
					int k=originalInput;
					originalInput = 0;
					while (k<SIZE){
						addPotentialValue = true;
						if (compareRow((k+1),i,sudoku)){
							addPotentialValue = false;
						}
						if (compareCol((k+1),j,sudoku)){
							addPotentialValue = false;
						}
						if (compareSqu((k+1),i,j,sudoku)){
							addPotentialValue = false;
						}
						if (addPotentialValue == true){
							sudoku.getArray()[i][j] = (k+1);
							k=SIZE;
						} else {
							k++;
						}
					}
					if (addPotentialValue == false){
						sudoku.getArray()[i][j] = 0;
						if (j==0){
							i--;
							j=SIZE-1;
						} else {
							j--;
						}
						while (checkArray[i][j] != 0){
							if (j==0){
								i--;
								j=SIZE-1;
							} else {
								j--;
							}
						}
						originalInput = sudoku.getArray()[i][j];
						j--;
					}
				}
				j++;
			}
			i++;
		}
		Sudoku s1 = new Sudoku(sudoku.getArray());
		return s1;
	}

	/**
	 * This method compareRow is used to check whether a potential value matches an existing one in the same row.
	 * This is used for the solve method as opposed to the SudokuCheck method as the process is more specific and hence faster.
	 * @param pValue The potential value to check.
	 * @param row The row number to check.
	 * @param sudoku The sudoku from which the row is selected.
	 * @return A boolean on whether the potential value matches any other value in that same row.
	 */
	public static boolean compareRow(int pValue, int row,Sudoku sudoku){
		for (int col=0; col<SIZE; col++){
			if (pValue == sudoku.getArray()[row][col]){
				return true;
			}
		}
		return false;
	}

	/**
	 * This method compareCol is used to check whether a potential value matches an existing one in the same column.
	 * This is used for the solve method as opposed to the SudokuCheck method as the process is more specific and hence faster.
	 * @param pValue The potential value to check.
	 * @param col The column number to check.
	 * @param sudoku The sudoku from which the column is selected.
	 * @return A boolean on whether the potential value matches any other value in that same column.
	 */
	public static boolean compareCol(int pValue, int col,Sudoku sudoku){
		for (int row=0; row<SIZE; row++){
			if (pValue == sudoku.getArray()[row][col]){
				return true;
			}
		}
		return false;
	}

	/**
	 * This method compareSqu is used to check whether a potential value matches an existing one in the same square.
	 * This is used for the solve method as opposed to the SudokuCheck method as the process is more specific and hence faster.
	 * @param pValue The potential value to check.
	 * @param row The row number to check.
	 * @param col The column number to check. 
	 * @param sudoku The sudoku from which the column is selected.
	 * @return A boolean on whether the potential value matches any other value in that same square.
	 */
	public static boolean compareSqu(int pValue, int row, int col,Sudoku sudoku){
		int startRow = (row/SUB_SIZE)*SUB_SIZE;
		int startCol = (col/SUB_SIZE)*SUB_SIZE;
		for (row=startRow; row<(startRow+SUB_SIZE); row++){
			for (col=startCol; col<(startCol+SUB_SIZE); col++){
				if (pValue == sudoku.getArray()[row][col]){
					return true;
				}
			}
		}
		return false;
	}

}
