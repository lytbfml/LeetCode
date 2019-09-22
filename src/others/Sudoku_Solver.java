package others; /**
 * @author Yangxiao on 11/9/2018.
 */

import algorithms.array.Valid_Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 * <p>
 * Note:
 * <p>
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 */
class Sudoku_Solver {
	
	public void solveSudoku(char[][] board) {
		Valid_Sudoku.isValidSudoku_Sol1(board);
		Set<Character> charSet =
				new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
		ArrayList<Set<Character>> rowSet = new ArrayList<>();
		ArrayList<Set<Character>> colSet = new ArrayList<>();
		ArrayList<Set<Character>> boxSet = new ArrayList<>();
		
		for (int i = 0; i < 9; i++) {
			rowSet.add(new HashSet<>());
			colSet.add(new HashSet<>());
			boxSet.add(new HashSet<>());
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					rowSet.get(i).add(board[i][j]);
					boxSet.get(3 * (i / 3) + (j / 3)).add(board[i][j]);
					colSet.get(j).add(board[i][j]);
				}
			}
		}
		
		int countEmpty = countEmpty(board);
		
		while (countEmpty > 0) {
			ArrayList<Set<Character>> avList = new ArrayList<>();
			ArrayList<Set<Character>> rowAvList = new ArrayList<>();
			ArrayList<Set<Character>> colAvList = new ArrayList<>();
			ArrayList<Set<Character>> boxAvList = new ArrayList<>();
			for (int i = 0; i < 9; i++) {
				rowAvList.add(new HashSet<>());
				colAvList.add(new HashSet<>());
				boxAvList.add(new HashSet<>());
			}
			
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[i][j] == '.') {
						Set<Character> set = new HashSet<>(
								Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
						set.removeAll(rowSet.get(i));
						set.removeAll(colSet.get(j));
						set.removeAll(boxSet.get(3 * (i / 3) + (j / 3)));
						avList.add(set);
						rowAvList.get(i).addAll(set);
						colAvList.get(j).addAll(set);
						boxAvList.get(3 * (i / 3) + (j / 3)).addAll(set);
					}
				}
			}
			
			int indAvList = 0;
			int foundOne = 0;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[i][j] == '.') {
						if (avList.get(indAvList).size() == 1) {
							char temp = avList.get(indAvList).iterator().next();
							board[i][j] = temp;
							rowSet.get(i).add(temp);
							boxSet.get(3 * (i / 3) + (j / 3)).add(temp);
							colSet.get(j).add(temp);
							foundOne++;
						}
						indAvList++;
					}
				}
			}
			if (foundOne > 0) {
				countEmpty -= foundOne;
			} else if (foundOne == 0) {
				indAvList = 0;
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (board[i][j] == '.') {
							
							
							indAvList++;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	private static int countEmpty(char board[][]) {
		int count = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					count++;
				}
			}
		}
		return count;
	}
	
	public void solveSudoku_Sol(char[][] board) {
		if (board == null || board.length == 0) { return; }
		solve(board);
	}
	
	public boolean solve(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') {
					for (char c = '1'; c <= '9'; c++) {//trial. Try 1 through 9
						if (isValid(board, i, j, c)) {
							board[i][j] = c; //Put c for this cell
							
							if (solve(board)) {
								return true; //If it's the solution return true
							} else {
								board[i][j] = '.'; //Otherwise go back
							}
						}
					}
					
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isValid(char[][] board, int row, int col, char c) {
		for (int i = 0; i < 9; i++) {
			if (board[i][col] != '.' && board[i][col] == c) {
				return false; //check row
			}
			if (board[row][i] != '.' && board[row][i] == c) {
				return false; //check column
			}
			if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.' &&
					board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
				return false; //check 3*3 block
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Sudoku_Solver ss = new Sudoku_Solver();
		char[][] xx = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
		               {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
		               {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
		               {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
		               {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
		               {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
		               {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
		               {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
		               {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
		
		System.out.println();
		ss.solveSudoku(xx);
		
		
		char[][] yy = {{'.', '.', '9', '7', '4', '8', '.', '.', '.'},
		               {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
		               {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
		               {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
		               {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
		               {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
		               {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
		               {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
		               {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
		//myPuzzles_llll
		ss.solveSudoku(yy);
	}
	
	class Solution {
		static final int ALL_ZEROS = 0;
		static final int ALL_ONES = 0x3fe;
		int[] row_bitmap, col_bitmap, cube_bitmap, entry, sequence;
		// Always points to the first empty cell's SQUARE index, which is stored in SEQUENCE
		int seq_start;
		// Utility arrays to store mapping from SQUARE to ROW/COLs/CUBES: e.g. 37 -> cube[1, 0], whose 1D index is 3;
		int[] square_to_row, square_to_col, square_to_cube;
		
		public void solveSudoku(char[][] board) {
			seq_start = 0;
			row_bitmap = new int[9];
			col_bitmap = new int[9];
			cube_bitmap = new int[9];
			entry =  new int[81];
			sequence =  new int[81];
			square_to_row =  new int[81];
			square_to_col =  new int[81];
			square_to_cube = new int[81];
			// Initialize all helping data structures
			// All digits are initially all available (marked by 1) in all rows/columns/cubes
			for (int i = 0; i < 9; i++)
				row_bitmap[i] = col_bitmap[i] = cube_bitmap[i] = ALL_ONES;
			// Sequence stores all SQUARE indices of all cells, with 0..start-1 being all filled cells, and the rest all empty
			// All cells initially all empty
			for (int i = 0; i < 81; i++)
				sequence[i] = i;
			for (int i = 0; i < 9; i++) for (int j = 0; j < 9; j++) {
				// Mapping from SQUARE to I/J is also beneficial: avoid calculating I/J from SQUARE later
				int square = i * 9 + j;
				square_to_row[square] = i;
				square_to_col[square] = j;
				square_to_cube[square] = (i / 3) * 3 + j / 3;
			}
			// Fill in the given cells. Update the bitmaps at the same time
			for (int i = 0; i < 9; i++) for (int j = 0; j < 9; j++) if (board[i][j] != '.') {
				int square = i * 9 + j, val = board[i][j] - '0', valbit = 1 << val;
				row_bitmap[i] &= ~valbit;
				col_bitmap[j] &= ~valbit;
				cube_bitmap[(i / 3) * 3 + j / 3] &= ~valbit;
				entry[square] = valbit;
				int seq_iter = seq_start;
				// Compact non-empty cells to the front, and use SEQ_START to mark the first empty cell's position
				while (seq_iter < 81 && sequence[seq_iter] != square)
					seq_iter++;
				swapSeq (seq_start++, seq_iter);
			}
			// main solver process
			boolean success = place (seq_start);
			assert success : "Unsolvable Puzzle!";
			// dump result back from ENTRY array to BOARD
			for (int s = 0; s < 81; s++) {
				int i = square_to_row[s], j = square_to_col[s];
				board[i][j] = (char) (Integer.numberOfTrailingZeros (entry[s]) + '0');
			}
		}
		
		boolean place (int seq_pos) {
			if (seq_pos >= 81)
				return true;
			int seq_next = nextPos (seq_pos);
			swapSeq (seq_pos, seq_next);
			int square = sequence[seq_pos], row_idx = square_to_row[square], col_idx = square_to_col[square], cube_idx = square_to_cube[square];
			int cell_bitmap = row_bitmap[row_idx] & col_bitmap[col_idx] & cube_bitmap[cube_idx];
			while (cell_bitmap > 0) {
				// Get each available bit/digit in order
				int next_digit_bit = cell_bitmap & -cell_bitmap;
				cell_bitmap &= ~next_digit_bit;
				entry[square] = next_digit_bit;
				// claim this DIGIT is used in row/column/cube
				row_bitmap[row_idx] &= ~next_digit_bit;
				col_bitmap[col_idx] &= ~next_digit_bit;
				cube_bitmap[cube_idx] &= ~next_digit_bit;
				
				if (place (seq_pos + 1))
					return true;
				
				// undo claims in the bitmaps
				row_bitmap[row_idx] |= next_digit_bit;
				col_bitmap[col_idx] |= next_digit_bit;
				cube_bitmap[cube_idx] |= next_digit_bit;
				entry[square] = ALL_ZEROS;
			}
			swapSeq (seq_pos, seq_next);
			return false;
		}
		
		// Find among empty cells the one with the smallest search space: least bits on its bitmap;
		int nextPos (int pos) {
			int min_idx = pos, min_digit_count = 100;
			for (int i = pos; i < 81; i++) {
				int square = sequence[i];
				// Number of bits in CELL_BITMAP is the number of digits that this cell can take
				int cell_bitmap = row_bitmap[square_to_row[square]] & col_bitmap[square_to_col[square]] & cube_bitmap[square_to_cube[square]];
				// Counts the bits, so you know how many digits this CELL can take: we want the minimum one
				int num_possible_digits = Integer.bitCount (cell_bitmap);
				if (num_possible_digits < min_digit_count) {
					min_idx = i;
					min_digit_count = num_possible_digits;
				}
			}
			return min_idx;
		}
		
		void swapSeq (int i, int j) {
			int tmp = sequence[i];
			sequence[i] = sequence[j];
			sequence[j] = tmp;
		}
		//     public void solveSudoku(char[][] board) {
		//         if (board.length == 0) {
		//             return;
		//         }
		//         solve(board);
		//     }
		
		//     public boolean solve(char[][] board){
		//         for(int i = 0; i < board.length; i++){
		//             for(int j = 0; j < board[0].length; j++){
		//                 if(board[i][j] == '.'){
		//                     for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
		//                         if(isValid(board, i, j, c)){
		//                             board[i][j] = c; //Put c for this cell
		
		//                             if(solve(board))
		//                                 return true; //If it's the solution return true
		//                             else
		//                                 board[i][j] = '.'; //Otherwise go back
		//                         }
		//                     }
		
		//                     return false;
		//                 }
		//             }
		//         }
		//         return true;
		//     }
		
		//     private boolean isValid(char[][] board, int row, int col, char c){
		//         for(int i = 0; i < 9; i++) {
		//             if(board[i][col] == c) return false; //check row
		//             if(board[row][i] == c) return false; //check column
		//             if(board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
		//         }
		//         return true;
		//     }
	}
}
