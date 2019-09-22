package algorithms.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Yangxiao on 9/12/2019.
 */
public class Next_Greater_Element_I {
	
	public int[] nextGreaterElement_map(int[] nums1, int[] nums2) {
		int[] re = new int[nums1.length];
		Map<Integer, Integer> m = new HashMap<>();
		
		for(int i = 0;i < nums2.length;i++) {
			m.put(nums2[i], i);
		}
		
		for(int i = 0;i < nums1.length;i++) {
			re[i] = -1;
			for(int j = m.get(nums1[i]) + 1;j < nums2.length;j++) {
				if(nums2[j] > nums1[i]) {
					re[i] = nums2[j];
					break;
				}
			}
		}
		
		return re;
	}
	
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		int n = nums2.length, m = nums1.length;
		Map<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && stack.peek() < nums2[i]) {
				map.put(stack.pop(), nums2[i]);
			}
			stack.push(nums2[i]);
		}
		
		for (int i = 0; i < m; i++) {
			nums1[i] = map.getOrDefault(nums1[i], -1);
		}
		
		return nums1;
	}
	
	
}
