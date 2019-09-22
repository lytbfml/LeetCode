package algorithms.string;

/**
 * @author Yangxiao on 11/12/2018.
 */

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't
 * exist, return -1.
 * <p>
 * s = "leetcode"
 * return 0.
 * <p>
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 */
class First_Unique_Character_in_a_String {
	
	public int firstUniqChar(String s) {
		int n = s.length();
		for (int i = 0; i < n; i++) {
			if (s.indexOf(s.charAt(i), i + 1) == -1) {
				if (s.indexOf(s.charAt(i)) == i) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public int firstUniqChar_Sol(String s) {
		int idx = s.length();
		for (char c = 'a'; c <= 'z'; c++) {
			int firstIdx = s.indexOf(c);
			if (firstIdx != -1 && s.lastIndexOf(c) == firstIdx) {
				idx = Math.min(idx, firstIdx);
			}
		}
		return idx < s.length() ? idx : -1;
	}
	
	public static void main(String[] args) {
		First_Unique_Character_in_a_String fs = new First_Unique_Character_in_a_String();
		System.out.println(fs.firstUniqChar("c"));
	}
}
