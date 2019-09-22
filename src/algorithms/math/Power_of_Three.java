package algorithms.math;

/**
 * @author Yangxiao on 3/25/2019.
 */
public class Power_of_Three {
	
	public static void main(String[] args) {
		Power_of_Three ps = new Power_of_Three();
		System.out.println(ps.isPowerOfThree(0));
	}
	
	public boolean isPowerOfThree(int n) {
		double x = Math.log10(n) / Math.log10(3);
		return (x == Math.floor(x) && !Double.isInfinite(x));
	}
	
	public boolean isPowerOfThree_Sol1(int n) {
		return n > 0 && (1162261467 % n == 0);
	}
	
	public boolean isPowerOfThree_Sol11(int n) {
		int maxPowerOfThree = (int) Math.pow(3, (int) (Math.log(0x7fffffff) / Math.log(3)));
		return n > 0 && maxPowerOfThree % n == 0;
	}
	
	public boolean isPowerOfThree_baseConvert(int n) {
		return Integer.toString(n, 3).matches("^10*$");
	}
	
	public boolean isPowerOfThree_loop(int n) {
		if (n < 1) {
			return false;
		}
		
		while (n % 3 == 0) {
			n /= 3;
		}
		
		return n == 1;
	}
}
