package algorithms.sort;

import java.util.Arrays;

/**
 * @author Yangxiao on 3/23/2019.
 */
public class Merge_Sorted_Array {
	
	
	public static void main(String[] args) {
		Merge_Sorted_Array ms = new Merge_Sorted_Array();
		int[] nums1 = {1, 2, 3, 0, 0, 0};
		int m = 3;
		int[] nums2 = {2, 5, 6};
		int n = 3;
		ms.merge(nums1, nums2, m, n);
		System.out.println(Arrays.toString(nums1));
	}
	
	
	public void merge(int[] nums1, int[] nums2, int m, int n) {
		int i = m - 1;
		int j = n - 1;
		while (i > -1 && j > -1) {
			if (nums1[i] < nums2[j]) {
				nums1[i + j + 1] = nums2[j];
				j--;
			} else {
				nums1[i + j + 1] = nums1[i];
				i--;
			}
		}
		while (j > -1) {
			nums1[i + j + 1] = nums2[j];
			j--;
		}
	}
}
