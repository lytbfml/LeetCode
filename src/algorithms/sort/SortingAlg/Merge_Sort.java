package algorithms.sort.SortingAlg;

/**
 * @author Yangxiao on 10/25/2018.
 */

class Merge_Sort {
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 8, 5, 3, 2};
		Merge_Sort cs = new Merge_Sort();
		cs.sort(arr, 0, arr.length - 1);
	}
	
	public void sort1(int[] num, int l, int r) {
		if (l < r) {
			int mid = (r - l) / 2 + l;
			sort1(num, l, mid);
			sort1(num, mid + 1, r);
			merge1(num, l, r, mid);
		}
	}
	
	public void merge1(int[] num, int l, int r, int m) {
		int L = m - l + 1;
		int R = r - m;
		int[] left = new int[L];
		int[] right = new int[R];
		for (int i = 0; i < L; i++) {
			left[i] = num[i + l];
		}
		for (int i = 0; i < R; i++) {
			right[i] = num[i + m + 1];
		}
		int i = 0, j = 0, ind = 0;
		while (i < L && j < R) {
			if (left[i] < right[j]) {
				num[l + ind] = left[i];
				i++;
			} else {
				num[l + ind] = right[j];
				j++;
			}
			ind++;
		}
		
		while (i < L) {
			num[l + ind++] = left[i++];
		}
		
		while (j < R) {
			num[l + ind++] = right[j++];
		}
	}
	
	
	/**
	 * Merges two subarrays of arr[].
	 * First subarray is arr[l..m]
	 * Second subarray is arr[m+1..r]
	 */
	public void merge(int[] num, int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;
		
		int L[] = new int[n1];
		int R[] = new int[n2];
		
		for (int i = 0; i < n1; i++) {
			L[i] = num[l + i];
		}
		for (int i = 0; i < n2; i++) {
			R[i] = num[m + 1 + i];
		}
		
		int k = l;
		int i = 0, j = 0;
		while (i < n1 && j < n2) {
			if (L[i] < R[j]) {
				num[k] = L[i];
				i++;
			} else {
				num[k] = R[j];
				j++;
			}
			k++;
		}
		
		while (i < n1) {
			num[k] = L[i];
			k++;
			i++;
		}
		
		while (j < n2) {
			num[k] = R[j];
			k++;
			j++;
		}
	}
	
	
	public void sort(int[] num, int l, int r) {
		if (l < r) {
			int m = (r + l) / 2;
			
			sort(num, l, m);
			sort(num, m + 1, r);
			
			merge(num, l, m, r);
		}
	}
}
