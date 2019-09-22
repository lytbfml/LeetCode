package algorithms.backtracking;

public class Word_Search {
	
	public static void main(String[] args) {
		String x = "ABCESEEEFS";
		char[][] n = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
		Word_Search ws = new Word_Search();
		ws.exist(n, x);
	}
	
	private int row;
	private int col;
	
	public boolean exist(char[][] board, String word) {
		row = board.length;
		col = board[0].length;
		char first = word.charAt(0);
		char[] wordC = word.toCharArray();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == first) {
					boolean[][] visited = new boolean[row][col];
					if (backtrack(board, wordC, 0, i, j, visited))
						return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean backtrack(char[][] board, char[] word, int index, int i, int j, boolean[][] visited) {
		if (index == word.length) {
			return true;
		}
		
		if (i < 0 || i >= row || j < 0 || j >= col || visited[i][j]) {
			return false;
		}
		
		if (word[index] != board[i][j]) {
			return false;
		}
		visited[i][j] = true;
		boolean re = backtrack(board, word, index + 1, i, j + 1, visited)
				|| backtrack(board, word, index + 1, i + 1, j, visited)
				|| backtrack(board, word, index + 1, i - 1, j, visited)
				|| backtrack(board, word, index + 1, i, j - 1, visited);
		visited[i][j] = false;
		return re;
	}
	
	
	class Solution_xorAndFaster {
		
		private int row;
		private int col;
		
		public boolean exist(char[][] board, String word) {
			row = board.length;
			col = board[0].length;
			char first = word.charAt(0);
			char[] wordC = word.toCharArray();
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (board[i][j] == first) {
						if (backtrack(board, wordC, 0, i, j))
							return true;
					}
				}
			}
			
			return false;
		}
		
		private boolean backtrack(char[][] board, char[] word, int index, int i, int j) {
			if (index == word.length) {
				return true;
			}
			
			if (i < 0 || i >= row || j < 0 || j >= col) {
				return false;
			}
			
			if (board[i][j] != word[index])
				return false;
			
			board[i][j] ^= 256; // char c = board[x][y]; board[x][y] = '#';
			boolean re = backtrack(board, word, index + 1, i, j + 1)
					|| backtrack(board, word, index + 1, i + 1, j)
					|| backtrack(board, word, index + 1, i - 1, j)
					|| backtrack(board, word, index + 1, i, j - 1);
			board[i][j] ^= 256; // board[x][y] = c;
			return re;
		}
	}
}
