package algorithms.math;

/**
 * wc128
 *
 * @author Yangxiao on 9/19/2019.
 */
public class Complement_of_Base_10_Integer {
	
	public static void main(String[] args) {
		Complement_of_Base_10_Integer cs = new Complement_of_Base_10_Integer();
		System.out.println(cs.bitwiseComplement(0));
		System.out.println(cs.bitwiseComplement(7));
		System.out.println(cs.bitwiseComplement(10));
	}
	
	public int bitwiseComplement(int N) {
		String ss = Integer.toBinaryString(N);
		int n = ss.length();
		char[] cc = ss.toCharArray();
		for (int i = 0; i < n; i++) {
			cc[i] = cc[i] == '1' ? '0' : '1';
		}
		String s = new String(cc);
		return Integer.parseInt(s, 2);
	}
	
	/**
	 * Let's find the first number X that X = 1111....1 > N
	 * And also, it has to be noticed that,
	 * N = 0 is a corner case expecting1 as result.
	 * <p>
	 * N + bitwiseComplement(N) = 11....11 = X
	 * Then bitwiseComplement(N) = X - N
	 */
	public int bitwiseComplement1(int N) {
		int X = 1;
		while (N > X) {
			X = X * 2 + 1;
		}
		return X - N;
	}
	
	/**
	 * N ^ bitwiseComplement(N) = 11....11 = X
	 * bitwiseComplement(N) = N ^ X
	 */
	public int bitwiseComplement2(int N) {
		int X = 1;
		while (N > X)
			X = X * 2 + 1;
		return N ^ X;
	}
}
