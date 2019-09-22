package algorithms.sort;


import java.util.Arrays;

/**
 * @author Yangxiao Wang on 2019-07-08
 */
public class K_Closest_to_original {
	
	public int[][] kClosest(int[][] points, int K) {
		int[][] res = new int[K][2];
		
		Arrays.sort(points, (a, b) -> {
			if (Math.abs(a[0]) < Math.abs(b[0]) && Math.abs(a[1]) < Math.abs(b[1]))
				return -1;
			if (Math.abs(a[0]) == Math.abs(b[0]) && Math.abs(a[1]) == Math.abs(b[1]))
				return 0;
			return (distance(a[0], a[1]) - distance(b[0], b[1])) > 0 ? 1 : -1;
		});
		for (int i = 0; i < K; i++) {
			res[i][0] = points[i][0];
			res[i][1] = points[i][1];
		}
		return res;
	}
	
	public int[][] kClosest2(int[][] points, int K) {
		int n = points.length;
		Point[] po = new Point[n];
		for (int i = 0; i < n; i++) {
			po[i] = new Point(points[i][0], points[i][1], distance(points[i]));
		}
		Arrays.sort(po, (a, b) -> a.d - b.d > 0 ? 1 : a.d - b.d < 0 ? -1 : 0);
		int[][] res = new int[K][2];
		for (int i = 0; i < K; i++) {
			res[i][0] = po[i].i;
			res[i][1] = po[i].j;
		}
		return res;
	}
	
	class Solution_qSort_fast {
		public int[][] kClosest(int[][] points, int K) {
			int n = points.length;
			int l = 0, r = n - 1;
			while (l < r) {
				int pos = helper(points, l, r);
				if (pos > K) {
					r = pos - 1;
				} else if (pos < K) {
					l = pos + 1;
				} else {
					break;
				}
			}
			
			return Arrays.copyOfRange(points, 0, K);
		}
		
		private int helper(int[][] a, int l, int r) {
			int p = (int) (Math.random() * (r + 1 - l)) + l;
			int dis = distance(a[p]);
			swap(a, p, r);
			int ind = l;
			for (int i = l; i < r; i++) {
				if (distance(a[i]) <= dis) {
					swap(a, ind, i);
					ind++;
				}
			}
			swap(a, ind, r);
			return ind;
		}
	}
	
	class Solution_qsortAlt {
		
		public int[][] kClosest(int[][] points, int K) {
			helper(points, K, 0, points.length - 1);
			return Arrays.copyOfRange(points, 0, K);
		}
		
		private void helper(int[][] points, int k, int lo, int hi) {
			if (lo >= hi) {
				return;
			}
			
			int left = lo;
			int right = hi;
			int[] pivot = points[lo + (hi - lo) / 2];
			
			while (left <= right) {
				while (left <= right && compare(points[left], pivot) < 0) {
					left++;
				}
				while (left <= right && compare(points[right], pivot) > 0) {
					right--;
				}
				
				if (left <= right) {
					swap(points, left++, right--);
				}
			}
			
			if (lo + k - 1 <= right) {
				helper(points, k, lo, right);
			}
			if (lo + k - 1 >= left) {
				helper(points, k - (left - lo), left, hi);
			}
		}
		
		private int compare(int[] p1, int[] p2) {
			return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
		}
		
		private void swap(int[][] points, int i, int j) {
			int[] temp = points[i];
			points[i] = points[j];
			points[j] = temp;
		}
	}
	
	private void swap(int[][] pp, int i, int j) {
		int[] a = pp[i];
		pp[i] = pp[j];
		pp[j] = a;
	}
	
	private int distance(int i, int j) {
		return i * i + j * j;
	}
	
	private int distance(int[] i) {
		return distance(i[0], i[1]);
	}
	
	private int compare(int[] p1, int[] p2) {
		return (p1[0] * p1[0] + p1[1] * p1[1]) - (p2[0] * p2[0] + p2[1] * p2[1]);
	}
	
	class Point {
		int i;
		int j;
		double d;
		
		public Point(int i, int j, double d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}
	}
}
