package algorithms.sort;

import java.util.*;

/**
 * @author Yangxiao on 3/27/2019.
 */
public class Kth_Largest_Element_in_an_Array {
	
	public static void main(String[] args) {
		Kth_Largest_Element_in_an_Array cs = new Kth_Largest_Element_in_an_Array();
		int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
		System.out.println(cs.findKthLargest(nums, 4));
		int[] nums2 = {3, 1, 2, 4};
		System.out.println(cs.findKthLargest(nums2, 2));
		int[] nums3 = {3, 2, 1, 5, 6, 4};
		System.out.println(cs.findKthLargest(nums3, 2));
	}
	
	private static int partition_LastPivot(int[] arr, int first, int last, int k) {
		if (first == last) {
			return arr[first];
		}
		// much more faster than last
		int mid = (last - first) / 2 + first;
		int pivot = arr[mid];
		int move = arr[last];
		arr[last] = pivot;
		arr[mid] = move;
		
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
		
		if (ind == k) {
			return arr[ind];
		} else if (ind > k) {
			return partition_LastPivot(arr, first, ind - 1, k);
		} else {
			return partition_LastPivot(arr, ind + 1, last, k);
		}
	}
	
	public int findKthLargest(int[] nums, int k) {
		int n = nums.length;
		return partition_LastPivot(nums, 0, nums.length - 1, n - k);
	}
	
	public int findKthLargest_Heap(int[] nums, int k) {
		// init heap 'the smallest element first'
		PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(n1 -> n1));
		List<Double> lis = new ArrayList<>();
		// keep k largest elements in the heap
		for (int n : nums) {
			heap.add(n);
			if (heap.size() > k) {
				heap.poll();
			}
		}
		
		// output
		return heap.poll();
	}
	
	class Solution {
		int[] nums;
		
		public void swap(int a, int b) {
			int tmp = this.nums[a];
			this.nums[a] = this.nums[b];
			this.nums[b] = tmp;
		}
		
		
		public int partition(int left, int right, int pivot_index) {
			int pivot = this.nums[pivot_index];
			// 1. move pivot to end
			swap(pivot_index, right);
			int store_index = left;
			
			// 2. move all smaller elements to the left
			for (int i = left; i <= right; i++) {
				if (this.nums[i] < pivot) {
					swap(store_index, i);
					store_index++;
				}
			}
			
			// 3. move pivot to its final place
			swap(store_index, right);
			
			return store_index;
		}
		
		public int quickselect(int left, int right, int k_smallest) {
    /*
    Returns the k-th smallest element of list within left..right.
    */
			
			if (left == right) // If the list contains only one element,
			{
				return this.nums[left];  // return that element
			}
			
			// select a random pivot_index
			Random random_num = new Random();
			int pivot_index = left + random_num.nextInt(right - left);
			
			pivot_index = partition(left, right, pivot_index);
			
			// the pivot is on (N - k)th smallest position
			if (k_smallest == pivot_index) {
				return this.nums[k_smallest];
			}
			// go left side
			else if (k_smallest < pivot_index) {
				return quickselect(left, pivot_index - 1, k_smallest);
			}
			// go right side
			return quickselect(pivot_index + 1, right, k_smallest);
		}
		
		public int findKthLargest(int[] nums, int k) {
			this.nums = nums;
			int size = nums.length;
			// kth largest is (N - k)th smallest
			return quickselect(0, size - 1, size - k);
		}
	}
	
}
