package algorithms.searching;

/**
 * wc128
 * @author Yangxiao on 9/19/2019.
 */
public class Capacity_To_Ship_Packages_Within_D_Days {
	
	public int shipWithinDays(int[] weights, int D) {
		int n = weights.length;
		int l = 0, r = 0;
		for (int i = 0; i < n; i++) {
			l = Math.max(l, weights[i]);
			r += weights[i];
		}
		
		while (l < r) {
			int mid = (l + r) / 2;
			int days = 1, cur = 0;
			for (int i = 0; i < n; i++) {
				if (cur + weights[i] > mid) {
					cur = 0;
					days++;
				}
				cur += weights[i];
			}
			
			if (days > D) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return l;
	}
	
}
