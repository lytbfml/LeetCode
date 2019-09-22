package algorithms.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangxiao on 3/25/2019.
 */
public class Fizz_Buzz {
	
	public List<String> fizzBuzz(int n) {
		if (n == 0) {
			return new ArrayList<>();
		}
		List<String> list = new ArrayList<>();
		for (int i = 1; i < n + 1; i++) {
			if (i % 3 == 0) {
				if (i % 5 == 0) {
					list.add("FizzBuzz");
				} else {
					list.add("Fizz");
				}
			} else if (i % 5 == 0) {
				if (i % 3 == 0) {
					list.add("FizzBuzz");
				} else {
					list.add("Buzz");
				}
			} else {
				list.add(Integer.toString(i));
			}
		}
		return list;
	}
	
	public List<String> fizzBuzz_Sol(int n) {
		List<String> ret = new ArrayList<String>(n);
		for (int i = 1, fizz = 0, buzz = 0; i <= n; i++) {
			fizz++;
			buzz++;
			if (fizz == 3 && buzz == 5) {
				ret.add("FizzBuzz");
				fizz = 0;
				buzz = 0;
			} else if (fizz == 3) {
				ret.add("Fizz");
				fizz = 0;
			} else if (buzz == 5) {
				ret.add("Buzz");
				buzz = 0;
			} else {
				ret.add(String.valueOf(i));
			}
		}
		return ret;
	}
}
