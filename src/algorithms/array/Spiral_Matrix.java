package algorithms.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangxiao Wang on 2019-09-04
 *
 * @see Spiral_Matrix_II
 */
public class Spiral_Matrix {
	
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		spiralHelper(matrix, 0, 0, matrix.length, matrix[0].length, res);
		return res;
	}
	
	private void spiralHelper(int[][] matrix, int i, int j, int r, int c, List<Integer> res) {
		if (i >= r || j >= c)
			return;
		for (int k = i; k < c; k++) {
			res.add(matrix[i][k]);
		}
		for (int k = i + 1; k < r; k++) {
			res.add(matrix[k][c - 1]);
		}
		if (r - 1 != i) {
			for (int k = c - 2; k >= j; k--) {
				res.add(matrix[r - 1][k]);
			}
		}
		if (c - 1 != j) {
			for (int k = r - 2; k > i; k--) {
				res.add(matrix[k][j]);
			}
		}
		spiralHelper(matrix, i + 1, j + 1, r - 1, c - 1, res);
	}
	
	
	public class Solution {
		public List<Integer> spiralOrder(int[][] matrix) {
			List<Integer> res = new ArrayList<Integer>();
			if (matrix.length == 0) {
				return res;
			}
			
			int rowBegin = 0;
			int rowEnd = matrix.length - 1;
			int colBegin = 0;
			int colEnd = matrix[0].length - 1;
			
			while (rowBegin <= rowEnd && colBegin <= colEnd) {
				// Traverse Right
				for (int j = colBegin; j <= colEnd; j++) {
					res.add(matrix[rowBegin][j]);
				}
				rowBegin++;
				
				// Traverse Down
				for (int j = rowBegin; j <= rowEnd; j++) {
					res.add(matrix[j][colEnd]);
				}
				colEnd--;
				
				if (rowBegin <= rowEnd) {
					// Traverse Left
					for (int j = colEnd; j >= colBegin; j--) {
						res.add(matrix[rowEnd][j]);
					}
				}
				rowEnd--;
				
				if (colBegin <= colEnd) {
					// Traver Up
					for (int j = rowEnd; j >= rowBegin; j--) {
						res.add(matrix[j][colBegin]);
					}
				}
				colBegin++;
			}
			
			return res;
		}
	}
}
