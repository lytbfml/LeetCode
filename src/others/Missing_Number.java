package others;

/**
 * @author Yangxiao on 3/26/2019.
 */
public class Missing_Number {
	
	public static void main(String[] args) {
		Missing_Number ms = new Missing_Number();
		int[] s = {3, 2, 1};
		System.out.println(ms.missingNumber(s));
	}
	
	public int missingNumber(int[] nums) {
		int n = nums.length;
		int sum = (1 + n) * n / 2;
		int sum2 = 0;
		for (int i : nums) {
			sum2 += i;
		}
		return sum - sum2;
	}
	
	/*
	 * The basic idea is to use XOR operation. We all know that a^b^b =a, which means two xor
	 * operations with the same number will eliminate the number and reveal the original number.
	 * In this solution, I apply XOR operation to both the index and value of the array.
	 * In a complete array with no missing numbers, the index and value should be perfectly
	 * corresponding( nums[index] = index), so in a missing array, what left finally is the missing number.
	 */
	public int missingNumber_sol(int[] nums) {
		int missing = nums.length;
		for (int i = 0; i < nums.length; i++) {
			missing ^= i ^ nums[i];
		}
		return missing;
	}
}
