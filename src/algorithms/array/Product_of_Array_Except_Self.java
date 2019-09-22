package algorithms.array;

/**
 * @author Yangxiao on 10/31/2018.
 */

/**
 * Given an array nums of n integers where n > 1
 * return an array output such that output[i] is
 * equal to the product of all the elements of nums except nums[i].
 */
class Product_of_Array_Except_Self {
	
	public int[] productExceptSelf(int[] nums) {
		int size = nums.length;
		int[] arr = new int[size];
		int[] left = new int[size];
		int[] right = new int[size];
		
		left[0] = 1;
		right[size - 1] = 1;
		
		for (int i = 1; i < size; i++) {
			left[i] = left[i - 1] * nums[i - 1];
		}
		
		for (int i = size - 2; i >= 0; i--) {
			right[i] = right[i + 1] * nums[i + 1];
		}
		
		for (int i = 0; i < size; i++) {
			arr[i] = left[i] * right[i];
		}
		
		return arr;
	}
	
	public int[] productExceptSelf_Sol(int[] nums) {
		int n = nums.length;
		int[] result = new int[n];
		result[0] = nums[0];
		for (int i = 1; i < n - 1; ++i) {
			result[i] = nums[i] * result[i-1];
		}
		// 1, 1*2, 1*2*3;
		int reverseProd = 1;
		for (int i = n - 1; i > 0; --i) {
			result[i] = result[i-1] * reverseProd;
			reverseProd *= nums[i];
		}
		result[0] = reverseProd;
		return result;
	}
}
