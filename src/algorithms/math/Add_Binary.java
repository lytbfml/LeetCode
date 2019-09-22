package algorithms.math;

/**
 * @author Yangxiao Wang on 7/30/2019
 */
public class Add_Binary {
	
	public String addBinary(String A, String B) {
		int n = A.length(), m = B.length();
		n--;
		m--;
		StringBuilder sb = new StringBuilder();
		int c = 0;
		while (m >= 0 || n >= 0) {
			int a = n >= 0 ? A.charAt(n--) - '0' : 0;
			int b = m >= 0 ? B.charAt(m--) - '0' : 0;
			int cur = a ^ b ^ c;
			c = (a & b) | ((a ^ b) & c);
			sb.append(cur);
		}
		
		if (c != 0) sb.append(c);
		
		return sb.reverse().toString();
	}
	
	public String addBinary_1(String a, String b) {
		StringBuilder sb = new StringBuilder();
		int i = a.length() - 1;
		int j = b.length() - 1;
		int remainder = 0;
		while (i >= 0 || j >= 0) {
			int sum = remainder;
			if (i >= 0) sum += a.charAt(i--) - '0';
			if (j >= 0) sum += b.charAt(j--) - '0';
			sb.append(sum % 2);
			remainder = sum / 2;
		}
		if (remainder != 0) {
			sb.append(remainder);
		}
		return sb.reverse().toString();
	}
}
