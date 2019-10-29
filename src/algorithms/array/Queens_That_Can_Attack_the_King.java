package algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yangxiao on 10/12/2019.
 */
public class Queens_That_Can_Attack_the_King {
	
	class Solution {
		public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
			List<List<Integer>> result = new ArrayList<>();
			boolean[][] seen = new boolean[8][8];
			for (int[] queen : queens) seen[queen[0]][queen[1]] = true;
			int[] dirs = {-1, 0, 1};
			for (int dx : dirs) {
				for (int dy : dirs) {
					if (dx == 0 && dy == 0) continue;
					int x = king[0], y = king[1];
					while (x + dx >= 0 && x + dx < 8 && y + dy >= 0 && y + dy < 8) {
						x += dx;
						y += dy;
						if (seen[x][y]) {
							result.add(Arrays.asList(x, y));
							break;
						}
					}
				}
			}
			return result;
		}
	}
	
	public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
		List<List<Integer>> res = new ArrayList<>();
		int[][] rr = new int[8][2];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 2; j++) {
				rr[i][j] = -1;
			}
		}
		for (int i = 0; i < queens.length; i++) {
			int[] cur = queens[i];
			if (cur[0] == king[0]) {
				if (cur[1] < king[1]) {
					if (cur[0] >= rr[0][0] && cur[1] > rr[0][1]) {
						rr[0][0] = cur[0];
						rr[0][1] = cur[1];
					}
				} else if (cur[1] > king[1]) {
					if (rr[1][0] == -1) {
						rr[1][0] = cur[0];
						rr[1][1] = cur[1];
						continue;
					}
					if (cur[0] >= rr[1][0] && cur[1] < rr[1][1]) {
						rr[1][0] = cur[0];
						rr[1][1] = cur[1];
					}
				}
			} else if (cur[1] == king[1]) {
				if (cur[0] < king[0]) {
					if (cur[0] > rr[2][0] && cur[1] >= rr[2][1]) {
						rr[2][0] = cur[0];
						rr[2][1] = cur[1];
					}
				} else if (cur[0] > king[0]) {
					if (rr[3][0] == -1) {
						rr[3][0] = cur[0];
						rr[3][1] = cur[1];
						continue;
					}
					if (cur[0] < rr[3][0] && cur[1] >= rr[3][1]) {
						rr[3][0] = cur[0];
						rr[3][1] = cur[1];
					}
				}
			} else if (cur[0] - cur[1] == king[0] - king[1]) {
				if (cur[0] < king[0] && cur[1] < king[1]) {
					if (cur[0] >= rr[4][0] && cur[1] >= rr[4][1]) {
						rr[4][0] = cur[0];
						rr[4][1] = cur[1];
					}
				} else if (cur[0] > king[0] && cur[1] > king[1]) {
					if (rr[5][0] == -1) {
						rr[5][0] = cur[0];
						rr[5][1] = cur[1];
						continue;
					}
					if (cur[0] <= rr[5][0] && cur[1] <= rr[5][1]) {
						rr[5][0] = cur[0];
						rr[5][1] = cur[1];
					}
				}
			} else if (cur[0] + cur[1] == king[0] + king[1]) {
				if (cur[0] > king[0] && cur[1] < king[1]) {
					if (rr[6][0] == -1) {
						rr[6][0] = cur[0];
						rr[6][1] = cur[1];
						continue;
					}
					if (cur[0] <= rr[6][0] && cur[1] >= rr[6][1]) {
						rr[6][0] = cur[0];
						rr[6][1] = cur[1];
					}
				} else if (cur[0] < king[0] && cur[1] > king[1]) {
					if (rr[7][0] == -1) {
						rr[7][0] = cur[0];
						rr[7][1] = cur[1];
						continue;
					}
					if (cur[0] >= rr[7][0] && cur[1] <= rr[7][1]) {
						rr[7][0] = cur[0];
						rr[7][1] = cur[1];
					}
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			if (rr[i][0] == -1) continue;
			res.add(Arrays.asList(rr[i][0], rr[i][1]));
		}
		
		return res;
	}
}
