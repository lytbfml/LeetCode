package algorithms.dynamic_programming;

import java.util.Arrays;

/**
 * @author Yangxiao Wang on 6/29/2019
 */
public class Filling_Bookcase_Shelves {
	
	// 0 thickness, 1 height
	public int minHeightShelves(int[][] books, int shelf_width) {
		int n = books.length;
		
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for (int i = 0; i < n; i++) {
			int thick = 0, height = 0;
			
			for (int j = i; j < n; j++) {
				thick += books[j][0];
				height = Math.max(height, books[j][1]);
				
				if (thick <= shelf_width) {
					dp[j + 1] = Math.min(dp[j + 1], dp[i] + height);
				}
			}
		}
		return dp[n];
	}
	
	public int minHeightShelves_Sol1(int[][] books, int shelf_width) {
		int[] dp = new int[books.length + 1];
		dp[0] = 0;
		
		for (int i = 1; i <= books.length; ++i) {
			int width = books[i - 1][0];
			int height = books[i - 1][1];
			dp[i] = dp[i - 1] + height;
			for (int j = i - 1; j > 0 && width + books[j - 1][0] <= shelf_width; --j) {
				height = Math.max(height, books[j - 1][1]);
				width += books[j - 1][0];
				dp[i] = Math.min(dp[i], dp[j - 1] + height);
			}
		}
		return dp[books.length];
	}
}