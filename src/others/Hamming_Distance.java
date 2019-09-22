package others;

/**
 * @author Yangxiao on 3/25/2019.
 */
public class Hamming_Distance {
	
	public int hammingDistance(int x, int y) {
		int m = x ^ y;
		int res = 0;
		for (int i = 0; i < 32; i++) {
			res += (m >> i) & 1;
		}
		return res;
	}
	
	public int hammingDistance_Sol1(int x, int y) {
		return Integer.bitCount(x ^ y);
	}
	
	//The problem is basically the same as counting the 1 bits in an integer,
	//and the useful trick to do that is : xor & (xor - 1) will eliminate the last 1 bit in a integer.
	public int hammingDistance_Sol2(int x, int y) {
		int xor = x ^ y;
		int c = 0;
		while (xor > 0) {
			xor &= (xor - 1);
			c++;
		}
		return c;
	}
}
