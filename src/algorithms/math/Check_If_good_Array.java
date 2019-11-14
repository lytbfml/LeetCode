package algorithms.math;

/**
 * @author Yangxiao on 11/2/2019.
 */
public class Check_If_good_Array {
	
	public boolean isGoodArray(int[] A) {
		int x = A[0], y;
		for (int a : A) {
			while (a > 0) {
				y = x % a;
				x = a;
				a = y;
			}
		}
		return x == 1;
	}
}
