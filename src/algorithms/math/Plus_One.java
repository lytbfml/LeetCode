package algorithms.math;

/**
 * @author Yangxiao on 10/24/2018.
 */

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list,
 * and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 */
class Plus_One {
	public int[] plusOne(int[] digits) {
		if (digits == null || digits.length == 0){
			return new int[0];
		}
		
		int len = digits.length;
		
		for (int i = len - 1; i >= 0; i--) {
			if(digits[i] == 9) {
				digits[i] = 0;
				if(i == 0) {
					int arr[] = new int[len+1];
					arr[0] = 1;
					for (int j = 0; j < digits.length; j++) {
						arr[j+1] = digits[j];
					}
					return arr;
				}
			} else {
				digits[i] += 1;
				break;
			}
		}
		
		return  digits;
	}
}
