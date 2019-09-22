package algorithms.math;

/**
 * @author Yangxiao on 3/23/2019.
 */
public class Smallest_Integer_Divisible_by_K {
	public static void main(String[] args) {
		Smallest_Integer_Divisible_by_K cs = new Smallest_Integer_Divisible_by_K();
		System.out.println(cs.smallestRepunitDivByK_Sol(3));
	}
	
	public int smallestRepunitDivByK_Sol(int K) {
		if (K == 1) {
			return 1;
		}
		// System.out.println(K + ", " + (K & 1));
		if (K % 2 == 0 || K % 5 == 0) {
			return -1;
		}
		int mod = 0;
		for (int i = 1; i <= K; i++) {
			mod = (mod * 10 + 1) % K;
			if (mod == 0) {
				return i;
			}
		}
		return -1;
	}
	
	public int smallestRepunitDivByK(int K) {
		if (K == 1) {
			return 1;
		}
		if ((K & 1) == 0) {
			return -1;
		}
		if (K % 5 == 0) {
			return -1;
		}
		int[] seen = new int[K];
		int mod = 0;
		for (int i = 1; i <= K; i++) {
			mod = (mod * 10 + 1) % K;
			if (mod == 0) {
				return i;
			}
			if (seen[mod] == 1) {
				return -1;
			}
			seen[mod] = 1;
		}
		return -1;
	}
}
