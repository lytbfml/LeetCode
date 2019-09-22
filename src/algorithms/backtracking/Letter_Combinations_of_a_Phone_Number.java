package algorithms.backtracking;

import java.util.*;

/**
 * @author Yangxiao Wang on 5/17/2019
 */
public class Letter_Combinations_of_a_Phone_Number {
	
	public static void main(String[] args) {
		Letter_Combinations_of_a_Phone_Number cs = new Letter_Combinations_of_a_Phone_Number();
		cs.letterCombinations("23");
	}
	
	public List<String> letterCombinations(String digits) {
		if (digits.isEmpty())
			return new ArrayList<>();
		int n = digits.length();
		String[] let = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		LinkedList<String> res = new LinkedList<>();
		res.offer("");
		for (int i = 0; i < n; i++) {
			int cur = Character.getNumericValue(digits.charAt(i));
			while (res.peek().length() == i) {
				char[] temp = let[cur].toCharArray();
				String x = res.poll();
				for (char c : temp) {
					res.offer(x + c);
				}
			}
		}
		return res;
	}
	
	static class Solution {
		
		private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		
		public List<String> letterCombinations(String digits) {
			
			List<String> ret = new LinkedList<>();
			if (digits == null || digits.length() == 0) {
				return ret;
			}
			backtrack("", digits, 0, ret);
			return ret;
		}
		
		public void backtrack(String prefix, String digits, int offset, List<String> ret) {
			if (offset >= digits.length()) {
				ret.add(prefix);
				return;
			}
			
			String letters = KEYS[(digits.charAt(offset) - '0')];
			for (int i = 0; i < letters.length(); i++) {
				backtrack(prefix + letters.charAt(i), digits, offset + 1, ret);
			}
		}
	}
	
	class Solution_back2 {
		Map<String, String> phone = new HashMap<String, String>() {{
			put("2", "abc");
			put("3", "def");
			put("4", "ghi");
			put("5", "jkl");
			put("6", "mno");
			put("7", "pqrs");
			put("8", "tuv");
			put("9", "wxyz");
		}};
		
		List<String> output = new ArrayList<>();
		
		public void backtrack(String combination, String next_digits) {
			// if there is no more digits to check
			if (next_digits.length() == 0) {
				// the combination is done
				output.add(combination);
			}
			// if there are still digits to check
			else {
				// iterate over all letters which map
				// the next available digit
				String digit = next_digits.substring(0, 1);
				String letters = phone.get(digit);
				for (int i = 0; i < letters.length(); i++) {
					String letter = phone.get(digit).substring(i, i + 1);
					// append the current letter to the combination
					// and proceed to the next digits
					backtrack(combination + letter, next_digits.substring(1));
				}
			}
		}
		
		public List<String> letterCombinations(String digits) {
			if (digits.length() != 0)
				backtrack("", digits);
			return output;
		}
	}
}
