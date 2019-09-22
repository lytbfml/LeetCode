package algorithms.array;

/**
 * @author Yangxiao Wang on 2019-09-04
 *
 * @see Spiral_Matrix
 */
public class Spiral_Matrix_II {
	
	public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		if (n == 0) return matrix;
		helper(matrix, 1, 0, 0, matrix.length, matrix[0].length);
		return matrix;
	}
	
	private void helper(int[][] matrix, int n, int i, int j, int r, int c) {
		if (i >= r || j >= c)
			return;
		for (int k = i; k < c; k++) {
			matrix[i][k] = n++;
		}
		for (int k = i + 1; k < r; k++) {
			matrix[k][c - 1] = n++;
		}
		if (r - 1 != i) {
			for (int k = c - 2; k >= j; k--) {
				matrix[r - 1][k] = n++;
			}
		}
		if (c - 1 != j) {
			for (int k = r - 2; k > i; k--) {
				matrix[k][j] = n++;
			}
		}
		helper(matrix, n, i + 1, j + 1, r - 1, c - 1);
	}
	
	public static int[][] generateMatrix_Sol(int n) {
		int[][] ret = new int[n][n];
		int left = 0, top = 0;
		int right = n - 1, down = n - 1;
		int count = 1;
		while (left <= right) {
			for (int j = left; j <= right; j++) {
				ret[top][j] = count++;
			}
			top++;
			for (int i = top; i <= down; i++) {
				ret[i][right] = count++;
			}
			right--;
			for (int j = right; j >= left; j--) {
				ret[down][j] = count++;
			}
			down--;
			for (int i = down; i >= top; i--) {
				ret[i][left] = count++;
			}
			left++;
		}
		return ret;
	}
	
}
