package algorithms.sort.SortingAlg;

import java.util.Arrays;

public class MergeSort {
	
	/**
	 * both v1 an v2 are sorted in non-descending order
	 *
	 * @param v1
	 * @param v2
	 * @return a merge array in sorted order
	 */
	public static int[] merge(int[] v1, int[] v2) {
		int[] result = new int[v1.length + v2.length];
		int j = 0, k = 0;
		for (int i = 0; i < result.length; ++i) {
			if (k == v2.length || j < v1.length && v1[j] <= v2[k])
				result[i] = v1[j++];
			else
				result[i] = v2[k++];
		}
		return result;
	}
	
	
	public static void mergeSort(int[] v) {
		System.out.println(Arrays.toString(v));
		// base case
		if (v.length == 1)
			return;
		
		// step 1: divide the array into two halves
		System.out.println(Arrays.toString(v));
		int mid = v.length / 2;
		int[] firstHalf = Arrays.copyOfRange(v, 0, mid);
		int[] secondHalf = Arrays.copyOfRange(v, mid, v.length);
		// step 2: sort the two smaller arrays
		mergeSort(firstHalf);
		mergeSort(secondHalf);
		
		// step 3: merge the two sorted arrays
		int[] sorted = merge(firstHalf, secondHalf);
		System.out.println(Arrays.toString(sorted));
		// step 4: copy the sorted array back to v
		for (int i = 0; i < v.length; ++i) {
			v[i] = sorted[i];
		}
		System.out.println(Arrays.toString(v));
	}
	
	public static void main(String[] args) {
		int v[] = {1, 3, 40, 5, 10, 2, 10, 60, 7, 16};
		System.out.println(Arrays.toString(v));
		mergeSort(v);
		System.out.println(Arrays.toString(v));
	}
}
