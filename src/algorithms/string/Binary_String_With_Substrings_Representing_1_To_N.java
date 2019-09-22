package algorithms.string;

/**
 * @author Yangxiao on 3/24/2019.
 * <p>
 * <p>
 * Given a binary string S (a string consisting only of '0' and '1's) and a positive integer N,
 * return true if and only if for every integer X from 1 to N, the binary representation of X is a
 * substring of S.
 */
public class Binary_String_With_Substrings_Representing_1_To_N {
	
	public static void main(String[] args) {
		Binary_String_With_Substrings_Representing_1_To_N cs = new Binary_String_With_Substrings_Representing_1_To_N();
		System.out.println(cs.queryString("0110", 4));
	}
	
	public boolean queryString_s(String S, int N) {
		if (N == 1 && S.equals("1")) {
			return true;
		}
		int cn = 1;
		while(cn <= N) {
			if (!S.contains(Integer.toBinaryString(cn))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean queryString_Sol(String S, int N) {
		for (int i = N; i > N / 2; --i)
			if (!S.contains(Integer.toBinaryString(i)))
				return false;
		return true;
	}
	
	
	public boolean queryString(String S, int N) {
		if (N == 1 && S.equals("1")) {
			return true;
		}
		int len = 1;
		double t = 1;
		while (N > t) {
			t = t + Math.pow(2, len);
			len++;
		}
		
		if (S.length() < len) {
			return false;
		}
		
		while (N > 0) {
			if (!S.contains(Integer.toBinaryString(N--))) {
				return false;
			}
		}
		
		return true;
	}
}
