package algorithms.searching;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yangxiao Wang on 8/10/2019
 */
public class Online_Majority_Element_In_Subarray {
	
	public static void main(String[] args) {
		MajorityChecker_SegmentTree cs = new MajorityChecker_SegmentTree(
				new int[]{8, 8, 8, 4, 8, 6, 8, 8});
		// 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
		cs.query(1, 7, 5);
	}
	
	class MajorityChecker {
		
		int[] arr;
		Map<Pair, Pair> map;
		
		public MajorityChecker(int[] arr) {
			this.arr = arr;
			map = new HashMap<>();
		}
		
		public int query(int left, int right, int threshold) {
			Pair ss = new Pair(left, right);
			if (map.containsKey(ss)) {
				Pair pp = map.get(ss);
				return pp.l >= threshold ? pp.r : -1;
			}
			
			int count = 0;
			Integer candidate = null;
			
			for (int i = left; i <= right; i++) {
				if (count == 0) {
					candidate = arr[i];
				}
				count += (arr[i] == candidate) ? 1 : -1;
			}
			int cnt = 0;
			for (int i = left; i <= right; i++) {
				if (arr[i] == candidate) {
					cnt++;
				}
			}
			
			map.put(new Pair(left, right), new Pair(cnt, candidate));
			return cnt >= candidate ? candidate : -1;
		}
		
		class Pair {
			int l;
			int r;
			
			public Pair(int l, int r) {
				this.l = l;
				this.r = r;
			}
			
			@Override
			public boolean equals(Object o) {
				if (this == o) return true;
				if (!(o instanceof Pair)) return false;
				Pair pair = (Pair) o;
				return l == pair.l &&
						r == pair.r;
			}
			
			@Override
			public int hashCode() {
				return Objects.hash(l, r);
			}
		}
	}
	
	static class MajorityChecker_SegmentTree {
		
		public int len, level;
		public Map<Integer, TreeMap<Integer, Integer>> prefixSum;
		public int[] segmentTreeX;
		public int[] segmentTreeY;
		
		public MajorityChecker_SegmentTree(int[] arr) {
			len = arr.length;
			
			// Map<k, Map<Index of k in arr, number of key in arr from 0 - index>
			prefixSum = new HashMap<>();
			for (int i = 0; i < len; i++) {
				if (!prefixSum.containsKey(arr[i])) prefixSum.put(arr[i], new TreeMap<>());
				Integer find = prefixSum.get(arr[i]).lowerKey(i);
				if (find == null) {
					prefixSum.get(arr[i]).put(i, 1);
				} else {
					prefixSum.get(arr[i]).put(i, 1 + prefixSum.get(arr[i]).get(find));
				}
			}
			
			// number of levels, if (2 ^ (level - 1) < len), for len = 15 (4 level), level = 5
			level = 1;
			while ((1 << (level - 1)) < len) {
				level++;
			}
			
			// 1 << level = 2 ^ level = number of nodes in tree + 1
			segmentTreeX = new int[1 << level];
			segmentTreeY = new int[1 << level];
			
			// two tree, one bottom all 1s, one bottom arr
			for (int i = 0; i < len; i++) {
				segmentTreeX[(1 << (level - 1)) + i] = arr[i];
				segmentTreeY[(1 << (level - 1)) + i] = 1;
			}
			
			// ((1 << level) - 1) = number of nodes in tree = last index of segmentTree
			// for tree 1-8:
			//        0                         1
			//    0       0                 1       5
			//  0   0   0   0     --->>   1   3   5   7
			// 1 2 3 4 5 6 7 8           1 2 3 4 5 6 7 8
			// check if any two are same ->
			// node from 1-8 represent the majority element of its child
			//
			//
			// for tree 1, 2, 3, 4, 5, 6, 8, 8:
			//        0                         8
			//    0       0                 1       8
			//  0   0   0   0     --->>   1   3   5   8
			// 1 2 3 4 5 6 8 8           1 2 3 4 5 6 8 8
			//        0                         2
			//    0       0                 0       2
			//  0   0   0   0     --->>   0   0   0   2
			// 1 1 1 1 1 1 1 1           1 1 1 1 1 1 1 1
			for (int i = ((1 << level) - 1) / 2; i >= 1; i--) {
				int left = segmentTreeX[i << 1];
				int right = segmentTreeX[(i << 1) + 1];
				int leftFreq = segmentTreeY[i << 1];
				int rightFreq = segmentTreeY[(i << 1) + 1];
				if (leftFreq >= rightFreq) {
					if (left == right) {
						segmentTreeX[i] = left;
						segmentTreeY[i] = leftFreq + rightFreq;
					} else {
						segmentTreeX[i] = left;
						segmentTreeY[i] = leftFreq - rightFreq;
					}
				} else {
					if (left == right) {
						segmentTreeX[i] = right;
						segmentTreeY[i] = leftFreq + rightFreq;
					} else {
						segmentTreeX[i] = right;
						segmentTreeY[i] = rightFreq - leftFreq;
					}
				}
			}
		}
		
		public int query(int left, int right, int threshold) {
			Set<Integer> candidates = new HashSet<>();
			int i = left + (1 << (level - 1)); // i = index of left in segmentTreeX
			int right_index = right + (1 << (level - 1));
			while (i <= right_index) {
				int step = 0;
				while ((i >> step) % 2 == 0) { // if i + (1 << (step + 1) exceed current sub tree,
					if (i + (1 << (step + 1)) > right_index) {
						break;
					}
					step++;
				}
				candidates.add(segmentTreeX[i >> step]); // parent of i and i + (1 << (step + 1))
				i += (1 << step);
			}
			// System.out.println(left + " " + right + ": " + (right - left + 1) + " " + candidates.size());
			for (Integer c : candidates) {
				int freq = 0;
				if (prefixSum.get(c).containsKey(right)) {
					freq += prefixSum.get(c).get(right);
				} else {
					Integer find = prefixSum.get(c).lowerKey(right);
					if (find != null) freq += prefixSum.get(c).get(find);
				}
				if (prefixSum.get(c).containsKey(left - 1)) {
					freq -= prefixSum.get(c).get(left - 1);
				} else {
					Integer find = prefixSum.get(c).lowerKey(left - 1);
					if (find != null) freq -= prefixSum.get(c).get(find);
				}
				if (freq >= threshold) return c;
			}
			return -1;
		}
	}
	
	class Solution_tradeOff {
		int shard;
		int[] arr;
		Map<Integer, List<Integer>> sortedMap;
		
		public Solution_tradeOff(int[] arr) {
			this.arr = arr;
			
			// sortedMap stores the positions of each element
			sortedMap = new HashMap<>();
			for (int i = 0; i < arr.length; i++) {
				if (!sortedMap.containsKey(arr[i])) {
					sortedMap.put(arr[i], new ArrayList<>());
				}
				sortedMap.get(arr[i]).add(i);
			}
			
			// sort the map by the number of occurrences in the array
			sortedMap = sortedMap.entrySet().stream().sorted((a, b) -> b.getValue().size() - a.getValue().size())
					.collect(Collectors.toMap(a -> a.getKey(), a -> a.getValue(), (e1, e2) -> e1, LinkedHashMap::new));
			
			shard = (int) Math.sqrt(arr.length);
		}
		
		public int query(int left, int right, int threshold) {
			if (right - left + 1 > shard) {
				// for long array, starting from the element that appears the most in the array
				for (int key : sortedMap.keySet()) {
					List<Integer> positions = sortedMap.get(key);
					
					// stop finding when the elements are less than threshold, since all the elements appearing after this will have elements less than threshold as well
					if (positions.size() < threshold) {
						break;
					}
					
					// among the positions of the element in the array, find the index of left and right so that we know how many elements are there in range [left, right]
					// for example, positions = [0,1,3,5,9,10], left = 1, right = 5
					// we binary search for 1 and 5 and => leftIdx = 1, rightIdx = 3
					// we know that the number of elements in range [1, 5] = 3 - 1 + 1
					int leftIdx = Collections.binarySearch(positions, left);
					int rightIdx = Collections.binarySearch(positions, right);
					leftIdx = leftIdx < 0 ? -(leftIdx + 1) : leftIdx;
					rightIdx = rightIdx < 0 ? -(rightIdx + 1) : rightIdx + 1;
					
					// if the number of elements in range > threshold, we return the answer
					if (rightIdx - leftIdx >= threshold) {
						return key;
					}
				}
			} else {
				// for short array, we count the number of elements in the array, one-by-one
				Map<Integer, Integer> map = new HashMap<>();
				for (int i = left; i <= right; i++) {
					map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
					if (map.get(arr[i]) >= threshold) {
						return arr[i];
					}
				}
			}
			return -1;
		}
	}
}
