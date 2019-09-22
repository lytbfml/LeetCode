package algorithms.sliding_window;

/**
 * @author Yangxiao on 8/31/2019.
 *
 * wc152
 */
public class Diet_Plan_Performance {
	
	public static void main(String[] args) {
		Diet_Plan_Performance cs = new Diet_Plan_Performance();
		int rr = cs.dietPlanPerformance(new int[]{1, 2, 3, 4, 5}, 1, 3, 3);
		System.out.println(rr);
		rr = cs.dietPlanPerformance(new int[]{3, 2}, 2, 0, 1);
		System.out.println(rr);
		rr = cs.dietPlanPerformance(new int[]{6, 5, 0, 0}, 2, 1, 5);
		System.out.println(rr);
		rr = cs.dietPlanPerformance(new int[]{6, 13, 8, 7, 10, 1, 12, 11}, 6, 5, 37);
		System.out.println(rr);
	}
	
	public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
		int n = calories.length;
		int res = 0;
		for (int i = 0; i + k <= n; i++) {
			int cnt = 0;
			for (int j = i; j < i + k; j++) {
				cnt += calories[j];
			}
			if (cnt < lower)
				res -= 1;
			if (cnt > upper)
				res += 1;
		}
		
		return res;
	}
	
	
	public int dietPlanPerformance_SlidingWindow(int[] calories, int k, int lower, int upper) {
		int point = 0;
		for (int i = -1, j = 0, win = 0; j < calories.length; ++j) {
			win += calories[j];
			if (j - i > k) {
				win -= calories[++i];
			}
			if (j - i < k) { continue; } // not a k sequence yet.
			if (win < lower) {
				--point;
			} else if (win > upper) {
				++point;
			}
		}
		return point;
	}
}
