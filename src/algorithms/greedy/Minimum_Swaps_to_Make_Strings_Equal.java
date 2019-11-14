package algorithms.greedy;

/**
 * @author Yangxiao on 11/2/2019.
 */
public class Minimum_Swaps_to_Make_Strings_Equal {
	
	public int minimumSwap(String s1, String s2) {
		int n = s1.length(), x1 = 0, x2 = 0, xcnt = 0, ycnt = 0;
		for (int i = 0; i < n; i++) {
			if (s1.charAt(i) == 'x' && s2.charAt(i) == 'y') {
				x1++;
				xcnt++;
				ycnt++;
			} else if (s1.charAt(i) == 'y' && s2.charAt(i) == 'x') {
				x2++;
				xcnt++;
				ycnt++;
			} else {
				if (s1.charAt(i) == 'x') {
					xcnt += 2;
				} else {
					ycnt += 2;
				}
			}
		}
		if (xcnt % 2 != 0 || ycnt % 2 != 0) {
			return -1;
		}
		
		return x1 / 2 + x1 % 2 + x2 / 2 + x2 % 2;
	}
	
}
