/**
 * This class SudokuCheck is a sub-class of Sudoku.
 * Methods contained in this class are used to check the inputs against the rules of Sudoku
 * Including checks for rows, columns and squares.
 * @author axd638
 * @version 29/11/2016
 */

public class SudokuCheck extends Sudoku{

	/** CONSTRUCTOR:
	 *  This constructor creates an SudokuCheck object from the super class array field variable:
	 *  @param array The array of numbers in the sudoku.
	 */
	public SudokuCheck(int[][] array) {
		super(array);
	}

 	/* METHODS:
 	 * There are 5 methods, 3 of which check the rows, columns and squares. The other 2 give details on true and false.
	 */
	
	/**
	 * This method creates a 2D array of boolean values dependent on the results from the row, column and square checks.
	 * row[0] contains the results for the row check.
	 * row[1] contains the results for the column check.
	 * row[2] contains the results for the squares check.
	 * @param sudoku The sudoku to check.
	 * @return A 2D array of boolean results for the sudoku check.
	 */
	public static boolean[][] check(Sudoku sudoku){
		boolean[][] checkArray = new boolean[3][SIZE];
		checkArray[0] = checkRows(sudoku);
		checkArray[1] = checkColumns(sudoku);
		checkArray[2] = checkSquares(sudoku);
		return checkArray;
	}
	
	/**
	 * This method is to check the 2D boolean array in the check methods.
	 * A nested 'for' loop iterates through the array and returns a single boolean result true if all checks are true.
	 * @param sudoku The input sudoku to check.
	 * @return A single boolean based on the check method. True is returned if all checks are satisfied.
	 */
	public static boolean checkCorrect(Sudoku sudoku){
		for (int i=0; i<3; i++){
			for (int j=0; j<SIZE; j++){
				if (check(sudoku)[i][j] == false){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method checks each row for duplicate values (1-9), giving a resulting 1D boolean array.
	 * A nested 'for' loop is used to determine a 'checkElement' which is then checked against every other element
	 * in the same row apart from itself or if the value is 0 using a conditional.
	 * If false is set to the boolean array, the jth and kth loops are ended and i is iterated through to the next row.
	 * @param sudoku The input sudoku to check.
	 * @return A 1D boolean array for each row. True is returned if there are no duplicates in the row.
	 */
	public static boolean[] checkRows(Sudoku sudoku){
		boolean[] rowCheck = new boolean[SIZE];
		for (int i=0; i<SIZE; i++){
			for (int j=0; j<SIZE; j++){
				int checkElement = sudoku.getArray()[i][j];
				for (int k=0; k<SIZE; k++){
					if (checkElement == sudoku.getArray()[i][k] && checkElement !=0 && j!=k){
						rowCheck[i] = false;
						k = SIZE;
						j = SIZE;
					} else {
						rowCheck[i] = true;
					}
				}
			}
		}
		return rowCheck;
	}

	/**
	 * This method checks each column for duplicate values (1-9), giving a resulting 1D boolean array.
	 * A nested 'for' loop is used to determine a 'checkElement' which is then checked against every other element
	 * in the same column apart from itself or if the value is 0 using a conditional.
	 * If false is set to the boolean array, the jth and kth loops are ended and i is iterated through to the next column.
	 * @param sudoku The input sudoku to check.
	 * @return A 1D boolean array for each column. True is returned if there are no duplicates in the column.
	 */
	public static boolean[] checkColumns(Sudoku sudoku){
		boolean[] colCheck = new boolean[SIZE];
		for (int i=0; i<SIZE; i++){
			for (int j=0; j<SIZE; j++){
				int checkElement = sudoku.getArray()[j][i];
				for (int k=0; k<SIZE; k++){
					if (checkElement == sudoku.getArray()[k][i] && checkElement !=0 && j!=k){
						colCheck[i] = false;
						k = SIZE;
						j = SIZE;
					} else {
						colCheck[i] = true;
					}
				}
			}
		}
		return colCheck;
	}
	
	/**
	 * This method checks each square for duplicate values (1-9), giving a resulting 1D boolean array.
	 * This method contains 3 nested 'for' loops:
	 * The first is used to determine a large square. i iterates through the rows, j through the columns.
	 * The second is used to determine a 'checkElement' within each 3x3 square. x iterates through the rows, y through the columns.
	 * Once a 'checkElement' is chosen a third and final nested 'for' loop is used to test the chosen 'checkElement'
	 * against every other value in the 3x3 square. k iterates through the rows, l through the columns.
	 * If false is set to the boolean array, the lth,kth,xth, and yth loops are ended and i and or j is iterated through to the next large square.
	 * @param sudoku The input sudoku to check.
	 * @return A 1D boolean array for each square. True is returned if there are no duplicates in the square.
	 */
	public static boolean[] checkSquares(Sudoku sudoku){
		boolean[] squCheck = new boolean[SIZE];
		for (int i=0; i<SUB_SIZE; i++){
			for (int j=0; j<SUB_SIZE; j++){
				
				for (int x=0; x<SUB_SIZE; x++){
					for (int y=0; y<SUB_SIZE; y++){
						
						int checkElement = sudoku.getArray()[x+(i*SUB_SIZE)][y+(j*SUB_SIZE)];
						for (int k=0; k<SUB_SIZE; k++){
							for (int l=0; l<SUB_SIZE; l++){
								if (checkElement == sudoku.getArray()[k+(i*SUB_SIZE)][l+(j*SUB_SIZE)] && checkElement !=0 && (k!=x || y!=l)){
									squCheck[j+(i*3)] = false;
									l=SUB_SIZE; k=SUB_SIZE; x=SUB_SIZE; y=SUB_SIZE;
								} else {
									squCheck[j+(i*3)] = true;
								}
							}
						}
					}
				}
			}
		}
		return squCheck;
	}
}
