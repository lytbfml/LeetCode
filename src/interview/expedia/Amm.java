package interview.expedia;

/**
 * @author Yangxiao on 11/16/2018.
 */

class Amm {
	
	public int getRe(int[] source, int[] factor) {
		if (source == null || factor == null) {
			return 0;
		}
		int n = source.length;
		int f = factor.length;
		if (n == 0 || f == 0) {
			return 0;
		}
		int[][] result = new int[n][f];
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < f; j++) {
				if (source[i] % factor[j] == 0) {
					if (i == 0) {
						result[i][j]++;
						max = Math.max(max, result[i][j]);
					} else {
						result[i][j] = result[i - 1][j] + 1;
						max = Math.max(max, result[i][j]);
					}
				}
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		Amm mm = new Amm();
		int[] x1 = {3,3,1,4,30,12,9,6,1,9,9,9,9,3,3,3};
		int[] x2 = {3,4};
		int x = mm.getRe(x1, x2);
		System.out.println(x);
	}
}
