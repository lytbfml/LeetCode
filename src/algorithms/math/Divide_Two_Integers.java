package algorithms.math;

/**
 * @author Yangxiao Wang on 2019-07-01
 */
public class Divide_Two_Integers {
	
	
	public int divide_Sol(int dividend, int divisor) {
		if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
		if (dividend > 0 && divisor > 0) return divideHelper(-dividend, -divisor);
		else if (dividend > 0) return -divideHelper(-dividend, divisor);
		else if (divisor > 0) return -divideHelper(dividend, -divisor);
		else return divideHelper(dividend, divisor);
	}
	
	private int divideHelper(int dividend, int divisor) {
		// base case
		if (divisor < dividend) return 0;
		// get highest digit of divisor
		int cur = 0, res = 0;
		while ((divisor << cur) >= dividend && divisor << cur < 0 && cur < 31) cur++;
		res = dividend - (divisor << cur - 1);
		if (res > divisor) return 1 << cur - 1;
		return (1 << cur - 1) + divide(res, divisor);
	}
	
	class Solution {
		public int divide(int dividend, int divisor) {
			int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1, quotient = 0;
			if (dividend == Integer.MIN_VALUE) {
				if (divisor == -1) return Integer.MAX_VALUE;
				if (divisor == Integer.MIN_VALUE) return 1;
				dividend += Math.abs(divisor);
				quotient++;
			}
			if (divisor == Integer.MIN_VALUE) return 0;
			dividend = Math.abs(dividend);
			divisor = Math.abs(divisor); //Algorithms.Math.abs(-2147483648)=-2147483648
			while (dividend >= divisor) {
				int tmp = divisor, count = 1;
				while (dividend - tmp >= tmp) { //(dividend >= tmp<<1 or >= 2*tmp) may cause overflows
					tmp <<= 1;
					count <<= 1;
				}
				dividend -= tmp;
				quotient += count;
			}
			return sign * quotient;
		}
	}
	
	
	public int divide(int y, int x) {
		int sign = (y < 0) ^ (x < 0) ? -1 : 1;
		int q = 0;
		if (y == Integer.MIN_VALUE) {
			if (x == Integer.MIN_VALUE) return 1;
			if (x == -1) return Integer.MAX_VALUE;
			y = y + Math.abs(x);
			q++;
		}
		if (x == Integer.MIN_VALUE) return 0;
		y = Math.abs(y);
		x = Math.abs(x);
		while (y >= x) {
			int temp = x;
			int cnt = 1;
			while (y - temp >= temp) {
				temp <<= 1;
				cnt <<= 1;
			}
			y -= temp;
			q += cnt;
		}
		return sign * q;
	}
	
}





