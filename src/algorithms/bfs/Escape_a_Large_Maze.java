package algorithms.bfs;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yangxiao Wang on 4/28/2019
 */
public class Escape_a_Large_Maze {
	
	public static void main(String[] args) {
		int[][] inp = {{100059, 100063}, {100060, 100064}, {100061, 100065}, {100062, 100066}, {100063, 100067},
		               {100064, 100068}, {100065, 100069}, {100066, 100070}, {100067, 100071}, {100068, 100072},
		               {100069, 100073}, {100070, 100074}, {100071, 100075}, {100072, 100076}, {100073, 100077},
		               {100074, 100078}, {100075, 100079}, {100076, 100080}, {100077, 100081}, {100078, 100082},
		               {100079, 100083}, {100080, 100082}, {100081, 100081}, {100082, 100080}, {100083, 100079},
		               {100084, 100078}, {100085, 100077}, {100086, 100076}, {100087, 100075}, {100088, 100074},
		               {100089, 100073}, {100090, 100072}, {100091, 100071}, {100092, 100070}, {100093, 100069},
		               {100094, 100068}, {100095, 100067}, {100096, 100066}, {100097, 100065}, {100098, 100064},
		               {100099, 100063}, {100098, 100062}, {100097, 100061}, {100096, 100060}, {100095, 100059},
		               {100094, 100058}, {100093, 100057}, {100092, 100056}, {100091, 100055}, {100090, 100054},
		               {100089, 100053}, {100088, 100052}, {100087, 100051}, {100086, 100050}, {100085, 100049},
		               {100084, 100048}, {100083, 100047}, {100082, 100046}, {100081, 100045}, {100080, 100044},
		               {100079, 100043}, {100078, 100044}, {100077, 100045}, {100076, 100046}, {100075, 100047},
		               {100074, 100048}, {100073, 100049}, {100072, 100050}, {100071, 100051}, {100070, 100052},
		               {100069, 100053}, {100068, 100054}, {100067, 100055}, {100066, 100056}, {100065, 100057},
		               {100064, 100058}, {100063, 100059}, {100062, 100060}, {100061, 100061}, {100060, 100062}
		};
		
		Escape_a_Large_Maze cs = new Escape_a_Large_Maze();
		int[] source = {100079, 100063};
		int[] sa = {999948, 999967};
		cs.isEscapePossible(inp, source, sa);
	}
	
	int max = 19900;
	
	public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
		if (blocked.length == 0) {
			return true;
		}
		
		Set<String> set = new HashSet<>();
		for (int i = 0; i < blocked.length; i++) {
			set.add(blocked[i][0] + "," + blocked[i][1]);
		}
		return helper(set, source[0], source[1], target[0], target[1]) &&
				helper(set, target[0], target[1], source[0], source[1]);
	}
	
	private boolean helper(Set<String> set, int x, int y, int targetX, int targetY) {
		Set<String> visited = new HashSet<>();
		return dfs(set, visited, x, y, targetX, targetY);
	}
	
	private boolean dfs(Set<String> set, Set<String> visited, int x, int y, int targetX, int targetY) {
		String ind = x + "," + y;
		if (x < 0 || y < 0 || x >= 1000000 || y >= 1000000 || set.contains(x + "," + y) ||
				visited.contains(x + "," + y))
			return false;
		
		if (visited.size() > max || (x == targetX && y == targetY))
			return true;
		
		visited.add(x + "," + y);
		return dfs(set, visited, x - 1, y, targetX, targetY) ||
				dfs(set, visited, x + 1, y, targetX, targetY) ||
				dfs(set, visited, x, y - 1, targetX, targetY) ||
				dfs(set, visited, x, y + 1, targetX, targetY);
	}
	
	class Solution {
		int max = 19900;
		
		public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
			if (blocked.length == 0) {
				return true;
			}
			
			Set<String> set = new HashSet<>();
			for (int i = 0; i < blocked.length; i++) {
				set.add(blocked[i][0] + "," + blocked[i][1]);
			}
			return helper(set, source[0], source[1]) && helper(set, target[0], target[1]);
		}
		
		private boolean helper(Set<String> set, int x, int y) {
			Set<String> visited = new HashSet<>();
			dfs(set, visited, x, y);
			return visited.size() >= max;
		}
		
		private void dfs(Set<String> set, Set<String> visited, int x, int y) {
			if (x < 0 || y < 0 || x >= 1000000 || y >= 1000000 || set.contains(x + "," + y) || visited.size() > max ||
					visited.contains(x + "," + y)) {
				return;
			}
			
			visited.add(x + "," + y);
			dfs(set, visited, x - 1, y);
			dfs(set, visited, x + 1, y);
			dfs(set, visited, x, y - 1);
			dfs(set, visited, x, y + 1);
		}
	}
}

