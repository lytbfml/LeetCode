package algorithms.array;

/**
 * @author Yangxiao Wang on 2019-07-18
 */
public class Game_of_Life {
	
	int n, m;
	
	public void gameOfLife(int[][] board) {
		if (board == null || board.length == 0) return;
		n = board.length;
		m = board[0].length;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int res = conv(board, i, j);
				if (board[i][j] == 0 || board[i][j] == 2) {
					if (res == 3) board[i][j] = 2;
				} else {
					if (res < 2) {
						board[i][j] = -1;
					} else if (res > 3) {
						board[i][j] = -1;
					}
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 2) {
					board[i][j] = 1;
				} else if (board[i][j] == -1) {
					board[i][j] = 0;
				}
			}
		}
	}
	
	private int conv(int[][] bo, int i, int j) {
		int res = helper(bo, i + 1, j) + helper(bo, i + 1, j + 1) + helper(bo, i+1, j - 1) +
				helper(bo, i, j + 1) + helper(bo, i, j - 1) +
				helper(bo, i - 1, j) + helper(bo, i - 1, j - 1) + helper(bo, i - 1, j + 1);
		return res;
	}
	
	
	private int helper(int[][] bo, int i, int j) {
		if (i < 0 || j < 0 || i >= n || j >= m) return 0;
		
		if (bo[i][j] < 0) {
			return 1;
		} else if (bo[i][j] == 2) {
			return 0;
		} else {
			return bo[i][j];
		}
	}
}
