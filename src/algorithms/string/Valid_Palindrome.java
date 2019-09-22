package algorithms.string;

/**
 * @author Yangxiao on 11/13/2018.
 */

import java.util.ArrayList;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and
 * ignoring cases.
 * <p>
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 */
class Valid_Palindrome {
	
	
	public boolean isPalindrome(String s) {
		s = s.trim();
		int n = s.length();
		if (n == 0) {
			return true;
		}
		char[] arr = s.toCharArray();
		ArrayList<Character> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if ((arr[i] >= 'a' && arr[i] <= 'z') || (arr[i] >= 'A' && arr[i] <= 'Z') ||
					(arr[i] >= '0' && arr[i] <= '9')) {
				list.add(arr[i]);
			}
		}
		n = list.size();
		arr = new char[n];
		for (int i = 0; i < n; i++) {
			arr[i] = list.get(i);
		}
		String old = new String(arr);
		old = old.toLowerCase();
		arr = old.toCharArray();
		for (int i = 0; i < n / 2; i++) {
			char left = arr[i];
			arr[i] = arr[n - 1 - i];
			arr[n - 1 - i] = left;
		}
		System.out.println(old);
		return new String(arr).equals(old);
	}
	
	public boolean isPalindrome_Sol1(String s) {
		String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
		String rev = new StringBuffer(actual).reverse().toString();
		return actual.equals(rev);
	}
	
	public boolean isPalindrome_Sol2(String s) {
		if (s != null) {
			int left, right, i = 0, j = s.length() - 1;
			while (i < j) {
				left = s.charAt(i);
				if (type(left) == -1) { i++; continue; }
				right = s.charAt(j);
				if (type(right) == -1) { j--; continue; }
				
				if (type(left) != type(right)) { // should belong to the same type
					return false;
				} else if (left != right && Math.abs(left - right) != 32) {
					return false;
				}
				
				i++; j--;
			}
		}
		return true;
	}
	
	public static int type(int c) {
		if (c >= '0' && c <= '9') {
			return 0; // when it's a number, return 0
		} else if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
			return 1; // when it's a letter, return 1
		}
		return -1;
	}
	
	public boolean isPalindrome_Sol3(String s, int low, int high) {
		while (low < high)
			if (s.charAt(low++) != s.charAt(high--))
				return false;
		return true;
	}
	
	public static void main(String[] args) {
		Valid_Palindrome vp = new Valid_Palindrome();
		boolean x = vp.isPalindrome("A man, a plan, a canal: Panama");
		System.out.println(x);
	}
}
