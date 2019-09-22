package algorithms.searching;

/**
 * @author Yangxiao on 9/13/2019.
 * @see algorithms.sort.Search_a_2D_Matrix_II
 */
public class Search_a_2D_Matrix {
	
	public boolean searchMatrix(int[][] matrix, int target) {
		int i = matrix.length - 1, j = 0;
		while(i >= 0 && j < matrix[0].length) {
			if (matrix[i][j] < target) {
				j++;
			} else if (matrix[i][j] > target) {
				i--;
			} else {
				return true;
			}
		}
		return false;
	}
	
	public boolean searchMatrix_bsearch(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}
		int n = matrix.length, m = matrix[0].length;
		int i = 0, j = n * m - 1;
		while(i <= j) { 
			int mid = i + (j - i) / 2;
			int cur = matrix[mid / m][mid % m];
			if (cur < target) {
				i = mid + 1;
			} else if (cur > target) {
				j = mid - 1;
			} else {
				return true;
			}
		}
		return false;
	}
}
