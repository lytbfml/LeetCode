package algorithms.math;

/**
 * @author Yangxiao Wang on 8/29/2019
 */
public class Palindrome_Number {
	
	public boolean isPalindrome(int x) {
		if (x < 0) return false;
		String s = String.valueOf(x);
		int l = 0, r = s.length() - 1;
		while (l <= r) {
			if (s.charAt(l++) != s.charAt(r--)) {
				return false;
			}
		}
		
		return true;
	}
	
	// how to reverse a integer
	public boolean isPalindrome_Sol(int x) {
		if (x < 0 || (x % 10 == 0 && x != 0)) return false;
		
		int m = 0;
		while (x > m) {
			m = m * 10 + x % 10;
			x /= 10;
		}
		
		return x == m || x == m / 10;
	}
	
}
