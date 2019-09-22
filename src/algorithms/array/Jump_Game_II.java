package algorithms.array;

/**
 * @author Yangxiao on 4/11/2019.
 */
public class Jump_Game_II {
	
	public static void main(String[] args) {
		Jump_Game_II cs = new Jump_Game_II();
		int[] c = {2, 3, 1, 1, 4};
		System.out.println(cs.jump(c));
	}
	
	public int jump(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		int[] dd = new int[n];
		int steps = 0;
		int dest = n - 1;
		for (int k = n - 1; k > 0; ) {
			for (int i = 0; i < k; i++) {
				if (dest - i <= nums[i]) {
					dd[dest] = i;
					System.out.println("this is " + i);
					break;
				}
				System.out.println(i);
			}
			dest = dd[dest];
			k = dest;
			steps++;
		}
		return steps;
	}
	
	public int jump_sol(int[] nums) {
		if (nums == null || nums.length == 0 || nums.length == 1) {
			return 0;
		}
		
		int n = nums.length;
		int steps = 0;
		int dest = 0;
		int next = 0;
		
		for (int i = 0; i < n; i++) {
			next = Math.max(next, i + nums[i]);
			if (i == dest) {
				steps++;
				dest = next;
				if (dest >= nums.length - 1) { return steps; }
			}
		}
		
		return steps;
	}
}
