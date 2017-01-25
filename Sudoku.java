/**
 * This class Sudoku creates a formatted puzzle using a toString method.
 * There is one field variable of type int which holds an array of a specified size.
 * There are 6 final variables which define the size and format of the drawn puzzle.
 * @author axd638
 * @version 2016-11-29
 */

public class Sudoku {
	
	/** VARIABLES:
	 *  The following variables have been defined:
	 *  ROWSN is a final String array containing the row letters.
	 *  COLUMNSN is a final String containing the column n numbers.
	 *  TOP_LINE is a final String format for the top lines.
	 *  MID_LINE is a final String format for the mid lines.
	 *  SIZE is the final size of the sudoku puzzle of type int; 9 has been set to create a 9x9 puzzle.
	 *  SUB_SIZE is the final size of the sub sections of the puzzle, for a 9x9 sudoku the SUB_SIZE is 3.
	 */
	private int[][] array;
	public static final String[] ROWSN  = {"a","b","c","d","e","f","g","h","i",};
	public static final String COLUMNSN = "     1   2   3    4   5   6    7   8   9   ";
	public static final String TOP_LINE = "  ++===+===+===++===+===+===++===+===+===++";
	public static final String MID_LINE = "  ++---+---+---++---+---+---++---+---+---++";
	public static final int SIZE = 9;
	public static final int SUB_SIZE = (int)Math.sqrt(SIZE);
	
	/** CONSTRUCTOR:
	 *  This constructor creates an Sudoku object from the array field variable:
	 *  @param array The array of numbers in the sudoku.
	 */
	public Sudoku(int[][] array) {
		this.array = array;
	}
	
 	/* METHODS:
 	 * There is one accessor and setter method for the array field variable.
 	 * There is a ifFilled method to check an array is filled with integer values between 1-9.
 	 * Finally, there is a toString method defining how the Sudoku object is printed.
	 */

	/**
	 * Getter for the array.
	 * @return The array of the Sudoku.
	 */
	public int[][] getArray() {
		return array;
	}

	/**
	 * Setter to set a new array for the sudoku.
	 * @param newArray The new array for the sudoku to be set.
	 */
	public void setArray(int[][] newArray) {
		this.array = newArray;
	}
	
	/**
	 * This toString method prints out the array in the Sudoku object in a specified format:
	 * drawSudoku is a local String variable that is used to build the entire string piece by piece.
	 * First the COLUMNSN and TOP_LINE are added.
	 * 4 'for' loops are used in order to create the string, each iterating over the length of the SUB_SIZE.
	 * The most nested loop starts by adding Strings of the first 3 numbers in the first row.
	 * The next loop up repeats this twice more to the end of the row. Adding the ROWSN and TOP_LINE or MIDLINE in a conditional post loop.
	 * The next loop up repeats this till the end of the SUB_SIZE (when the next TOP_LINE is added).
	 * The next loop up repeats this till the end of the SUB_SIZE (3 times).
	 * @return The completed String of the sudoku.
	 */
	public String toString(){
		String drawSudoku = "";
		drawSudoku += COLUMNSN + "\n" +TOP_LINE;
		for (int i=0;i<SUB_SIZE;i++){
			for (int j=0;j<SUB_SIZE;j++){
				drawSudoku += "\n" + ROWSN[(i*SUB_SIZE)+j] + " ||";
				for (int k=0;k<SUB_SIZE;k++){
					for (int h=0;h<SUB_SIZE;h++){
						int check = getArray()[j+(i*SUB_SIZE)][h+(k*SUB_SIZE)];
						if (check == 0){
							drawSudoku += "   |";
						} else {
							drawSudoku += " " + check + " |";
						}
					}
					drawSudoku += "|";
				}
				if (j < 2){
					drawSudoku += "\n" + MID_LINE;
				} else {
					drawSudoku += "\n" + TOP_LINE;
				}
			}
		}
		return drawSudoku;
	}
	
	/**
	 * This isFilled method has two for loops that iterate through the rows and columns of the given array.
	 * A conditional checks whether each element is equal zero or not. If it is, then false is returned.
	 * Therefore the sudoku is not filled since 0 stands for a blank value.
	 * @return Whether the sudoku is filled or not.
	 */
	public boolean isFilled(){
		for (int i=0; i<SIZE; i++){
			for (int j=0; j<SIZE; j++){
				if (getArray()[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}
	
}
