package algorithms.string;

/**
 * @author Yangxiao Wang on 8/17/2019
 */
public class Last_Substring_in_Lexicographical_Order {
	
	public String lastSubstring(String s) {
		int maxIndex = 0, candIndex = 1, p = 0;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) > s.charAt(maxIndex)) {
				maxIndex = i;
				p = i;
				candIndex = i + 1;
			} else if (s.charAt(i) > s.charAt(p)) {
				maxIndex = candIndex;
				candIndex = i + 1;
				p = maxIndex;
			} else if (s.charAt(i) == s.charAt(p)) {
				p++;
			} else {
				p = maxIndex;
				candIndex = i + 1;
			}
		}
		return s.substring(maxIndex);
	}
}
