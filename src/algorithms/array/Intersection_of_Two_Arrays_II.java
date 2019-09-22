package algorithms.array;

/**
 * @author Yangxiao on 10/24/2018.
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given two arrays, write a function to compute their intersection.
 */
class Intersection_of_Two_Arrays_II {
	
	public int[] intersect(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int len1 = nums1.length;
		int len2 = nums2.length;
		
		ArrayList<Integer> arr = new ArrayList<>();
		
		int i = 0, j = 0;
		while (i < len1 && j < len2) {
			if (nums1[i] < nums2[j]) {
				i++;
			} else if (nums1[i] > nums2[j]) {
				j++;
			} else {
				arr.add(nums1[i]);
				j++;
				i++;
			}
		}
		
		int[] intArray = new int[arr.size()];
		for (int k = 0; k < intArray.length; k++) {
			intArray[k] = arr.get(k);
		}
		
		return intArray;
	}
	
	/**
	 * No extra space
	 */
	public int[] intersect_Sol(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i=0,j= 0,index=0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] == nums2[j]) {
				nums1[index++] = nums2[j];
				i++;
				j++;
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else j++;
		}
		
		return Arrays.copyOf(nums1, index);
	}
}
