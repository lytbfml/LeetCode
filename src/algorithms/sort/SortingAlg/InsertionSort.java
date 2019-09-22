package algorithms.sort.SortingAlg;

import java.util.Arrays;

public class InsertionSort {
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 8, 5, 3, 2};
		InsertionSort.InsertionSort1(arr);
	}
	
	
	public static void InsertionSort1(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			int j = i;
			int temp = arr[j];
			while (j > 0 && temp < arr[j - 1]) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = temp;
		}
		System.out.println(Arrays.toString(arr));
	}
	
	
	
	public static void InsertionSort(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n - 1; i++) {
			int temp = arr[i];
			int j = i - 1;
			
			while (temp < arr[j] && j > -1) {
				arr[j + 1] = arr[j];
				--j;
			}
			arr[j + 1] = temp;
		}
	}
}
