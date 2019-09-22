package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 11/17/2018.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array A of strings, find any smallest string that contains each string in A as a
 * substring.
 * <p>
 * We may assume that no string in A is substring of another string in A.
 * <p>
 * Example 1:
 * Input: ["alex","loves","leetcode"]
 * Output: "alexlovesleetcode"
 * Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
 * <p>
 * Example 2:
 * Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
 * Output: "gctaagttcatgcatc"
 * <p>
 * <p>
 * Note:
 * 1 <= A.length <= 12
 * 1 <= A[i].length <= 20
 */
class Find_the_Shortest_Superstring {
	
	public String shortestSuperstring(String[] A) {
		ArrayList<String> list = new ArrayList<>(Arrays.asList(A));
		while (list.size() > 1) {
			int matched = -1;
			int ind1 = 0;
			int ind2 = 0;
			for (int m = 0; m < list.size(); m++) {
				String sb = list.get(m);
				for (int i = 0; i < list.size(); i++) {
					String t = list.get(i);
					if (t.equals(sb)) { continue; }
					int len = t.length();
					for (int j = 0; j < len; j++) {
						String backS = t.substring(j);
						String frontS = t.substring(0, j + 1);
						int backt = sb.indexOf(backS);
						int frontt = sb.indexOf(frontS);
						if (backt != -1 && backt == 0 && matched < backS.length()) {
							matched = backS.length();
							ind1 = i;
							ind2 = m;
						}
						if (frontt != -1 && frontt == sb.length() - frontS.length() &&
								matched < frontS.length()) {
							matched = frontS.length();
							ind1 = m;
							ind2 = i;
						}
					}
				}
			}
			if (matched == -1) {
				String x1 = list.get(0);
				String x2 = list.get(1);
				list.remove(x1);
				list.remove(x2);
				list.add(x1 + x2);
			} else {
				String x1 = list.get(ind1);
				String x2 = list.get(ind2);
				list.remove(x1);
				list.remove(x2);
				String result = x1;
				result = result + x2.substring(matched);
				list.add(result);
			}
		}
		return list.get(0);
	}
	
	class Solution1 {
		public String shortestSuperstring_Sol1(String[] A) {
			int length = A.length;
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < A.length; i++) {
				list.add(A[i]);
			}
			int size = list.size();
			while (list.size() > 1) {
				int maxLength = -1, index1 = 0, index2 = 0;
				for (int i = 0; i < list.size(); i++) {
					for (int j = i + 1; j < list.size(); j++) {
						int matchLength1 = getMatchedLength(list.get(i), list.get(j));
						int matchLength2 = getMatchedLength(list.get(j), list.get(i));
						if (maxLength < matchLength1) {
							maxLength = matchLength1;
							index1 = i;
							index2 = j;
						}
						if (maxLength < matchLength2) {
							maxLength = matchLength2;
							index1 = j;
							index2 = i;
						}
					}
				}
				String merged = getMergeString(index1, index2, maxLength, list);
				String item1 = list.get(index1);
				String item2 = list.get(index2);
				list.remove(item1);
				list.remove(item2);
				list.add(merged);
				size--;
			}
			return list.get(0);
		}
		
		private String getMergeString(int index1, int index2, int maxLength, List<String> list) {
			String result = "";
			result = result + list.get(index1);
			result = result + list.get(index2).substring(maxLength);
			return result;
		}
		
		private int getMatchedLength(String s1, String s2) {
			
			int len = Math.min(s1.length(), s2.length());
			int count = 0;
			for (int i = len; i > 0; i--) {
				String substring = s2.substring(0, i);
				if (s1.endsWith(substring)) {
					count = substring.length();
					break;
				}
			}
			return count;
		}
	}
	
	public String shortestSuperstring_Sol2(String[] A) {
		int n = A.length;
		String[][] dp = new String[1 << n][n];
		for (int i = 1; i < 1 << n; i++) {
			if (Integer.bitCount(i) == 1) {
				dp[i][Integer.numberOfTrailingZeros(i)] = A[Integer.numberOfTrailingZeros(i)];
				continue;
			}
			for (int k = 0; k < n; k++) {
				if (i << ~k < 0) {
					for (int j = 0; j < n; j++) { // j->k
						if (i << ~j < 0 && j != k) {
							for (int l = A[k].length(); l >= 0; l--) {
								if (dp[i ^ 1 << k][j].endsWith(A[k].substring(0, l))) {
									String can = dp[i ^ 1 << k][j] + A[k].substring(l);
									if (dp[i][k] == null || dp[i][k].length() > can.length()) {
										dp[i][k] = can;
									}
									break;
								}
							}
						}
					}
				}
			}
		}
		String ret = dp[(1 << n) - 1][0];
		for (int i = 1; i < n; i++) {
			if (dp[(1 << n) - 1][i].length() < ret.length()) {
				ret = dp[(1 << n) - 1][i];
			}
		}
		
		return ret;
	}
	
	class Solution2 {
		public String shortestSuperstring(String[] A) {
			int N = A.length;
			
			// Populate overlaps
			int[][] overlaps = new int[N][N];
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (i != j) {
						int m = Math.min(A[i].length(), A[j].length());
						for (int k = m; k >= 0; --k) {
							if (A[i].endsWith(A[j].substring(0, k))) {
								overlaps[i][j] = k;
								break;
							}
						}
					}
				}
			}
			
			// dp[mask][i] = most overlap with mask, ending with ith element
			int[][] dp = new int[1 << N][N];
			int[][] parent = new int[1 << N][N];
			for (int mask = 0; mask < (1 << N); ++mask) {
				Arrays.fill(parent[mask], -1);
				
				for (int bit = 0; bit < N; ++bit) {
					if (((mask >> bit) & 1) > 0) {
						// Let's try to find dp[mask][bit].  Previously, we had
						// a collection of items represented by pmask.
						int pmask = mask ^ (1 << bit);
						if (pmask == 0) { continue; }
						for (int i = 0; i < N; ++i) {
							if (((pmask >> i) & 1) > 0) {
								// For each bit i in pmask, calculate the value
								// if we ended with word i, then added word 'bit'.
								int val = dp[pmask][i] + overlaps[i][bit];
								if (val > dp[mask][bit]) {
									dp[mask][bit] = val;
									parent[mask][bit] = i;
								}
							}
						}
					}
				}
			}
			
			// # Answer will have length sum(len(A[i]) for i) - max(dp[-1])
			// Reconstruct answer, first as a sequence 'perm' representing
			// the indices of each word from left to right.
			
			int[] perm = new int[N];
			boolean[] seen = new boolean[N];
			int t = 0;
			int mask = (1 << N) - 1;
			
			// p: the last element of perm (last word written left to right)
			int p = 0;
			for (int j = 0; j < N; ++j) {
				if (dp[(1 << N) - 1][j] > dp[(1 << N) - 1][p]) { p = j; }
			}
			
			// Follow parents down backwards path that retains maximum overlap
			while (p != -1) {
				perm[t++] = p;
				seen[p] = true;
				int p2 = parent[mask][p];
				mask ^= 1 << p;
				p = p2;
			}
			
			// Reverse perm
			for (int i = 0; i < t / 2; ++i) {
				int v = perm[i];
				perm[i] = perm[t - 1 - i];
				perm[t - 1 - i] = v;
			}
			
			// Fill in remaining words not yet added
			for (int i = 0; i < N; ++i) { if (!seen[i]) { perm[t++] = i; } }
			
			// Reconstruct final answer given perm
			StringBuilder ans = new StringBuilder(A[perm[0]]);
			for (int i = 1; i < N; ++i) {
				int overlap = overlaps[perm[i - 1]][perm[i]];
				ans.append(A[perm[i]].substring(overlap));
			}
			
			return ans.toString();
		}
	}
	
	public static void main(String[] args) {
		Find_the_Shortest_Superstring fs = new Find_the_Shortest_Superstring();
		String[][] s = {{"ift", "efd", "dnete", "tef", "fdn"}, {"alex", "loves", "leetcode"}};
		for (int i = 0; i < s.length; i++) {
			System.out.println(fs.shortestSuperstring(s[i]));
		}
		
	}
}
