package algorithms.array;

/**
 * @author Yangxiao Wang on 2019-08-03
 */
public class Decrease_Elements_To_Make_Array_Zigzag {
	
	public int movesToMakeZigzag(int[] nums) {
		int n = nums.length;
		if (n == 1) return 0;
		int moveL = 0, moveR = 0;
		
		for (int i = 1; i < n; i += 2) {
			int lef = 0, ri = 0;
			if (nums[i] >= nums[i - 1]) {
				lef = 1 + nums[i] - nums[i - 1];
			}
			if (i < n - 1 && nums[i] >= nums[i + 1]) {
				ri = 1 + nums[i] - nums[i + 1];
			}
			moveL += Math.max(lef, ri);
		}
		
		for (int i = 0; i < n; i += 2) {
			int lef = 0, ri = 0;
			if (i != 0 && nums[i] >= nums[i - 1]) {
				lef = 1 + nums[i] - nums[i - 1];
			}
			if (i < n - 1 && nums[i] >= nums[i + 1]) {
				ri = 1 + nums[i] - nums[i + 1];
			}
			moveR += Math.max(lef, ri);
		}
		
		return Math.min(moveL, moveR);
	}
	
	
	public int movesToMakeZigzag_good(int[] A) {
		int res[] = new int[2], n = A.length, left, right;
		for (int i = 0; i < n; ++i) {
			left = i > 0 ? A[i - 1] : 1001;
			right = i + 1 < n ? A[i + 1] : 1001;
			res[i % 2] += Math.max(0, A[i] - Math.min(left, right) + 1);
		}
		return Math.min(res[0], res[1]);
	}
}
