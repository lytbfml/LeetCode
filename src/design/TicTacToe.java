package design;

/**
 * @author Yangxiao Wang on 2019-07-01
 */
public class TicTacToe {
	
	public static void main(String[] args) {
		TicTacToe cs = new TicTacToe(3);
		cs.move(0, 0, 1);
		cs.move(0, 2, 2);
		cs.move(2, 2, 1);
		cs.move(1, 1, 2);
		cs.move(2, 0, 1);
		cs.move(1, 0, 2);
		cs.move(2, 1, 1);
	}
	
	
	int[][] grid;
	int goal;
	int win;
	
	/**
	 * Initialize your data structure here.
	 */
	public TicTacToe(int n) {
		grid = new int[n][n];
		goal = n;
		win = 0;
	}
	
	/**
	 * Player {player} makes a move at ({row}, {col}).
	 *
	 * @param row    The row of the board.
	 * @param col    The column of the board.
	 * @param player The player, can be either 1 or 2.
	 * @return The current winning condition, can be either: 0: No one wins. 1: Player 1 wins. 2: Player 2 wins.
	 */
	public int move(int row, int col, int player) {
		if (win != 0) {
			return win;
		}
		grid[row][col] = player;
		
		int cntC = helper(row, col, player, new int[]{0, 1});
		cntC += helper(row, col, player, new int[]{0, -1});
		cntC--;
		int cntR = helper(row, col, player, new int[]{-1, 0});
		cntR += helper(row, col, player, new int[]{1, 0});
		cntR--;
		int cntD1 = helper(row, col, player, new int[]{1, 1});
		cntD1 += helper(row, col, player, new int[]{-1, -1});
		cntD1--;
		int cntD2 = helper(row, col, player, new int[]{-1, 1});
		cntD2 += helper(row, col, player, new int[]{1, -1});
		cntD2--;
		
		if (cntC == goal || cntR == goal || cntD1 == goal || cntD2 == goal) {
			win = player;
			return player;
		}
		
		return 0;
	}
	
	private int helper(int r, int c, int p, int[] d) {
		if (r < 0 || c < 0 || r >= goal || c >= goal || grid[r][c] != p) {
			return 0;
		}
		int cnt = 1;
		cnt += helper(r + d[0], c + d[1], p, d);
		return cnt;
	}
	
	class Solution {
		
		int[] rows;
		int[] cols;
		int dig1;
		int dig2;
		int goal;
		int win;
		
		/**
		 * Initialize your data structure here.
		 */
		public Solution(int n) {
			rows = new int[n];
			cols = new int[n];
			goal = n;
			dig1 = 0;
			dig2 = 0;
			win = 0;
		}
		
		/**
		 * Player {player} makes a move at ({row}, {col}).
		 *
		 * @param row    The row of the board.
		 * @param col    The column of the board.
		 * @param player The player, can be either 1 or 2.
		 * @return The current winning condition, can be either: 0: No one wins. 1: Player 1 wins. 2: Player 2 wins.
		 */
		public int move(int row, int col, int player) {
			if (win != 0) {
				return win;
			}
			
			int mo = player == 1 ? -1 : 1;
			rows[row] += mo;
			cols[col] += mo;
			if (row == col) {
				dig1 += mo;
			}
			if (goal - 1 == row + col) {
				dig2 += mo;
			}
			if (Math.abs(rows[row]) == goal || Math.abs(cols[col]) == goal || Math.abs(dig1) == goal ||
					Math.abs(dig2) == goal) {
				win = player;
				return player;
			}
			return 0;
		}
	}
}

/**
 * Your TicTacToe object will be instantiated and called as such: TicTacToe obj = new TicTacToe(n); int param_1 =
 * obj.move(row,col,player);
 */
