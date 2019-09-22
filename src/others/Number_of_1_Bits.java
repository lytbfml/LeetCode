package others;

/**
 * @author Yangxiao on 3/25/2019.
 */
public class Number_of_1_Bits {
	
	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
		return Integer.bitCount(n);
	}
	
	public int hammingWeight_Sol1(int n) {
		int bits = 0;
		int mask = 1;
		for (int i = 0; i < 32; i++) {
			if ((n & mask) != 0) {
				bits++;
			}
			mask <<= 1;
		}
		return bits;
	}
	
	public int hammingWeight_Sol4(int n) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			count += (n >> i & 1) == 1 ? 1 : 0;
		}
		return count;
	}
	
	public int hammingWeight_Sol2(int n) {
		int sum = 0;
		while (n != 0) {
			sum++;
			n &= (n - 1);
		}
		return sum;
	}
	
	public int hammingWeight_Sol3(int n) {
		int ones = 0;
		while (n != 0) {
			ones = ones + (n & 1);
			n = n >>> 1;
		}
		return ones;
	}
}
