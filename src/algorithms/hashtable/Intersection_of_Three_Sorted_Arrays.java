package algorithms.hashtable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Yangxiao on 10/5/2019.
 */
public class Intersection_of_Three_Sorted_Arrays {
	
	public static void main(String[] args) {
		Intersection_of_Three_Sorted_Arrays cs = new Intersection_of_Three_Sorted_Arrays();
		int[] a1 = {1, 2, 3, 4, 5};
		int[] a2 = {1, 2, 5, 7, 9};
		int[] a3 = {1, 3, 4, 5, 8};
		
		cs.arraysIntersection(a1, a2, a3);
	}
	
	public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
		int n = arr1.length, a = 0, b = 0, c = 0;
		List<Integer> list = new ArrayList<>();
		while (a < n && b < n && c < n) {
			int x1 = arr1[a];
			int x2 = arr2[b];
			int x3 = arr3[c];
			if (x1 == x2 && x2 == x3) {
				a++;
				b++;
				c++;
				list.add(x1);
			} else {
				int m = Math.min(Math.min(x1, x2), x3);
				if (m == x1) {
					a++;
				}
				if (m == x2) {
					b++;
				}
				if (m == x3) {
					c++;
				}
			}
		}
		
		return list;
	}
	
	public List<Integer> arraysIntersection2(int[] arr1, int[] arr2, int[] arr3) {
		int indexes[] = new int[]{0, 0, 0};
		int[][] arrs = new int[][]{arr1, arr2, arr3};
		List<Integer> res = new ArrayList<>();
		while (IntStream.range(0, 3).allMatch(i -> indexes[i] < arrs[i].length)) {
			Integer min = IntStream.range(0, 3).map(i -> arrs[i][indexes[i]]).min().getAsInt();
			if (IntStream.range(0, 3).allMatch(i -> min == arrs[i][indexes[i]])) {
				res.add(arrs[0][indexes[0]]);
			}
			IntStream.range(0, 3).filter(i -> min == arrs[i][indexes[i]]).forEach(i -> indexes[i] += 1);
		}
		return res;
	}
}
