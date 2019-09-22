package algorithms.math;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yangxiao Wang on 8/29/2019
 */
public class Happy_Number {
	
	public boolean isHappy(int n) {
		Set<Integer> set = new HashSet<>();
		int tmp = 0, rem = 0;
		while (tmp != 1) {
			if (set.contains(n)) return false;
			set.add(n);
			tmp = 0;
			while (n > 0) {
				rem = n % 10;
				tmp += rem * rem;
				n /= 10;
			}
			if (tmp == 1) return true;
			n = tmp;
		}
		return true;
	}
	
	class Solution {
		// It converts a HashTable problem into a detecting cycled link list problem
		// Traverse linked list using two pointers.
		// Move one pointer(slow_p) by one and another pointer(fast_p) by two.
		// If these pointers meet at the same node then there is a loop. If pointers do not meet then linked list doesn’t have a loop
		public boolean isHappy(int n) {
			if (n == 0) return false;
			int x = n;
			int y = n;
			while (x > 1) {
				x = cal(x);
				if (x == 1) return true;
				y = cal(cal(y));
				if (y == 1) return true;
				
				if (x == y) return false;
			}
			return true;
		}
		
		public int cal(int n) {
			int x = n;
			int s = 0;
			while (x > 0) {
				s = s + (x % 10) * (x % 10);
				x = x / 10;
			}
			return s;
		}
		
	}
	
	
	class Solution2 {
		
		/**
		 * proof:
		 * <p>
		 * 1.loop number is less than 162.
		 * <p>
		 * Assume f(x) is the sum of the squares of x's digits. let's say 0 < x <= 9,999,999,999
		 * which is larger than the range of an int.
		 * f(x) <= 9^2 * 10 = 810. So no mater how big x is, after one step of f(x),
		 * it will be less than 810.The most large f(x) value (x < 810) is f(799) = 211.
		 * And do this several times: f(211) < f(199) = 163. f(163) < f(99) = 162.
		 * So no mater which x you choose after several times of f(x),it finally fall in the range of [1,162] and can never get out.
		 * <p>
		 * 2.I check every unhappy number in range of [1,162] , they all fall in loop {4,16,37,58,89,145,42,20},
		 * which means every unhappy number fall in this loop.
		 *
		 * @param n
		 * @return
		 */
		public boolean isHappy(int n) {
			int sum = n;
			while (sum != 1) {
				sum = getNum(sum);
				if (sum == 4) {
					return false;
				}
			}
			return true;
		}
		
		private int getNum(int n) {
			int sum = 0;
			while (n != 0) {
				sum += (n % 10) * (n % 10);
				n /= 10;
			}
			return sum;
		}
		
		
		public boolean isHappy_sameIdea(int n) {
			int temp = 0;
			while (n != 1) {
				if (n == 4 || n == 16 || n == 37 || n == 58 || n == 89 || n == 145 || n == 42 || n == 20)
					return false;
				
				while (n / 10 > 0) {
					temp += (n % 10) * (n % 10);
					n = n / 10;
				}
				temp += n * n;
				n = temp;
				temp = 0;
			}
			return true;
		}
	}
}
