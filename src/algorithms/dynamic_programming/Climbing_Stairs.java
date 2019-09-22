package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 3/23/2019.
 */
public class Climbing_Stairs {
	
	public static void main(String[] args) {
		Climbing_Stairs cs = new Climbing_Stairs();
		System.out.println(cs.climbStairs(3));
	}
	
	public int climbStairs(int n) {
		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		}
		int first = 1;
		int second = 2;
		int res = 0;
		for (int i = 2; i < n; i++) {
			res = first + second;
			first = second;
			second = res;
		}
		return res;
	}
	
	public int climbStairs_Brute_Force(int n) {
		return climb_Stairs(0, n);
	}
	
	public int climb_Stairs(int i, int n) {
		if (i > n) {
			return 0;
		}
		if (i == n) {
			return 1;
		}
		return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
	}
	
	public int climbStairs_DP(int n) {
		if (n == 1) {
			return 1;
		}
		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}
	
	public int climbStairs_FibFormula(int n) {
		double sqrt5 = Math.sqrt(5);
		double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
		return (int) (fibn / sqrt5);
	}
}
