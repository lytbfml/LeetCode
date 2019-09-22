package algorithms.array;

/**
 * @author Yangxiao on 11/9/2018.
 */

/**
 * You are given an n x n 2D matrix representing an image.
 * <p>
 * Rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix
 * directly. DO NOT allocate another 2D matrix and do the rotation.
 */
class Rotate_Image {
	
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < (n + 1) / 2; j++) {
				int pre = matrix[i][j];
				int cur = 0;
				for (int k = 0; k < 4; k++) {
					cur = matrix[j][n - i - 1];
					matrix[j][n - i - 1] = pre;
					int tt = i;
					i = j;
					j = n - tt - 1;
					pre = cur;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Rotate_Image s = new Rotate_Image();
		int[][] x = {{1, 2, 3},
		             {4, 5, 6},
		             {7, 8, 9}};
		int[][] y = {{1, 2, 3, 4},
		             {5, 6, 7, 8},
		             {9, 10, 11, 12},
		             {13, 14, 15, 16}};
		s.rotate(x);
		printout(x);
		s.rotate(y);
		printout(y);
	}
	
	public static void printout(int[][] s) {
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				System.out.print(s[i][j] + ",");
			}
			System.out.println();
		}
	}
}
