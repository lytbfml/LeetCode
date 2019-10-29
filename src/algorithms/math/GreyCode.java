package algorithms.math;

import java.util.ArrayList;
import java.util.List;

/**
 * https://en.wikipedia.org/wiki/Gray_code
 * @author Yangxiao on 10/26/2019.
 */
public class GreyCode {
	
	public List<Integer> grayCode(int n) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < (1 << n); i++) {
			res.add(i ^ (i >> 1));
		}
		return res;
	}
	
	// 000 001 011 010 ->+ 100 101 111 110 -> 110 111 101 100
	public List<Integer> grayCode_1(int n) {
		List<Integer> rs = new ArrayList<Integer>();
		rs.add(0);
		for (int i = 0; i < n; i++) {
			int size = rs.size();
			for (int k = size - 1; k >= 0; k--)
				rs.add(rs.get(k) | 1 << i);
		}
		return rs;
	}
}
