package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 7/27/2019
 */
public class N_th_Tribonacci_Number {
	
	public int tribonacci(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 2 || n == 1) return 1;
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1;
		for (int i = 3; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		return dp[n];
	}
	
	
	public int tribonacci_sol(int n) {
		int first = 0, second = 1, third = 1;
		if (n < 2) return n;
		for (int i = 3; i <= n; i++) {
			int newElement = first + second + third;
			first = second;
			second = third;
			third = newElement;
		}
		return third;
	}
	
	public int tribonacci_Sol2(int n) {
		if (n < 2) return n;
		int a = 0, b = 1, c = 1, d;
		while (n-- > 2) {
			d = a + b + c;
			a = b;
			b = c;
			c = d;
		}
		return c;
	}
	
}
