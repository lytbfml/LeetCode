package algorithms.string;

/**
 * @author Yangxiao on 10/31/2019.
 */
public class Length_of_Last_Word {
	
	public int lengthOfLastWord(String s) {
		if (s == null || s.isEmpty()) return 0;
		int end = s.length() - 1;
		while (end >= 0 && s.charAt(end) == ' ') end--;
		if (end == -1) return 0;
		int start = end;
		while (start >= 0 && s.charAt(start) != ' ') start--;
		return end - start;
	}
	
	public int lengthOfLastWord2(String s) {
		int len = s.length(), lastLength = 0;
		
		while (len > 0 && s.charAt(len - 1) == ' ') {
			len--;
		}
		while (len > 0 && s.charAt(len - 1) != ' ') {
			lastLength++;
			len--;
		}
		
		return lastLength;
	}
	
	public int lengthOfLastWord3(String s) {
		return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
	}
}
