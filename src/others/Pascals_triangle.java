package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yangxiao on 3/25/2019.
 */
public class Pascals_triangle {
	
	
	public static void main(String[] args) {
	
	}
	
	
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();
		if (numRows <= 0) {
			return res;
		}
		res.add(new ArrayList<>(Arrays.asList(1)));
		
		for (int i = 1; i < numRows; i++) {
			List<Integer> arr = new ArrayList<>();
			arr.add(1);
			for (int j = 1; j < i; j++) {
				arr.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
			}
			arr.add(1);
			res.add(arr);
		}
		
		return res;
	}
	
	
	public List<List<Integer>> generate_Sol(int numRows) {
		List<List<Integer>> allrows = new ArrayList<>();
		ArrayList<Integer> row = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			row.add(0, 1);
			for (int j = 1; j < row.size() - 1; j++) {
				row.set(j, row.get(j) + row.get(j + 1));
			}
			allrows.add(new ArrayList<>(row));
		}
		return allrows;
	}
	
}
