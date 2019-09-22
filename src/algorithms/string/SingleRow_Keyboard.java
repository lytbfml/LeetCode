package algorithms.string;

/**
 * @author Yangxiao on 9/14/2019.
 */
public class SingleRow_Keyboard {
	
	public int calculateTime(String keyboard, String word) {
		int[] mem = new int[26];
		for (int i = 0; i < 26; i++) {
			mem[keyboard.charAt(i) - 'a'] = i;
		}
		int start = 0, res = 0;
		for (int i = 0; i < word.length(); i++) {
			int idx = mem[word.charAt(i) - 'a'];
			res += Math.abs(start - idx);
			start = idx;
		}
		return res;
	}
	
	// a little bit faster
	public int calculateTime1(String keyboard, String word) {
		int[] mem = new int[26];
		char[] cKey = keyboard.toCharArray();
		for (int i = 0; i < 26; i++) {
			mem[cKey[i] - 'a'] = i;
		}
		int start = 0, res = 0;
		char[] wd = word.toCharArray();
		for (int i = 0; i < wd.length; i++) {
			int idx = mem[wd[i] - 'a'];
			res += Math.abs(start - idx);
			start = idx;
		}
		return res;
	}
	
	public int calculateTime2(String keyboard, String word) {
		int[] alphabet = new int[26];
		int i = 0;
		for (char c : keyboard.toCharArray()) {
			alphabet[c - 'a'] = i;
			i++;
		}
		int prev = 0;
		int result = 0;
		for (char c : word.toCharArray()) {
			result += Math.abs(alphabet[c - 'a'] - prev);
			prev = alphabet[c - 'a'];
		}
		return result;
	}
}
