package algorithms.hashtable;

/**
 * @author Yangxiao Wang on 2019-07-09
 */
public class Jewels_and_Stones {
	
	
	public int numJewelsInStones(String J, String S) {
		int j = J.length();
		int s = S.length();
		int[] hash = new int[60];
		for (char c : S.toCharArray()) {
			hash[c - 'A']++;
		}
		int res = 0;
		for (char c : J.toCharArray()) {
			res += hash[c - 'A'];
		}
		return res;
	}
}
