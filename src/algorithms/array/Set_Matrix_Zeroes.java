package algorithms.array;

/**
 * @author Yangxiao Wang on 5/16/2019
 */
public class Set_Matrix_Zeroes {
	
	public void setZeroes(int[][] a) {
		int r = a.length;
		int c = a[0].length;
		boolean r0 = false, c0 = false;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (a[i][j] == 0) {
					if (i == 0)
						r0 = true;
					if (j == 0)
						c0 = true;
					a[0][j] = 0;
					a[i][0] = 0;
				}
			}
		}
		
		for (int i = 1; i < r; i++) {
			for (int j = 1; j < c; j++) {
				if (a[i][0] == 0 || a[0][j] == 0) {
					a[i][j] = 0;
				}
			}
		}
		
		if (r0) {
			for (int i = 0; i < c; i++) {
				a[0][i] = 0;
			}
		}
		if (c0) {
			for (int i = 0; i < r; i++) {
				a[i][0] = 0;
			}
		}
	}
}
