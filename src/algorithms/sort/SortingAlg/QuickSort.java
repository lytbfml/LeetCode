package algorithms.sort.SortingAlg;

import java.util.Arrays;

public class QuickSort {
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 8, 5, 3, 2};
		qSort1(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void quickSort(int[] arr) {
		quickSortRec(arr, 0, arr.length - 1);
	}
	
	private static void quickSortRec(int[] arr, int first, int last) {
		if (first >= last) { return; }
		int p = partition(arr, first, last);
		quickSortRec(arr, first, p - 1);
		quickSortRec(arr, p + 1, last);
	}
	
	static int partition(int[] arr, int first, int last) {
		// Use the last element as the pivot.
		int pivot = arr[last];
		int i = first - 1;
		for (int j = first; j < last - 1; j++) {
			if (arr[j] <= pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp; //swap arr[i] and arr[j]
			}
		}
		// Now put pivot in position i+1.
		int temp = arr[i + 1];
		arr[i + 1] = arr[last];
		arr[last] = temp;
		return i + 1;
	}
	
	
	public static void qSort1(int[] arr) {
		quickSortRec1(arr, 0, arr.length - 1);
	}
	
	private static void quickSortRec1(int[] arr, int first, int last) {
		if (first < last) {
			int p = partition_FirstPivot(arr, first, last);
			quickSortRec1(arr, first, p - 1);
			quickSortRec1(arr, p + 1, last);
		}
	}
	
	private static int partition_LastPivot(int[] arr, int first, int last) {
		int pivot = arr[last];
		int ind = first;
		for (int i = first; i < last; i++) {
			if (arr[i] <= pivot) {
				int temp = arr[ind];
				arr[ind] = arr[i];
				arr[i] = temp;
				ind++;
			}
		}
		int temp = arr[ind];
		arr[ind] = pivot;
		arr[last] = temp;
		
		return ind;
	}
	
	private static int partition_FirstPivot(int[] arr, int first, int last) {
		int pivot = arr[first];
		int ind = first + 1;
		for (int i = first + 1; i <= last; i++) {
			if (arr[i] <= pivot) {
				int temp = arr[ind];
				arr[ind] = arr[i];
				arr[i] = temp;
				ind++;
			}
		}
		int temp = arr[ind - 1];
		arr[ind - 1] = pivot;
		arr[first] = temp;
		
		return ind-1;
	}
	
}
