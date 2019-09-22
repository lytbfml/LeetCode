package algorithms.array;

/**
 * @author Yangxiao on 11/9/2018.
 */

/**
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to
 * the following rules:
 * Each row must contain the digits 1-9 without repetition. Each column must
 * contain the digits 1-9 without repetition. Each of the 9 3x3 sub-boxes of the grid must contain
 * the digits 1-9 without repetition.
 */
public class Valid_Sudoku {
	
	public boolean isValidSudoku(char[][] board) {
		int[] charSet;
		int[] charSetj;
		for (int i = 0; i < 9; i++) {
			charSet = new int[256];
			charSetj = new int[256];
			for (int j = 0; j < 9; j++) {
				char temp = board[i][j];
				if (temp != '.') {
					if (charSet[temp] == 1) {
						return false;
					}
					charSet[temp] = 1;
				}
				
				char tempj = board[j][i];
				if (tempj != '.') {
					if (charSetj[tempj] == 1) {
						return false;
					}
					charSetj[tempj] = 1;
				}
			}
		}
		
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				charSet = new int[256];
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						char temp = board[i + k][j + l];
						if (temp != '.') {
							if (charSet[temp] == 1) {
								return false;
							}
							charSet[temp] = 1;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	public static boolean isValidSudoku_Sol1(char[][] board) {
		int[] charSeti;
		int[] charSetj;
		int[] charSetb;
		for (int i = 0; i < 9; i++) {
			charSeti = new int[9];
			charSetj = new int[9];
			charSetb = new int[9];
			for (int j = 0; j < 9; j++) {
				char temp = board[i][j];
				if (temp != '.') {
					if (charSeti[temp - 49] == 1) {
						return false;
					}
					charSeti[temp - 49] = 1;
				}
				
				char tempj = board[j][i];
				if (tempj != '.') {
					if (charSetj[tempj - 49] == 1) {
						return false;
					}
					charSetj[tempj - 49] = 1;
				}
				
				int row = 3 * (i / 3);
				int col = 3 * (i % 3);
				char tempb = board[row + j / 3][col + j % 3];
				if (tempb != '.') {
					if (charSetb[tempb - 49] == 1) {
						return false;
					}
					charSetb[tempb - 49] = 1;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		char[][] xx = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
		               {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
		               {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
		               {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
		               {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
		               {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
		               {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
		               {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
		               {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
		
		Valid_Sudoku vs = new Valid_Sudoku();
		System.out.println(vs.isValidSudoku(xx));
	}
	
}