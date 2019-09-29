package algorithms.searching;

/**
 * @author Yangxiao on 9/27/2019.
 */
public class Find_smallest_Common_Element_in_All_Rows {
	
	public int smallestCommonElement(int[][] mat) {
		int n = mat.length, m = mat[0].length;
		for (int i = 0; i < m; i++) {
			int cur = mat[0][i];
			boolean find = true;
			for (int j = 1; j < n; j++) {
				int l = 0, r = m - 1;
				while (l < r) {
					int mid = (l + r) / 2;
					if (mat[j][mid] < cur) {
						l = mid + 1;
					} else {
						r = mid;
					}
				}
				if (mat[j][l] != cur) {
					find = false;
					break;
				}
			}
			if (find) {
				return cur;
			}
		}
		return -1;
	}
}
