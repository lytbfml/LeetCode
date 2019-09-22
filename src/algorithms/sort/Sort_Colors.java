package algorithms.sort;

import java.util.Arrays;

/**
 * @author Yangxiao on 3/27/2019.
 */
public class Sort_Colors {
	
	public static void main(String[] args) {
		Sort_Colors cs = new Sort_Colors();
		int[] a =
				{2, 0, 2, 1, 1, 0, 2, 0, 2, 1, 1, 0, 2, 0, 2, 1, 1, 0, 2, 0, 2, 1, 1, 0, 2, 0, 2, 1,
				 1, 0, 2, 0, 2, 1, 1, 0};
		cs.sortColors(a);
		System.out.println(Arrays.toString(a));
	}
	
	static void sortColors_Sol(int[] nums) {
		if (nums.length == 0) {
			return;
		}
		int start = 0;
		int end = nums.length - 1;
		
		for (int i = 0; i < nums.length && start < end; ) {
			if (nums[i] == 0 && i > start) {
				swap(nums, i, start++);
			} else if (nums[i] == 2 && i < end) {
				swap(nums, i, end--);
			} else {
				i++;
			}
		}
	}
	
	private static void swap(int[] nums, int left, int right) {
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	}
	
	public void sortColors(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int n = nums.length;
		int place2 = n - 1;
		int place0 = 0;
		for (int i = 0; i <= place2; i++) {
			if (nums[i] == 2) {
				int temp = nums[place2];
				nums[place2] = 2;
				nums[i] = temp;
				place2--;
				if (temp == 0 || temp == 2) {
					i--;
				}
			} else if (nums[i] == 0) {
				int temp = nums[place0];
				nums[place0] = 0;
				nums[i] = temp;
				place0++;
			}
		}
	}
	
	public void sortColors_optimize(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int n = nums.length;
		int place2 = n - 1;
		int place0 = 0;
		for (int i = 0; i <= place2; ) {
			if (nums[i] == 2 && i < place2) {
				int temp = nums[place2];
				nums[place2] = 2;
				nums[i] = temp;
				place2--;
			} else if (nums[i] == 0 && i > place0) {
				int temp = nums[place0];
				nums[place0] = 0;
				nums[i] = temp;
				place0++;
			} else {
				i++;
			}
		}
	}
	
	// two pass O(m+n) space
	void sortColors_Sol1(int A[], int n) {
		int num0 = 0, num1 = 0, num2 = 0;
		
		for (int i = 0; i < n; i++) {
			if (A[i] == 0) {
				++num0;
			} else if (A[i] == 1) {
				++num1;
			} else if (A[i] == 2) {
				++num2;
			}
		}
		
		for (int i = 0; i < num0; ++i) { A[i] = 0; }
		for (int i = 0; i < num1; ++i) { A[num0 + i] = 1; }
		for (int i = 0; i < num2; ++i) { A[num0 + num1 + i] = 2; }
	}
	
	// one pass in place solution
	void sortColors_Sol2(int A[], int n) {
		int n0 = -1, n1 = -1, n2 = -1;
		for (int i = 0; i < n; ++i) {
			if (A[i] == 0) {
				A[++n2] = 2;
				A[++n1] = 1;
				A[++n0] = 0;
			} else if (A[i] == 1) {
				A[++n2] = 2;
				A[++n1] = 1;
			} else if (A[i] == 2) {
				A[++n2] = 2;
			}
		}
	}
	
	// one pass in place solution
	void sortColors_So3(int A[], int n) {
		int j = 0, k = n - 1;
		for (int i = 0; i <= k; ++i) {
			if (A[i] == 0 && i != j) { swap(A, A[i--], A[j++]); } else if (A[i] == 2 && i != k) {
				swap(A, A[i--], A[k--]);
			}
		}
	}
	
	// one pass in place solution
	void sortColors_Sol4(int A[], int n) {
		int j = 0, k = n - 1;
		for (int i = 0; i <= k; i++) {
			if (A[i] == 0) { swap(A, i, j++); } else if (A[i] == 2) { swap(A, i--, k--); }
		}
	}
}
