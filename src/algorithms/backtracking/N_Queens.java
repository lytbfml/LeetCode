package algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangxiao on 9/10/2019.
 */
public class N_Queens {
	
	public static void main(String[] args) {
		N_Queens cs = new N_Queens();
		System.out.println(cs.solveNQueens(5));
	}
	
	// For all "hill" (/) diagonals row + column = const, and for all "dale" (\) diagonals row - column = const.
	int len = 0;
	StringBuilder sb = new StringBuilder();
	boolean[] col;
	boolean[] d1;
	boolean[] d2;
	
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> res = new ArrayList<>();
		len = n;
		col = new boolean[n];
		d1 = new boolean[2 * n];
		d2 = new boolean[2 * n];
		for (int i = 0; i < len; i++) {
			sb.append('.');
		}
		backtrack(res, new int[n], 0);
		return res;
	}
	
	private void backtrack(List<List<String>> res, int[] list, int num) {
		if (num == len) {
			res.add(buildList(list));
		} else {
			for (int j = 0; j < len; j++) {
				int dL = num + j, dR = num - j + len;
				if (!col[j] && !d1[dL] && !d2[dR]) {
					list[num] = j;
					col[j] = true;
					d1[dL] = true;
					d2[dR] = true;
					backtrack(res, list, num + 1);
					col[j] = false;
					d1[dL] = false;
					d2[dR] = false;
				}
			}
		}
	}
	
	private List<String> buildList(int[] list) {
		List<String> res = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			sb.setCharAt(list[i], 'Q');
			res.add(sb.toString());
			sb.setCharAt(list[i], '.');
		}
		return res;
	}
	
}
