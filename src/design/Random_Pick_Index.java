package design;

import java.util.Random;

/**
 * @author Yangxiao Wang on 2019-07-03
 */
public class Random_Pick_Index {
	
	int[] nums;
	Random rand;
	int n;
	
	public Random_Pick_Index(int[] nums) {
		this.nums = nums;
		this.n = nums.length;
		rand = new Random();
	}
	
	public int pick(int target) {
		int cnt = 0;
		int res = -1;
		for (int i = 0; i < n; i++) {
			if (nums[i] != target) continue;
			res = rand.nextInt(++cnt) == 0 ? i : res;
		}
		return res;
	}
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj = new Solution(nums); int param_1 =
 * obj.pick(target);
 */