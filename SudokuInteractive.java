/**
 * This class SudokuInteractive is a sub-class of Sudoku. The class is interactive, containing a methoed
 * called play that uses a while loop that is controlled by user input in the command line.
 * @author axd638
 * @version 2016-11-29
 */

import java.io.IOException;
import java.util.Scanner;

public class SudokuInteractive extends Sudoku{

	/** CONSTRUCTOR:
	 *  This constructor creates an SudokuInteractive object from the super class array field variable:
	 *  @param array The array of numbers in the sudoku.
	 */
	public SudokuInteractive(int[][] array) {
		super(array);
	}
	
 	/* METHODS:
 	 * There are 4 methods:
 	 * The play method allows the user to interact with the sudoku.
 	 * The reset method resets an input sudoku to an originally given sudoku.
 	 * The toString method overwrites the to String of the original toString within Class Sudoku.
 	 * The Main method allows SudokuInteractive to be played.
	 */

	/**
	 * This method contains multiple sections in order to create a playable scenario.
	 * An instance of SudokuInteractive is created under the local variable 'sudoku'.
	 * Using this, an originalArray called checkArray is created using a nested loop to copy over the values.
	 * This check array is used for comparison checks against the original input later in the method.
	 * An introductionary statement and starting sudoku is printed and a new scanner created.
	 * A 'while' loop is then started, looping around till either a break is reached or the sudoku is completed.
	 * Within the 'while' loop there are4 main conditionals, The first three check for 
	 * statements, 'exit', 'reset', and 'cheatcode123' respectively, each acting accordingly.
	 * Typing 'exit' breaks the 'while' loop and ends the method.
	 * Typing 'reset' calls the reset method and continues to loop which uses the 'checkArray' to reset the sudoku to its original format.
	 * Typing 'cheatcode123' resets the sudoku and solves it using the solve sudoku method from SudokuSolve.
	 * If the 4th conditional is reached then the input is analysed to check if it is of the format a1:1 for example.
	 * The input is then split up using a split method and each element is taken and translated to ints that
	 * can then be used to input the value within the sudoku at the specified row and column.
	 * If a value is already present (checkArray !=0), or an input value does not meet the rules of sudoku 
	 * respective conditional else statements are used to return information to the player about the issue.
	 * The method is encapsulated within a try and catch to catch any illegal arguments.
	 * @param fileName The input file that is read by the play method.
	 */
	public static void play(String fileName){
		SudokuInteractive sudoku;
		try {
			sudoku = new SudokuInteractive(SudokuRead.readSudokuArray(fileName));
			int[][] checkArray = new int[SIZE][SIZE];
			for (int r=0; r<SIZE; r++){
				for (int c=0; c<SIZE; c++){
					checkArray[r][c] = sudoku.getArray()[r][c];
				}
			}
			System.out.print("Welcome to Sudoku interactive, use the console to input coordinates and values.\n\n"
							+"Type \"reset\" to restart the sudoku at any time and \"exit\" to quit the game.\n\n"
							+"Good luck!\n\n");
			System.out.print(sudoku.toString(checkArray) + "\n");
			System.out.print("Enter a position and value in the format: *row**column*:*value*\n");
			Scanner scan = new Scanner(System.in);
			boolean checkSudokuFinished = sudoku.isFilled();
			while (scan.hasNext() && checkSudokuFinished == false) {
				String userInput = scan.nextLine();
				if (userInput.equals("exit")){
					System.out.print("Giving up already...?\n");
					break;
				}
				if (userInput.equals("reset")){
					reset(sudoku,checkArray);
					System.out.print(sudoku.toString(checkArray) + "\n");
					System.out.print("Sudoku has been reset. Have another go.\n");
					continue;
				}
				if (userInput.equals("cheatcode123")){
					reset(sudoku,checkArray);
					SudokuSolve.solve(sudoku);
					System.out.print(sudoku.toString(checkArray) + "\n");
					System.out.print("You're such a cheat.\n");
					break;
				}
				String[]splitLine = userInput.split("");
				if ("abcdefghi".contains(splitLine[0]) && "123456789".contains(splitLine[1]) 
						&& ":".equals(splitLine[2]) && "123456789".contains(splitLine[3])){
					int row = Character.getNumericValue(splitLine[0].charAt(0))-10;
					int column = Integer.valueOf(splitLine[1])-1;
					int inputValue = Integer.valueOf(splitLine[3]);
					if (checkArray[row][column] == 0){
						sudoku.getArray()[row][column] = inputValue;
						if (SudokuCheck.checkCorrect(sudoku) == true){
							System.out.print(sudoku.toString(checkArray) + "\n");
							System.out.print("At  row: " + splitLine[0] + "     column: " + splitLine[1] + "     input value: " + inputValue + "\n");
							checkSudokuFinished = sudoku.isFilled();
							if (checkSudokuFinished == true){
								System.out.print("\nWell done! You've completed the puzzle.\n");
								break;
							}
						} else {
							sudoku.getArray()[row][column] = 0;
							System.out.println("Invalid input, number already appears in another row, coloumn or square.");
						}
					} else {
						System.out.println("Invalid coordinate, enter a coordinate that wasn't given.");
					}
				} else {
					System.out.println("Invalid input, please use the format: *row**column*:*value*");
				}
			}
			scan.close();
			System.out.println("\nThanks for playing.");
		} catch (IllegalArgumentException|IOException e) {
			throw new IllegalArgumentException("Illegal Argumment input");
		}
	}
	
	/**
	 * This method iterates through a sudokus row and columns to transform the values of a sudoku puzzle
	 * to an originalArray of sudoku values. This is used in the play method to reset the puzzle.
	 * @param sudoku This input sudoku to be reset.
	 * @param originalArray The original array of sudoku values for the sudoku to be set to.
	 */
	public static void reset(Sudoku sudoku, int[][] originalArray){
		for (int r=0; r<9; r++){
			for (int c=0; c<9; c++){
				sudoku.getArray()[r][c] = originalArray[r][c];
			}
		}
	}
	
	/**
	 * This toString method is used to override the original toString in Sudoku, printing out the array in the Sudoku 
	 * object in a specified format but with an additional conditional to create stars around original inputs:
	 * drawSudoku is a local String variable that is used to build the entire string piece by piece.
	 * First the COLUMNSN and TOP_LINE are added.
	 * 4 'for' loops are used in order to create the string, each iterating over the length of the SUB_SIZE.
	 * The most nested loop starts by adding Strings of the first 3 numbers in the first row.
	 * A conditional is used to check against the original array to evaluate where to locate the stars around original inputs.
	 * The next loop up repeats this twice more to the end of the row. Adding the ROWSN and TOP_LINE or MIDLINE in a conditional post loop.
	 * The next loop up repeats this till the end of the SUB_SIZE (when the next TOP_LINE is added).
	 * The next loop up repeats this till the end of the SUB_SIZE (3 times).
	 * @param originalArray The orignal array at the beginning of the game.
	 * @return The completed String of the sudoku.
	 */
	public String toString(int[][] originalArray){
		String drawSudoku = "";
		drawSudoku += COLUMNSN + "\n" +TOP_LINE;
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				drawSudoku += "\n" + ROWSN[(i*3)+j] + " ||";
				for (int k=0;k<3;k++){
					for (int h=0;h<3;h++){
						int check = getArray()[j+(i*3)][h+(k*3)];
						if (check == 0){
							drawSudoku += "   |";
						} else {
							if (originalArray[j+(i*3)][h+(k*3)] == 0){
								drawSudoku += " " + check + " |";
							} else {
								drawSudoku += "*" + check + "*|";
							}
						}
					}
					drawSudoku += "|";
				}
				if (j < (SUB_SIZE-1)){
					drawSudoku += "\n" + MID_LINE;
				} else {
					drawSudoku += "\n" + TOP_LINE;
				}
			}
		}
		return drawSudoku;
	}

	/**
	 * This main method is used to allow SudokuInteractive to be playable.
	 * Classes of Sudoku, SudokuRead, SudokuCheck and SudokuSolve must be accessible.
	 * This method has also been used to 'play' test the functionality of this Class.
	 * @param args The method play is instigated.
	 */
	public static void main(String[] args){
		SudokuInteractive.play("/home/students/axd638/Current Work/WS1-5/Sudoku/test-sudoku.txt");	
	}
}
