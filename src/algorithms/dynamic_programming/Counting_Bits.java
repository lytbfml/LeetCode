package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 4/16/2019.
 */
public class Counting_Bits {
	
	public int[] countBits(int num) {
		int[] bits = new int[num + 1];
		for (int i = 0; i <= num; i++) {
			bits[i] = Integer.bitCount(i);
		}
		return bits;
	}
	
	public int[] countBits_dp(int num) {
		int[] bits = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			bits[i] = bits[i / 2] + i % 2;
		}
		return bits;
	}
	
	
	public int[] countBits_Fast(int num) {
		int[] result = new int[num + 1];
		fill(result, num, 1, 1);
		return result;
	}
	
	private void fill(int[] result, int num, int cur, int count) {
		if (cur > num || result[cur] != 0) {
			return;
		}
		result[cur] = count;
		fill(result, num, (cur << 1) + 1, count + 1);
		fill(result, num, cur << 1, count);
	}
}
