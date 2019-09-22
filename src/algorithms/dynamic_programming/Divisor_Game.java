package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 4/13/2019.
 */
public class Divisor_Game {
	
	public static void main(String[] args) {
		Divisor_Game cs = new Divisor_Game();
		// 4 true
		System.out.println(cs.divisorGame(4));
	}
	
	public boolean divisorGame(int N) {
		return N % 2 == 0;
	}
	
	
	public boolean divisorGame_DP(int N) {
		boolean[] dp = new boolean[N + 1];
		dp[0] = false;
		dp[1] = false;
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j < i; j++) {
				if (i % j == 0 && !dp[i - j]) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[N];
	}
	
	public boolean divisorGame_DP2(int N) {
		boolean[] dp = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int x = 1; x < i; x++) {
				if (i % x == 0 && !dp[i - x]) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[N];
	}
}
