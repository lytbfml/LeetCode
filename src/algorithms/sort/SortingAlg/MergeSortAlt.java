package algorithms.sort.SortingAlg;

public class MergeSortAlt {
	
	/**
	 * Performs a recursive merge sort on the given array.  This version
	 * does less copying than our original MergeSort, but still requires
	 * 100% additional memory.
	 *
	 * @param arr
	 */
	public static void mergeSort(int[] arr) {
		// Base case
		if (arr.length <= 1) {
			return;
		}
		
		// Split into two new arrays
		int mid = arr.length / 2;
		int secondLength = arr.length - mid;
		int[] first = new int[mid];
		for (int i = 0; i < mid; ++i) {
			first[i] = arr[i];
		}
		int[] second = new int[secondLength];
		for (int i = 0; i < secondLength; ++i) {
			second[i] = arr[mid + i];
		}
		
		// Recursively sort each half and merge back together
		mergeSort(first);
		mergeSort(second);
		merge(first, second, arr);
	}
	
	/**
	 * Merges two sorted arrays into a third one.
	 * The given arrays 'first' and 'second' must already be sorted, the
	 * array 'result' must already exist and must be the correct size,
	 * i.e., result.length = first.length + second.length.
	 *
	 * @param a
	 * @param b
	 * @param result
	 */
	static void merge(int[] a, int[] b, int[] result) {
		// This code is identical to MergeSort.merge, except we don't have to
		// construct or return the 'result' array
		
		int i = 0;                  // starting index in a
		int j = 0;                  // starting index in b
		final int iMax = a.length;  // max index in a
		final int jMax = b.length;  // max index in b
		int k = 0;                  // index in result
		
		while (i < iMax && j < jMax) {
			if (a[i] <= b[j]) {
				result[k] = a[i];
				i = i + 1;
				k = k + 1;
			} else {
				result[k] = b[j];
				j = j + 1;
				k = k + 1;
			}
		}
		
		// pick up any stragglers
		while (i < iMax) {
			result[k] = a[i];
			i = i + 1;
			k = k + 1;
		}
		while (j < jMax) {
			result[k] = b[j];
			j = j + 1;
			k = k + 1;
		}
	}
}