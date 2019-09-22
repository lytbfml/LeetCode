package algorithms.dynamic_programming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Yangxiao on 4/7/2019.
 */
public class Video_Stitching {
	
	public static void main(String[] args) {
		int[][] ss = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
		Video_Stitching cs = new Video_Stitching();
		System.out.println(cs.videoStitching_Sol(ss, 10));
	}
	
	public int videoStitching_Sol(int[][] clips, int T) {
		int n = clips.length;
		Arrays.sort(clips, Comparator.comparingInt(a -> a[0]));
		int count = 0;
		int cur = 0;
		for (int i = 0; i < n; ) {
			if (clips[i][0] > cur) {
				return -1;
			}
			int tempCur = cur;
			while (i < n && clips[i][0] <= cur) {
				tempCur = Math.max(tempCur, clips[i++][1]);
			}
			count++;
			cur = tempCur;
			if (cur >= T) {
				return count;
			}
		}
		return -1;
	}
	
	public int videoStitching(int[][] clips, int T) {
		int res = 0;
		Arrays.sort(clips, (a, b) -> a[0] - b[0]);
		for (int i = 0, st = 0, end = 0; st < T; st = end, ++res) {
			for (; i < clips.length && clips[i][0] <= st; ++i)
				end = Math.max(end, clips[i][1]);
			if (st == end) return -1;
		}
		return res;
	}
	
	public int videoStitching_DP(int[][] clips, int T) {
		int[] dp = new int[T + 1];
		Arrays.fill(dp, T + 1);
		dp[0] = 0;
		for (int i = 0; i <= T; i++) {
			for (int[] c : clips) {
				if (i >= c[0] && i <= c[1]) dp[i] = Math.min(dp[i], dp[c[0]] + 1);
			}
			if (dp[i] == T + 1) return -1;
		}
		return dp[T];
	}
}
