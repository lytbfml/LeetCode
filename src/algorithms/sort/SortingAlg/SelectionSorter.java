package algorithms.sort.SortingAlg;

import java.util.Arrays;


public class SelectionSorter {
	public static void main(String[] args) {
		// try it out...
		int[] arr = {5, 3, 4, 7, 6, 2, 4, 3, 1};
		System.out.println(Arrays.toString(arr));
		
		selectionSort(arr);
		// alternatively, use Arrays.sort
		//Arrays.sort(arr);
		System.
				out.println(Arrays.toString(arr));
		
		String[] arr2 = {"bear", "aardvark", "donkey", "chimpanzee", "antelope"};
		System.out.println(Arrays.toString(arr2));
		selectionSortForStrings(arr2);
		System.out.println(Arrays.toString(arr2));
	}
	
	
	/**
	 * Selection sort for integer arrays, as normally written in textbooks
	 * using nested loops.
	 */
	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; ++i) {
			// find index of minimal element to the right of i
			int minIndex = i;
			for (int j = i + 1; j < arr.length; ++j) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			
			// swap into position i
			swap(arr, i, minIndex);
		}
	}
	
	public static void selectionSortForStrings(String[] arr) {
		for (int i = 0; i < arr.length - 1; ++i) {
			// find index of minimal element to the right of i
			int minIndex = i;
			for (int j = i + 1; j < arr.length; ++j) {
				//if (arr[j] < arr[minIndex])
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			
			// swap into position i
			String temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}
	
	
	/**
	 * Exchanges two elements of an int array indicated by
	 * the given indices.
	 *
	 * @param arr
	 * @param i
	 * @param j
	 */
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}