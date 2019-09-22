package design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Leetcode 384
 *
 * @author Yangxiao on 3/24/2019.
 */
public class Shuffle_an_Array {
	public static void main(String[] args) {
		Random rand = new Random();
		final int[] arr = rand.ints(0, 20).distinct().limit(20).toArray();
		System.out.println(Arrays.toString(arr));
	}
	
	class Solution {
		
		private int[] nums;
		private int[] randArr;
		private Random rand;
		private int n;
		
		public Solution(int[] nums) {
			this.n = nums.length;
			this.nums = Arrays.copyOf(nums, this.n);
			this.randArr = nums;
			this.rand = new Random();
		}
		
		/** Resets the array to its original configuration and return it. */
		public int[] reset() {
			return nums;
		}
		
		
		/** Returns a random shuffling of the array. */
		public int[] shuffle() {
			int[] res = new int[n];
			final int[] arr = rand.ints(0, 20).distinct().limit(20).toArray();
			for (int i = 0; i < n; i++) {
				res[arr[i]] = randArr[i];
			}
			randArr = res;
			return res;
		}
		
		public int[] shuffle2() {
			if (n == 0) {
				return null;
			}
			int[] res = new int[n];
			ArrayList<Integer> inds = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				inds.add(i);
			}
			for (int i = 0; i < n; i++) {
				int ind = inds.get(rand.nextInt(n - i));
				res[ind] = nums[i];
				inds.remove(ind);
			}
			return res;
		}
		
		
		public int[] shuffle_Sol() {
			for (int i = n - 1; i > -1; i--) {
				int randN = rand.nextInt(i+1);
				int temp = randArr[i];
				randArr[i] = randArr[randN];
				randArr[randN] = temp;
			}
			return randArr;
		}
	}
}


