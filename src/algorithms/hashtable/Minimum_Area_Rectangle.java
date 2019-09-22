package algorithms.hashtable;

/**
 * @author Yangxiao on 11/10/2018.
 */

import java.util.*;

/**
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from
 * these points, with sides parallel to the x and y axes.
 * <p>
 * If there isn't any rectangle, return 0.
 */
class Minimum_Area_Rectangle {
	
	public int minAreaRect(int[][] points) {
		int n = points.length;
		int result = Integer.MAX_VALUE;
		boolean found = false;
		//		HashMap<Integer, Set<Integer>> yMap = new HashMap();
		HashMap<Integer, Set<Integer>> xMap = new HashMap();
		for (int i = 0; i < n; i++) {
			int x = points[i][0];
			int y = points[i][1];
			//			if (yMap.containsKey(y)) {
			//				yMap.get(y).add(x);
			//			} else {
			//				yMap.put(y, new HashSet<>(Arrays.asList(x)));
			//			}
			if (xMap.containsKey(x)) {
				xMap.get(x).add(y);
			} else {
				xMap.put(x, new HashSet<>(Arrays.asList(y)));
			}
		}
		if (xMap.isEmpty() || xMap.size() == 1/* || yMap.isEmpty() || yMap.size() == 1*/) {
			return 0;
		}
		
		ArrayList<Integer> sortedX = new ArrayList<Integer>();
		for (Map.Entry<Integer, Set<Integer>> e : xMap.entrySet()) {
			if (e.getValue().size() >= 2) {
				sortedX.add(e.getKey());
			}
		}
		//		ArrayList<Integer> sortedY = new ArrayList<Integer>(yMap.keySet());
		Collections.sort(sortedX);
		//		Collections.sort(sortedY);
		for (int i = 0; i < sortedX.size(); i++) {
			for (int j = i + 1; j < sortedX.size(); j++) {
				Set<Integer> xx = new HashSet<>(xMap.get(sortedX.get(i)));
				xx.retainAll(xMap.get(sortedX.get(j)));
				if (xx.size() >= 2) {
					found = true;
					ArrayList<Integer> arr = new ArrayList<>(xx);
					Collections.sort(arr);
					int dis = Integer.MAX_VALUE;
					for (int k = 1; k < arr.size(); k++) {
						int x = Math.abs(arr.get(k) - arr.get(k - 1));
						if (x < dis) {
							dis = x;
						}
					}
					int area = dis * Math.abs(sortedX.get(i) - sortedX.get(j));
					if (area < result) {
						result = area;
					}
				}
			}
		}
		return found ? result : 0;
	}
	
	public int minAreaRect_Sol1(int[][] points) {
		int n = points.length;
		Set<Long> set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			set.add((long) points[i][0] << 32 | points[i][1]);
		}
		
		int ret = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				long S = Math.abs(
						(long) (points[i][0] - points[j][0]) * (points[i][1] - points[j][1]));
				if (S == 0) { continue; }
				long x = (long) points[i][0] << 32 | points[j][1];
				if (!set.contains(x)) { continue; }
				x = (long) points[j][0] << 32 | points[i][1];
				if (!set.contains(x)) { continue; }
				ret = Math.min(ret, (int) S);
			}
		}
		if (ret == Integer.MAX_VALUE) { return 0; }
		return ret;
	}
	
	public int minAreaRect_Sol2(int[][] points) {
		Set<Integer> pointSet = new HashSet();
		for (int[] point : points)
			pointSet.add(40001 * point[0] + point[1]);
		
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < points.length; ++i)
			for (int j = i + 1; j < points.length; ++j) {
				if (points[i][0] != points[j][0] && points[i][1] != points[j][1]) {
					if (pointSet.contains(40001 * points[i][0] + points[j][1]) &&
							pointSet.contains(40001 * points[j][0] + points[i][1])) {
						ans = Math.min(ans, Math.abs(points[j][0] - points[i][0]) *
								Math.abs(points[j][1] - points[i][1]));
					}
				}
			}
		
		return ans < Integer.MAX_VALUE ? ans : 0;
	}
	
	public int distinctSubseqII(String S) {
		long end[] = new long[26], mod = (long) 1e9 + 7;
		for (char c : S.toCharArray())
			end[c - 'a'] = Arrays.stream(end).sum() % mod + 1;
		return (int) (Arrays.stream(end).sum() % mod);
	}
	
	public static void main(String[] args) {
		Minimum_Area_Rectangle ma = new Minimum_Area_Rectangle();
		int[][] points = {{1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}};
		System.out.println(ma.minAreaRect(points));
		
		int[][] pp = {{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}};
		System.out.println(ma.minAreaRect(pp));
	}
}
