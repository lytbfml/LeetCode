package algorithms.stack;

import java.util.Stack;

/**
 * @author Yangxiao Wang on 2019-07-09
 */
public class Trapping_Rain_Water {
	
	public static void main(String[] args) {
		Trapping_Rain_Water cs = new Trapping_Rain_Water();
		System.out.println(cs.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
		System.out.println(cs.trap(new int[]{19, 2, 4, 19}));
		System.out.println(cs.trap(new int[]{0, 2, 0}));
		System.out.println(cs.trap(new int[]{5, 4, 1, 2}));
	}
	
	public int trap(int[] h) {
		if (h == null || h.length < 3) {
			return 0;
		}
		int n = h.length;
		int res = 0;
		int l = 0, r = 2;
		int[] interTmp = new int[n];
		interTmp[0] = h[0];
		for (int i = 1; i < n; i++)
			interTmp[i] = h[i] + interTmp[i - 1];
		while (r < n) {
			while (l < n - 1 && h[l + 1] > h[l]) l++;
			if (l >= n - 2) return res;
			r = l + 2;
			int max = r;
			for (int i = r; i < n; i++) {
				if (h[i] > h[l]) {
					max = i;
					break;
				}
				if (h[i] >= h[max]) max = i;
			}
			
			while (h[l + 1] > h[max] && l + 1 <= max - 2) {
				l++;
			}
			
			int water = calWater(h, l, max, interTmp[max - 1] - interTmp[l]);
			if (water > 0) res += water;
			l = max;
			r = l + 2;
		}
		return res;
	}
	
	private int calWater(int[] a, int left, int right, int inter) {
		return (right - left - 1) * (a[left] > a[right] ? a[right] : a[left]) - inter;
	}
	
	class Solution_twoPointers {
		public int trap(int[] height) {
			if (height.length < 3) return 0;
			int left = 0, right = height.length - 1;
			int res = 0;
			int leftH = height[left++], rightH = height[right--];
			while (left <= right) {
				//System.out.println(Structures.String.format("left:%d, right:%d", left, right));
				if (leftH <= rightH) {
					while (height[left] < leftH) {
						res += leftH - height[left];
						left++;
					}
					leftH = height[left];
					left++;
				} else {
					while (height[right] < rightH) {
						res += rightH - height[right];
						right--;
					}
					rightH = height[right];
					right--;
				}
			}
			return res;
		}
		
		public int trap_2(int[] A) {
			int a = 0;
			int b = A.length - 1;
			int max = 0;
			int leftmax = 0;
			int rightmax = 0;
			while (a <= b) {
				leftmax = Math.max(leftmax, A[a]);
				rightmax = Math.max(rightmax, A[b]);
				if (leftmax < rightmax) {
					// leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
					max += (leftmax - A[a]);
					a++;
				} else {
					max += (rightmax - A[b]);
					b--;
				}
			}
			return max;
		}
	}
	
	public int trap_stack(int[] h) {
		int n = h.length;
		int res = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && h[stack.peek()] < h[i]) {
				int peek = stack.pop();
				if (stack.isEmpty()) break;
				int distance = i - stack.peek() - 1;
				int bounded_height = Math.min(h[i], h[stack.peek()]) - h[peek];
				res += distance * bounded_height;
			}
			stack.push(h[i]);
		}
		
		return res;
	}
}
