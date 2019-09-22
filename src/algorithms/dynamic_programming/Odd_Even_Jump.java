package algorithms.dynamic_programming;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Yangxiao Wang on 2019-07-02
 * <p>
 * You are given an integer array A.  From some starting index, you can make a series of jumps.  The (1st, 3rd, 5th,
 * ...) jumps in the series are called odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called
 * even numbered jumps.
 * <p>
 * You may from index i jump forward to index j (with i < j) in the following way:
 * <p>
 * During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the
 * smallest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
 * During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the
 * largest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j. (It
 * may be the case that for some index i, there are no legal jumps.) A starting index is good if, starting from that
 * index, you can reach the end of the array (index A.length - 1) by jumping some number of times (possibly 0 or more
 * than once.)
 * <p>
 * Return the number of good starting indexes.
 */
public class Odd_Even_Jump {

	public static void main(String[] args) {
		Odd_Even_Jump cs = new Odd_Even_Jump();
		System.out.println(cs.oddEvenJumps(new int[]{10, 13, 12, 14, 15}));
		System.out.println(cs.oddEvenJumps(new int[]{2, 3, 1, 1, 4}));
		System.out.println(cs.oddEvenJumps(new int[]{5, 1, 3, 4, 2}));
		System.out.println(cs.oddEvenJumps(new int[]{1, 1, 1, 1, 1}));
		System.out.println(cs.oddEvenJumps(new int[]{1, 1}));
	}

	// [10,13,12,14,15]
	// [2,3,1,1,4]
	// [5,1,3,4,2]
	// [1, 1, 1, 1, 1]
	// [1,1]
	public int oddEvenJumps(int[] A) {
		int n = A.length;
		int[][] dp = new int[n][2];
		for (int i = 0; i < n; i++) {
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			int minInd = -1;
			int maxInd = -1;
			dp[i][0]++;
			for (int j = i + 1; j < n; j++) {
				if (A[i] <= A[j] && A[j] < min) {
					min = A[j];
					minInd = j;
				}
				if (A[i] >= A[j] && A[j] > max) {
					max = A[j];
					maxInd = j;
				}
			}
			if (maxInd != -1) {
				dp[maxInd][0] += dp[i][1];
			}
			if (minInd != -1) {
				dp[minInd][1] += dp[i][0];
			}
		}

		return dp[n - 1][0] + dp[n - 1][1];
	}

	public int oddEvenJumps_Sol(int[] A) {
		int n = A.length;
		int res = 1;
		boolean[] odd = new boolean[n], even = new boolean[n];
		odd[n - 1] = even[n - 1] = true;
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(A[n - 1], n - 1);
		for (int i = n - 2; i >= 0; --i) {
			// Treemap.get, put, contains and remove costs O(logN)
			Map.Entry hi = map.ceilingEntry(A[i]), lo = map.floorEntry(A[i]);
			if (hi != null)
				odd[i] = even[(int) hi.getValue()];
			if (lo != null)
				even[i] = odd[(int) lo.getValue()];
			if (odd[i])
				res++;
			map.put(A[i], i);
		}
		return res;
	}

	class Solution {
		public int oddEvenJumps(int[] A) {
			int N = A.length;
			if (N <= 1)
				return N;
			boolean[] odd = new boolean[N];
			boolean[] even = new boolean[N];
			odd[N - 1] = even[N - 1] = true;

			TreeMap<Integer, Integer> vals = new TreeMap<>();
			vals.put(A[N - 1], N - 1);
			for (int i = N - 2; i >= 0; --i) {
				int v = A[i];
				if (vals.containsKey(v)) {
					odd[i] = even[vals.get(v)];
					even[i] = odd[vals.get(v)];
				} else {
					Integer lower = vals.lowerKey(v);
					Integer higher = vals.higherKey(v);

					if (lower != null)
						even[i] = odd[vals.get(lower)];
					if (higher != null) {
						odd[i] = even[vals.get(higher)];
					}
				}
				vals.put(v, i);
			}

			int ans = 0;
			for (boolean b : odd)
				if (b)
					ans++;
			return ans;
		}
	}

	class Solution_fast {
		private int minIndex(int index, int[] map) {
			for (; index < map.length; index++)
				if (map[index] > 0) return map[index];
			return -1;
		}

		private int maxIndex(int index, int[] map) {
			for (; index >= 0; index--)
				if (map[index] > 0) return map[index];
			return -1;
		}

		public int oddEvenJumps(int[] A) {
			int len = A.length;
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < len; i++) {
				max = Integer.max(max, A[i]);
			}
			boolean[][] record = new boolean[len][2];
			record[len - 1][0] = true;
			record[len - 1][1] = true;
			int count = 1;

			int[] map = new int[max + 1];
			map[A[len - 1]] = len - 1;
			for (int i = len - 2; i >= 0; i--) {
				int gt = minIndex(A[i], map);
				int lt = maxIndex(A[i], map);

				if (gt > 0 && record[gt][0]) {
					record[i][1] = true;
					count++;
				}
				if (lt > 0 && record[lt][1]) {
					record[i][0] = true;
				}
				map[A[i]] = i;
			}
			return count;
		}
	}

	class Solution_fastandSpace {
		int rerange(int[] A) {
			int min, max;
			min = max = A[0];
			for (int v : A) {
				if (v < min) min = v;
				if (v > max) max = v;
			}
			int[] map = new int[max - min + 1];
			for (int v : A) {
				map[v - min] = 1;
			}
			int ix = 0;
			for (int i = 0; i < map.length; i++) {
				if (map[i] != 0) map[i] = ++ix;
			}
			for (int i = 0; i < A.length; i++) {
				A[i] = map[A[i] - min];
			}
			return ix;
		}

		int ceilingIndex(int[] map, int v) {
			for (; v < map.length; v++) {
				if (map[v] != 0)
					return map[v];
			}
			return 0;
		}

		int floorIndex(int[] map, int v) {
			for (; v > 0; v--) {
				if (map[v] != 0)
					return map[v];
			}
			return 0;
		}

		public int oddEvenJumps(int[] A) {
			int max = rerange(A);
			int[] map = new int[max + 1];
			int res = 1;
			final int N = A.length;
			map[A[N - 1]] = N - 1;
			boolean[] odds = new boolean[N];
			boolean[] evens = new boolean[N];
			odds[N - 1] = evens[N - 1] = true;
			for (int i = N - 2; i >= 0; i--) {
				int key = A[i];
				int minGE = ceilingIndex(map, key);
				int maxLE = floorIndex(map, key);
				if (minGE != 0 && evens[minGE]) {
					res++;
					odds[i] = true;
				}
				if (maxLE != 0 && odds[maxLE]) {
					evens[i] = true;
				}
				map[key] = i;
			}
			return res;
		}

	}
}
