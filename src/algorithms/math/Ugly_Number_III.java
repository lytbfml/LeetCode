package algorithms.math;

/**
 * @author Yangxiao Wang on 9/21/2019
 */
public class Ugly_Number_III {
	
	public static void main(String[] args) {
		Ugly_Number_III cs = new Ugly_Number_III();
		long time = System.currentTimeMillis();
		System.out.println(cs.nthUglyNumber(3, 2, 3, 5));
		System.out.println(cs.nthUglyNumber(4, 2, 3, 4));
		System.out.println(cs.nthUglyNumber(5, 2, 11, 13));
		System.out.println(cs.nthUglyNumber(1000000000, 2, 217983653, 336916467));
		System.out.println(System.currentTimeMillis() - time);
	}
	
	public int nthUglyNumber(int n, int a, int b, int c) {
		int low = 1, high = Integer.MAX_VALUE;
		while (low < high) {
			int mid = low + (high - low) / 2;
			int num = count(a, b, c, mid);
			if (num < n) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return high;
	}
	
	private int count(long a, long b, long c, long num) {
		return (int) ((num / a) + (num / b) + (num / c)
				- (num / lcm(a, b))
				- (num / lcm(b, c))
				- (num / lcm(a, c))
				+ (num / lcm(a, lcm(b, c))));
	}
	
	// must return long
	private long lcm(long a, long b) {
		return (a * b) / gcd(a, b);
	}
	
	// must return long
	private long gcd(long a, long b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}
	
	
}
