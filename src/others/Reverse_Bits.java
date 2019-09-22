package others;

/**
 * @author Yangxiao on 3/25/2019.
 */
public class Reverse_Bits {
	
	// you need treat n as an unsigned value
	public int reverseBits(int n) {
		return Integer.reverse(n);
	}
	
	public int reverseBits_Sol1(int n) {
		int res = 0;
		for (int i = 0; i < 32; i++) {
			res = (res << 1) | ((n >> i) & 1);
		}
		return res;
	}
}
