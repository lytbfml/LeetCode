package algorithms.string;

/**
 * @author Yangxiao on 11/14/2018.
 */


/**
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary until the first
 * non-whitespace character is found. Then, starting from this character, takes an optional initial
 * plus or minus sign followed by as many numerical digits as possible, and interprets them as a
 * numerical value.
 * <p>
 * The string can contain additional characters after those that form the integral number, which are
 * ignored and have no effect on the behavior of this function.
 * <p>
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if
 * no such sequence exists because either str is empty or it contains only whitespace characters, no
 * conversion is performed.
 * <p>
 * If no valid conversion could be performed, a zero value is returned.
 * <p>
 * Note:
 * <p>
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit
 * signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of
 * representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 */
class String_to_Integer_atoi {
	
	public int myAtoi(String str) {
		int n = str.length();
		if (n == 0 || str.trim().length() == 0) { return 0; }
		boolean found = false;
		int index = 0;
		while (!found) {
			char t = str.charAt(index);
			if (t == ' ') {
				index++;
			} else {
				if ((t < '0' || t > '9') && t != '-' && t != '+') {
					return 0;
				} else {
					found = true;
				}
			}
		}
		if (index >= n) {
			return 0;
		}
		
		found = false;
		int sign = 1;
		if (str.charAt(index) == '-') {
			index++;
			sign = -1;
		} else if (str.charAt(index) == '+') {
			index++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		while (!found && index < n) {
			char t = str.charAt(index);
			if (t <= '9' && t >= '0') {
				sb.append(t);
				index++;
			} else {
				found = true;
			}
		}
		
		if (sb.length() == 0) {
			return 0;
		}
		
		long re = 0;
		
		try {
			re = Long.parseLong(sb.toString());
		} catch (NumberFormatException e) {
			return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		}
		re *= sign;
		
		if (re > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (re < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		
		return (int) re;
	}
	
	public int myAtoi_Sol1(String str) {
		int sign = 1, base = 0, i = 0, INT_MAX = Integer.MAX_VALUE, INT_MIN = Integer.MIN_VALUE;
		while (i < str.length() && str.charAt(i) == ' ') {
			i++;
		}
		if (i >= str.length()) { return 0; }
		
		if (str.charAt(i) == '+' || str.charAt(i) == '-') {
			if (str.charAt(i) == '-') { sign = -1; }
			i++;
		}
		
		while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			if (base > INT_MAX / 10 || (base == INT_MAX / 10 && str.charAt(i) - '0' > 7)) {
				if (sign == -1) { return INT_MIN; } else { return INT_MAX; }
			}
			base = 10 * base + (str.charAt(i++) - '0');
		}
		
		return base * sign;
		
	}
	
	public static void main(String[] args) {
		String_to_Integer_atoi ss = new String_to_Integer_atoi();
		System.out.println(ss.myAtoi("  0000000000012345678"));
	}
}
