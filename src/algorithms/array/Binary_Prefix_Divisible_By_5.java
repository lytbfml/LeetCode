package algorithms.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yangxiao Wang on 5/4/2019
 */
public class Binary_Prefix_Divisible_By_5 {
	
	public static void main(String[] args) {
		Binary_Prefix_Divisible_By_5 cs = new Binary_Prefix_Divisible_By_5();
		int[] x = {1,0,0,1,0,1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,0,1,0,0,0,0,1,1,0,1,0,0,0,1};
		cs.prefixesDivBy5(x);
	}
	
	public List<Boolean> prefixesDivBy5(int[] A) {
		int n = A.length;
		List<Boolean> res = new LinkedList<>();
		int x = 0;
		for (int i = 0; i < n; i++) {
			res.add(x * 2 + A[i] == 0 || x * 2 + A[i] == 5);
			System.out.println(x * 2 + A[i]);
			x = (x * 2 + A[i]) % 5;
		}
		
		return res;
	}
	
	public List<Boolean> prefixesDivBy5_Sol(int[] A) {
		List<Boolean> result = new ArrayList<>(A.length);
		int remainder = 0;
		for (int a : A) {
			remainder = ((remainder << 1) + a) % 5;
			result.add(remainder == 0);
		}
		return result;
	}
	
	
}
