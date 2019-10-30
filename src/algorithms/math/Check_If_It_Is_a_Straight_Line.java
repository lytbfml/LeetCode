package algorithms.math;

/**
 * @author Yangxiao Wang on 10/29/2019
 */
public class Check_If_It_Is_a_Straight_Line {
	
	public boolean checkStraightLine(int[][] coord) {
		int n = coord.length;
		if (n == 2) {
			return true;
		}
		
		if (coord[0][0] - coord[1][0] == 0) {
			for (int i = 2; i < coord.length; i++) {
				if (coord[i][0] != coord[0][0]) {
					return false;
				}
			}
		}
		
		int a = (coord[0][1] - coord[1][1]) / (coord[0][0] - coord[1][0]);
		int b = coord[0][1] - a * coord[0][0];
		for (int i = 2; i < coord.length; i++) {
			if (a * coord[i][0] + b != coord[i][1]) {
				return false;
			}
		}
		
		return true;
	}
	
	// For point (p, q) and (u, v):
	// (y - q) / (x - p) = (y - v) / (x - u)
	// then (x - u) * (y - q) = (x - p) * (y - v)
	// (A.x - C.x)*(A.y - C.y) == (C.x - B.x)*(C.y - B.y);
	public boolean checkStraightLine_CheckSlop(int[][] co) {
		int p = co[0][0], q = co[0][1], u = co[1][0], v = co[1][1];
		for (int[] c : co) {
			if ((c[0] - p) * (c[1] - v) != (c[0] - u) * (c[1] - q)) {
				return false;
			}
		}
		return true;
	}
	
}
