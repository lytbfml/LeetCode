package algorithms.sort.SortingAlg;

/**
 * @author Yangxiao on 10/31/2018.
 */

class BubbleSort {
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 8, 5, 3, 2};
		BubbleSort.bubbleSortPract1(arr);
	}
	
	
	
	public static void bubbleSortOpt1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		int n = arr.length;
		boolean changed;
		for (int i = 0; i < n - 1; i++) {
			changed = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					changed = true;
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			if (!changed) {
				break;
			}
		}
	}
	
	
	public static void bubbleSortPract1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
	
	
	void bubbleSort_opmtimized(int[] arr) {
		int n = arr.length;
		boolean swapped;
		for (int i = 0; i < n - 1; i++) {
			swapped = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
	}
}
