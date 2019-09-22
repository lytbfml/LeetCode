package algorithms.string;

/**
 * @author Yangxiao on 11/12/2018.
 */

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 */
class Reverse_Integer {
	
	//O(n) space
	public int reverse(int x) {
		int start = 0;
		if (x < 0) {
			start = 1;
		}
		String s = Integer.toString(x);
		
		int n = s.length();
		char[] arr = s.toCharArray();
		for (int i = 0; i < n / 2; i++) {
			char left = arr[i + start];
			arr[i + start] = arr[n - 1 - i];
			arr[n - 1 - i] = left;
		}
		long re = Long.parseLong(new String(arr));
		if (re > Integer.MAX_VALUE || re < Integer.MIN_VALUE) {
			return 0;
		}
		
		return (int) re;
	}
	
	public int reverse_Sol(int x) {
		int rev = 0;
		while (x != 0) {
			int pop = x % 10;
			x /= 10;
			if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
				return 0;
			}
			if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
				return 0;
			}
			rev = rev * 10 + pop;
		}
		return rev;
	}
	
//	public static void main(Structures.String[] args) {
//		Reverse_Integer ri = new Reverse_Integer();
//		System.out.println(ri.reverse(1534236469));
//	}
}
