/**
 * This class SudokuRead is a sub-class of Sudoku that reads in a sudoku in the format of an array or sudoku.
 * @author axd638
 * @version 29/11/2016
 */

import java.io.*;

public class SudokuRead extends Sudoku {
	
	/** VARIABLES:
	 *  The following static variable has been defined:
	 */
	public static String readString;

	/** CONSTRUCTOR:
	 *  This constructor creates an SudokuRead object from the super class array field variable:
	 *  @param array The array of numbers in the sudoku.
	 */
	public SudokuRead(int[][] array) {
		super(array);
	}
	
 	/* METHODS:
 	 * There are two methods, one that reads in a file and translates the input into an output array
 	 * and a second method that transforms this array into a sudoku object.
	 */

	/**
	 * This method readSudokuArray takes in a file of type String using a FileReader and BudderedReader
	 * The method in encaspsulated within a try and catch in order to throw invalid file inputs.
	 * realine is then used to read each line within the first 'for' loop which is iterated over the SIZE of the puzzle.
	 * A split method is then used to create an array of individual characters which are then inserted into the input array using a nested for loop.
	 * A conditional is then used to to then determine whether an integer or zero is inserted into the array at the current location.
	 * An illegal argument is thrown if the character is invalid.
	 * The reader is then closed and catch statements throw any illegal arguments.
	 * @param fileName The file to be read.
	 * @return An array of Sudoku values.
	 * @throws IllegalArgumentException For inputs that do not conform to the rules of Sudoku or are illegal inputs.
	 * @throws IOException There is an error in the input file.
	 * @throws ArrayIndexOutOfBoundsException For when the line lengths are not correct.
	 */
	public static int[][] readSudokuArray(String fileName) throws IllegalArgumentException, IOException, ArrayIndexOutOfBoundsException{
		int inputArray[][] = new int[SIZE][SIZE];
		try {
			FileReader readFile = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(readFile);
			for (int i=0; i<SIZE; i++){
				readString = reader.readLine();
				String[]splitLine = readString.split("");
				for (int j=0; j<SIZE; j++){
					if ("123456789 ".contains(splitLine[j])){
						if (splitLine[j].equals(" ")){
							inputArray[i][j] = 0;
						} else {
							inputArray[i][j] = Integer.valueOf(splitLine[j]);
						}
					} else {
						reader.close();
						throw new IllegalArgumentException("Input character is not an integer of value 1-9 or an empty space.");
					}
				}
			}
			reader.close();
			Sudoku sudokuToCheck = new Sudoku(inputArray);
			if (!SudokuCheck.checkCorrect(sudokuToCheck)){
				throw new IllegalArgumentException("Input sudoku is incorrect and cannot be completed.");
			}
			if (sudokuToCheck.isFilled()){
				throw new IllegalArgumentException("Input sudoku is already filled out.");
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException("Input line not of the correct length.");
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("Unable to open file '" + fileName + "'");
		}
		catch(IOException e) {
			throw new IOException("Error reading in file '"  + fileName + "'");
		}
		return inputArray;
	}

	/**
	 * 
	 * @param fileName The file to be read.
	 * @return An array of Sudoku values in the type Sudoku.
	 * @throws IllegalArgumentException For inputs that do not conform to the rules of Sudoku or are illegal inputs.
	 * @throws IOException There is an error in the input file.
	 */
	public static Sudoku readSudoku(String fileName) throws IllegalArgumentException, IOException{
		Sudoku s1 = new Sudoku(readSudokuArray(fileName));
		return s1;
	}
}
