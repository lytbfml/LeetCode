package algorithms.sort;

/**
 * @author Yangxiao on 4/4/2019.
 * @see algorithms.searching.Search_a_2D_Matrix
 */
public class Search_a_2D_Matrix_II {
	
	public static void main(String[] args) {
		int[][] dat = {{1, 4, 7, 11, 15},
		               {2, 5, 8, 12, 19},
		               {3, 6, 9, 16, 22},
		               {10, 13, 14, 17, 24},
		               {18, 21, 23, 26, 30}};
		Search_a_2D_Matrix_II cs = new Search_a_2D_Matrix_II();
		System.out.println(cs.searchMatrix(dat, 5));
	}
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int n = matrix.length;
		int m = matrix[0].length;
		int i = 0, j = m - 1;
		while (i < n && j >= 0) {
			if (matrix[i][j] < target) {
				i++;
			} else if (matrix[i][j] > target) {
				j--;
			} else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * The coding seems to be much more complex than those smart methods such as this one,
	 * but the idea behind is actually quite straightforward. Unfortunately, it is not as fast as the smart ones.
	 * <p>
	 * First, we divide the matrix into four quarters as shown below:
	 * <p>
	 * zone 1      zone 2
	 * *  *  *  * | *  *  *  *
	 * *  *  *  * | *  *  *  *
	 * *  *  *  * | *  *  *  *
	 * *  *  *  * | *  *  *  *
	 * -----------------------
	 * *  *  *  * | *  *  *  *
	 * *  *  *  * | *  *  *  *
	 * *  *  *  * | *  *  *  *
	 * *  *  *  * | *  *  *  *
	 * zone 3      zone 4
	 * <p>
	 * We then compare the element in the center of the matrix with the target. There are three possibilities:
	 * 1. center < target. In this case, we discard zone 1 because all elements in zone 1 are less than target.
	 * 2. center > target. In this case, we discard zone 4.
	 * 3. center == target. return true.
	 * <p>
	 * For time complexity, if the matrix is a square matrix of size nxn, then for the worst case,
	 * T(nxn) = 3T(n/2 x n/2)
	 * which makes
	 * T(nxn) = O(n^log3)
	 */
	class Solution_DivideAndCon {
		
		public boolean searchMatrix(int[][] matrix, int target) {
			int m = matrix.length;
			if (m < 1)
				return false;
			int n = matrix[0].length;
			return searchMatrix(matrix, new int[]{0, 0}, new int[]{m - 1, n - 1}, target);
		}
		
		private boolean searchMatrix(int[][] matrix, int[] upperLeft, int[] lowerRight, int target) {
			if (upperLeft[0] > lowerRight[0] || upperLeft[1] > lowerRight[1]
					|| lowerRight[0] >= matrix.length || lowerRight[1] >= matrix[0].length) {
				return false;
			}
			if (lowerRight[0] - upperLeft[0] == 0 && lowerRight[1] - upperLeft[1] == 0) {
				return matrix[upperLeft[0]][upperLeft[1]] == target;
			}
			int rowMid = (upperLeft[0] + lowerRight[0]) >> 1;
			int colMid = (upperLeft[1] + lowerRight[1]) >> 1;
			int diff = matrix[rowMid][colMid] - target;
			if (diff > 0) {
				return searchMatrix(matrix, upperLeft, new int[]{rowMid, colMid}, target)
						|| searchMatrix(matrix, new int[]{upperLeft[0], colMid + 1}, new int[]{rowMid,
						                                                                       lowerRight[1]}, target)
						|| searchMatrix(matrix, new int[]{rowMid + 1, upperLeft[1]}, new int[]{lowerRight[0],
						                                                                       colMid}, target);
			} else if (diff < 0) {
				return searchMatrix(matrix, new int[]{upperLeft[0], colMid + 1}, new int[]{rowMid,
				                                                                           lowerRight[1]}, target)
						|| searchMatrix(matrix, new int[]{rowMid + 1, upperLeft[1]}, new int[]{lowerRight[0],
						                                                                       colMid}, target)
						|| searchMatrix(matrix, new int[]{rowMid + 1, colMid + 1}, lowerRight, target);
			} else {
				return true;
			}
		}
	}
}
