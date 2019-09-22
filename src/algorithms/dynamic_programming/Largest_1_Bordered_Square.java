package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 7/27/2019
 */
public class Largest_1_Bordered_Square {
	
	class Solution {
		public int largest1BorderedSquare(int[][] A) {
			int m = A.length, n = A[0].length;
			int[][] left = new int[m][n], top = new int[m][n];
			for (int i = 0; i < m; ++i) {
				for (int j = 0; j < n; ++j) {
					if (A[i][j] > 0) {
						left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
						top[i][j] = i > 0 ? top[i - 1][j] + 1 : 1;
					}
				}
			}
			for (int l = Math.min(m, n); l > 0; --l)
				for (int i = 0; i < m - l + 1; ++i)
					for (int j = 0; j < n - l + 1; ++j)
						if (top[i + l - 1][j] >= l &&
								top[i + l - 1][j + l - 1] >= l &&
								left[i][j + l - 1] >= l &&
								left[i + l - 1][j + l - 1] >= l)
							return l * l;
			return 0;
		}
	}
	
	class Solution_sol {
		public int largest1BorderedSquare(int[][] grid) {
			if (grid.length == 0) return 0;
			int m = grid.length, n = grid[0].length;
			int hor[][] = new int[m][n];
			int ver[][] = new int[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] == 1) {
						hor[i][j] = j == 0 ? 1 : hor[i][j - 1] + 1;
						ver[i][j] = i == 0 ? 1 : ver[i - 1][j] + 1;
					}
				}
			}
			int max = 0;
			for (int i = m - 1; i >= 0; i--) {
				for (int j = n - 1; j >= 0; j--) {
					int edge = Math.min(hor[i][j], ver[i][j]);
					while (edge > max) {
						if (ver[i][j - edge + 1] >= edge && hor[i - edge + 1][j] >= edge) max = edge;
						edge--;
					}
				}
			}
			return max * max;
		}
	}
}
