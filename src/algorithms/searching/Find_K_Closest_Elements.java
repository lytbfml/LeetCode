package algorithms.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Yangxiao on 10/24/2018.
 * <p>
 * Binary search
 */

class Find_K_Closest_Elements {
	
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		List<Integer> intArr = new ArrayList<>();
		ArrayList<disInt> disInts = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			disInts.add(new disInt(arr[i], Math.abs(arr[i] - x)));
		}
		
		Collections.sort(disInts, (o1, o2) -> {
			if (o1.dis == o2.dis) {
				return o1.val - o2.val;
			}
			
			return o1.dis - o2.dis;
		});
		
		for (int i = 0; i < k; i++) {
			intArr.add(disInts.get(i).val);
		}
		
		Collections.sort(intArr);
		
		return intArr;
	}
	
	public class disInt {
		int val;
		int dis;
		
		public disInt(int val, int dis) {
			this.val = val;
			this.dis = dis;
		}
	}
	
	/**
	 * Using Collection.sort( ) [Accepted]
	 */
	public List<Integer> findClosestElements_Sol(List<Integer> arr, int k, int x) {
		Collections.sort(arr, (a, b) -> a == b ? a - b : Math.abs(a - x) - Math.abs(b - x));
		arr = arr.subList(0, k);
		Collections.sort(arr);
		return arr;
	}
	
	/**
	 * Using Binary Search and Two Pointers [Accepted]
	 */
	public List<Integer> findClosestElements_Sol2(List<Integer> arr, int k, int x) {
		int n = arr.size();
		if (x <= arr.get(0)) {
			return arr.subList(0, k);
		} else if (arr.get(n - 1) <= x) {
			return arr.subList(n - k, n);
		} else {
			int index = Collections.binarySearch(arr, x);
			if (index < 0)
				index = -index - 1;
			int low = Math.max(0, index - k - 1), high = Math.min(arr.size() - 1, index + k - 1);
			
			while (high - low > k - 1) {
				if (low < 0 || (x - arr.get(low)) <= (arr.get(high) - x))
					high--;
				else if (high > arr.size() - 1 || (x - arr.get(low)) > (arr.get(high) - x))
					low++;
				else
					System.out.println("unhandled case: " + low + " " + high);
			}
			return arr.subList(low, high + 1);
		}
	}
	
	public List<Integer> findClosestElements_Sol3(int[] arr, int k, int x) {
		if (arr == null || arr.length == 0) return new ArrayList<>();
		int lo = 0, hi = arr.length - k;
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (x - arr[mid] > arr[mid + k] - x) lo = mid + 1;
			else hi = mid;
		}
		Integer[] res = new Integer[k];
		for (int i = 0; i < k; i++) res[i] = arr[i + lo];
		return Arrays.asList(res);
	}
}
