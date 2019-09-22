package algorithms.dfs;

/**
 * @author Yangxiao Wang on 5/4/2019
 */
public class Number_of_Enclaves {
	
	public int numEnclaves(int[][] a) {
		int n = a.length;
		int m = a[0].length;
		for (int i = 0; i < n; i++) {
			if (a[i][0] == 1) {
				dfs(a, 0, i);
			}
		}
		
		for (int i = 0; i < n; i++) {
			if (a[i][m - 1] == 1) {
				dfs(a, 0, i);
			}
		}
		
		for (int i = 0; i < m; i++) {
			if (a[0][i] == 1) {
				dfs(a, 0, i);
			}
		}
		
		for (int i = 0; i < m; i++) {
			if (a[n - 1][i] == 1) {
				dfs(a, 0, i);
			}
		}
		int re = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (a[i][j] == 1) {
					re++;
				}
			}
		}
		return re;
	}
	
	private void dfs(int[][] a, int i, int j) {
		if (i >= 0 && i < a.length && j >= 0 && j < a[0].length && a[i][j] == 1) {
			a[i][j] = 0;
			dfs(a, i - 1, j);
			dfs(a, i + 1, j);
			dfs(a, i, j - 1);
			dfs(a, i, j + 1);
		}
	}
}
