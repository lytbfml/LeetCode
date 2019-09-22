package algorithms.string;

/**
 * @author Yangxiao Wang on 8/26/2019
 */
public class Compare_Strings_by_Frequency_of_the_Smallest_Character {
	
	public int[] numSmallerByFrequency(String[] q, String[] w) {
		int n = q.length, m = w.length;
		int[] wordsF = new int[m];
		int[] res = new int[n];
		for (int j = 0; j < m; j++) {
			wordsF[j] = funcF(w[j]);
		}
		
		for (int i = 0; i < n; i++) {
			int curF = funcF(q[i]);
			for (int j = 0; j < m; j++) {
				if (curF < wordsF[j]) {
					res[i]++;
				}
			}
		}
		return res;
	}
	
	private int funcF(String s) {
		int[] mm = new int[26];
		int small = 26;
		for (int i = 0; i < s.length(); i++) {
			int cur = s.charAt(i) - 'a';
			if (cur < small)
				small = cur;
			mm[cur]++;
		}
		return mm[small];
	}
	
	
	class Solution2 {
		public int[] numSmallerByFrequency(String[] queries, String[] words) {
			int qn = queries.length, wn = words.length;
			int[] res = new int[qn];
			int[] fre = new int[11];
			for (String w : words) {
				int cur = helper(w);
				fre[cur]++;
			}
			int[] sum = new int[11];
			sum[10] = fre[10];
			for (int i = 9; i > 0; i--) {
				sum[i] = fre[i] + sum[i + 1];
			}
			for (int i = 0; i < qn; i++) {
				int cur = helper(queries[i]);
				res[i] = cur == 10 ? 0 : sum[cur + 1];
			}
			return res;
		}
		
		private int helper(String s) {
			int len = s.length(), count = 1;
			char min = s.charAt(0);
			for (int i = 1; i < len; i++) {
				char cur = s.charAt(i);
				if (cur == min) count++;
				else if (cur < min) {
					min = cur;
					count = 1;
				}
			}
			return count;
		}
	}
}
