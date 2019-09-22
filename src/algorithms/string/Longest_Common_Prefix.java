package algorithms.string;

/**
 * @author Yangxiao on 11/15/2018.
 */

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 */
class Longest_Common_Prefix {
	
	//8ms
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) { return ""; }
		String maxS = strs[0];
		String minS = maxS;
		int n = strs.length;
		for (int i = 1; i < n; i++) {
			StringBuilder minTemp = new StringBuilder();
			minTemp.append("");
			String cur = strs[i];
			for (int j = 0; j < cur.length() && j < maxS.length(); j++) {
				if (maxS.charAt(j) == cur.charAt(j)) {
					minTemp.append(maxS.charAt(j));
				} else {
					break;
				}
			}
			if (minS.length() > minTemp.length()) {
				minS = minTemp.toString();
			}
		}
		return minS;
	}
	
	public String longestCommonPrefix_Sol1(String[] strs) {
		if (strs.length == 0) { return ""; }
		String prefix = strs[0];
		int pin = 0;
		while (pin < strs.length) {
			while (strs[pin].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
			}
			if (prefix.length() == 0) {
				return "";
			}
			pin++;
		}
		return prefix;
	}
	
	//5ms
	public String longestCommonPrefix_Sol2(String[] strs) {
		if (strs.length == 0) { return ""; }
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) { return ""; }
			}
		}
		return prefix;
	}
	
	public String longestCommonPrefix_Sol3(String[] strs) {
		if (strs == null || strs.length == 0) { return ""; }
		for (int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j++) {
				if (i == strs[j].length() || strs[j].charAt(i) != c) {
					return strs[0].substring(0, i);
				}
			}
		}
		return strs[0];
	}
	
	//6ms
	public String longestCommonPrefix_Sol4(String[] strs) {
		if (strs == null || strs.length == 0) { return ""; }
		return longestCommonPrefix(strs, 0, strs.length - 1);
	}
	
	private String longestCommonPrefix(String[] strs, int l, int r) {
		if (l == r) {
			return strs[l];
		} else {
			int mid = (l + r) / 2;
			String lcpLeft = longestCommonPrefix(strs, l, mid);
			String lcpRight = longestCommonPrefix(strs, mid + 1, r);
			return commonPrefix(lcpLeft, lcpRight);
		}
	}
	
	String commonPrefix(String left, String right) {
		int min = Math.min(left.length(), right.length());
		for (int i = 0; i < min; i++) {
			if (left.charAt(i) != right.charAt(i)) { return left.substring(0, i); }
		}
		return left.substring(0, min);
	}
	
	public static void main(String[] args) {
		Longest_Common_Prefix lp = new Longest_Common_Prefix();
		String[] x = {"", "b"};
		String m = lp.longestCommonPrefix(x);
		System.out.println(m);
	}
}
