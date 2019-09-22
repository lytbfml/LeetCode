package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 5/14/2019.
 */
public class Selling_Candy {
	
	public static void main(String[] args) {
		int[] a = {1, 2, 4, 4, 5, 0};
		Selling_Candy cs = new Selling_Candy();
		System.out.println(cs.sellCandy(a));
	}
	
	/**
	 * Let's say that you have n identical pieces of candy that you want to sell for as much as possible.
	 * Oddly, you can sometimes make more by selling a subset together, rather than as individual pieces.
	 * You are given a table that says for each  0 < i <= n
	 * how much profit you can achieve for a set of i pieces of candy.
	 * For i pieces, your profit is pi.
	 * Note that selling a set with i pieces of candy does not prevent you from
	 * selling another set with the same number of candies for the same price (if you still have enough left).
	 * Your goal is to develop an algorithm that maximizes the profit of selling all of the
	 * candy by determining the highest value division of the n pieces.
	 */
	public int sellCandy(int[] a) {
		int n = a.length;
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			for (int k = 0; k < i; k++) {
				dp[i] = Math.max(dp[i - k - 1] + a[k], dp[i]);
			}
		}
		return dp[n];
	}
}
