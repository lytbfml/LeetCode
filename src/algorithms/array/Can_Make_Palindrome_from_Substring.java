package algorithms.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangxiao on 8/31/2019.
 * <p>
 * wc152
 */
public class Can_Make_Palindrome_from_Substring {
	public List<Boolean> canMakePaliQueries(String s, int[][] q) {
		int n = s.length(), m = q.length;
		char[] arr = s.toCharArray();
		int[][] mem = new int[26][n + 1];
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < n; j++) {
				mem[i][j + 1] = mem[i][j] + (arr[j] - 'a' == i ? 1 : 0);
			}
		}
		List<Boolean> res = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			int cnt = 0;
			for (int j = 0; j < 26; j++) {
				if ((mem[j][q[i][1] + 1] - mem[j][q[i][0]]) % 2 != 0) {
					cnt++;
				}
			}
			res.add((cnt / 2) <= q[i][2]);
		}
		return res;
	}
}
