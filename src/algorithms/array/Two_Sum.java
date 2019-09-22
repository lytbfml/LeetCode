package algorithms.array; /**
 * @author Yangxiao on 10/24/2018.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
class Two_Sum {
	
	public int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[]{i, j};
				}
			}
		}
		throw new IllegalArgumentException();
	}
	
	// Sorted array
	public int[] twoSum_(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		
		while (l < r) {
			int res = nums[l] + nums[r];
			if (res < target) {
				l++;
			} else if (res > target) {
				r--;
			} else {
				return new int[]{l, r};
			}
		}
		throw new IllegalArgumentException();
	}
	
	
	public int[] twoSum_Sol1(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (map.containsKey(complement)) {
				return new int[]{map.get(complement), i};
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}
	
	public int[] twoSum_Sol2(int[] nums, int target) {
		int t = 4096;
		int bitMode = t - 1;
		int[] temp = new int[t];
		int length = nums.length;
		int firstValue = nums[0];
		
		for (int i = 1; i < length; i++) {
			int different = target - nums[i];
			int current = nums[i];
			if (different == firstValue) {
				return new int[]{0, i};
			}
			int differentIndex = temp[different & bitMode];
			if (differentIndex != 0) {
				return new int[]{differentIndex, i};
			}
			
			int currentIndex = current & bitMode;
			temp[currentIndex] = i;
		}
		return null;
	}
	
	
	public int[] twoSum_Sol3(int[] nums, int target) {
		int[] numsCopy = Arrays.copyOf(nums, nums.length);
		Arrays.sort(numsCopy);
		for (int i = 0; i < numsCopy.length; i++) {
			int firstElement = numsCopy[i];
			int desiredElement = target - firstElement;
			
			int indexOfSecond = Arrays.binarySearch(numsCopy, desiredElement);
			if (indexOfSecond > -1) {
				if (indexOfSecond == i) {
					if (!((indexOfSecond != 0 && numsCopy[indexOfSecond - 1] == desiredElement) ||
							(indexOfSecond != numsCopy.length - 1 && numsCopy[indexOfSecond + 1] == desiredElement))) {
						continue;
					}
				}
				int indexOfFirst = getIndex(nums, firstElement, -1);
				return new int[] {indexOfFirst, getIndex(nums, desiredElement, indexOfFirst)};
			}
		}
		throw new IllegalArgumentException();
	}
	
	private static int getIndex(int[] array, int element, int exceptIndex) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element && i != exceptIndex) {
				return i;
			}
		}
		throw new IllegalArgumentException();
	}
}
