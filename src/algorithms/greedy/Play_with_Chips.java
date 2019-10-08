package algorithms.greedy;

/**
 * @author Yangxiao on 10/7/2019.
 */
public class Play_with_Chips {
	class Solution {
		public int minCostToMoveChips(int[] chips) {
			int odd = 0, even = 0;
			for (int i = 0; i < chips.length; i++) {
				if (chips[i] % 2 == 0) {
					even++;
				} else {
					odd++;
				}
			}
			
			return Math.min(odd, even);
		}
	}
}
