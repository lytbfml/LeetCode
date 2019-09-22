package algorithms.hashtable;

/**
 * @author Yangxiao on 9/14/2019.
 */
public class Maximum_Number_Balloons {
	
	public int maxNumberOfBalloons(String text) {
		int n = text.length();
		int[] mem = new int[26];
		char[] cc = text.toCharArray();
		for (int i = 0; i < n; i++) {
			mem[cc[i] - 'a']++;
		}
		int res = Integer.MAX_VALUE;
		res = Math.min(mem['b' - 'a'], res);
		res = Math.min(mem['a' - 'a'], res);
		res = Math.min(mem['n' - 'a'], res);
		res = Math.min(mem['l' - 'a'] / 2, res);
		res = Math.min(mem['o' - 'a'] / 2, res);
		return res;
	}
	
}
