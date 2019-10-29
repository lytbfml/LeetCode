package algorithms.greedy;

/**
 * @author Yangxiao on 10/12/2019.
 */
public class Split_String_in_Balanced_Strings {
	
	public int balancedStringSplit(String s) {
		int cur = 0, n = s.length(), res = 0;
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == 'L') {
				cur--;
			} else {
				cur++;
			}
			if (cur == 0) {
				res++;
			}
		}
		
		return res;
	}
	
}
