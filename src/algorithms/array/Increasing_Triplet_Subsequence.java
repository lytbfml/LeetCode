package algorithms.array;

/**
 * @author Yangxiao Wang on 5/17/2019
 */
public class Increasing_Triplet_Subsequence {
	
	public boolean increasingTriplet(int[] nums) {
		if (nums.length < 3)
			return false;
		int one = Integer.MAX_VALUE, two = one;
		for (int num : nums) {
			if (num < one)
				one = num;
			else if (num < two)
				two = num;
			else
				return true;
		}
		return false;
	}
}
