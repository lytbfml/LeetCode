package algorithms.math;

/**
 * @author Yangxiao on 8/31/2019.
 *
 * wc152
 */
public class PrimeArrangement {
	
	public static void main(String[] args) {
		PrimeArrangement cs = new PrimeArrangement();
		System.out.println(cs.numPrimeArrangements(5));
		System.out.println(cs.numPrimeArrangements(100));
		System.out.println(cs.numPrimeArrangements(11));
	}
	
	public int numPrimeArrangements(int n) {
		if (n == 1)
			return 1;
		int count = 1;
		boolean[] nP = new boolean[n+1];
		if (n >= 3) {
			count = 1;
			for (int i = 3; i <= n; i += 2) {
				if (!nP[i]) {
					count++;
					for (int j = 3; i * j <= n; j += 2) {
						nP[i * j] = true;
					}
				}
			}
		}
		int mod = (int) 1e9 + 7;
		long res = 1;
		for (int i = 1; i <= count; i++) {
			res = res * i % mod;
		}
		for (int i = 1; i <= (n - count); i++) {
			res = res * i % mod;
		}
		return (int) res % mod;
	}
}
