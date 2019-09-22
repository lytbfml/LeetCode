package algorithms.math;

/**
 * @author Yangxiao Wang on 2019-07-01
 */
public class Powx_n {
	
	public static void main(String[] args) {
		Powx_n cs = new Powx_n();
		// cs.Powx_n(0.00001, 2147483647);
		// cs.Powx_n(3.0, 3);
		cs.myPow(2.00000, -2147483648);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
	}
	
	public double myPow(double x, int n) {
		if (n == 0) return 1.0;
		
		if (n < 0) {
			if (n == Integer.MIN_VALUE) {
				n++;
				n = -n;
				x = 1 / x;
				return x * myPow(x, n);
			}
			n = -n;
			x = 1 / x;
		}
		double res = 1;
		while (n > 0) {
			if (n % 2 == 1) res *= x;
			x *= x;
			n >>= 1;
		}
		return res;
	}
	
	public double myPow_Sol(double x, int n) {
		if (n == 0)
			return 1;
		
		double t = myPow(x, n / 2);
		if (n % 2 != 0)
			return n < 0 ? 1 / x * t * t : x * t * t;
		else
			return t * t;
	}
	
	class Solution {
		public double myPow(double x, int n) {
			long N = n;
			if (N < 0) {
				x = 1 / x;
				N = -N;
			}
			double ans = 1;
			double current_product = x;
			for (long i = N; i > 0; i /= 2) {
				if ((i % 2) == 1) {
					ans = ans * current_product;
				}
				current_product = current_product * current_product;
			}
			return ans;
		}
	}
}
