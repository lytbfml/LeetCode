package algorithms.math;

/**
 * @author Yangxiao Wang on 5/11/2019
 */
public class Robot_Bounded_In_Circle {
	
	public static void main(String[] args) {
		Robot_Bounded_In_Circle cs = new Robot_Bounded_In_Circle();
		cs.isRobotBounded("GL");
	}
	
	public boolean isRobotBounded(String instructions) {
		int n = instructions.length();
		int x = 0, y = 0;
		int dir = 0;
		for (int k = 0; k < n * 4; k++) {
			for (int i = 0; i < n; i++) {
				char cur = instructions.charAt(i);
				if (cur == 'G') {
					if (dir == 0) {
						y++;
					} else if (dir == 1) {
						x++;
					} else if (dir == 2) {
						y--;
					} else {
						x--;
					}
				} else if (cur == 'L') {
					dir -= 1;
					if (dir < 0) {
						dir = 3;
					}
				} else {
					dir += 1;
					if (dir > 3) {
						dir = 0;
					}
				}
			}
			if (x == 0 && y == 0) {
				return true;
			}
		}
		
		return false;
	}
	
	
	// if chopper return to the origin, he's in an obvious circle.
	// if chopper finishes with face not towards north,
	// it will get back to the initial status in another one or three sequences.
	public boolean isRobotBounded_Sol(String instructions) {
		int n = instructions.length();
		int x = 0, y = 0;
		int dir = 0;
		for (int i = 0; i < n; i++) {
			char cur = instructions.charAt(i);
			if (cur == 'G') {
				if (dir == 0) {
					y++;
				} else if (dir == 1) {
					x++;
				} else if (dir == 2) {
					y--;
				} else {
					x--;
				}
			} else if (cur == 'L') {
				dir -= 1;
				if (dir < 0) {
					dir = 3;
				}
			} else {
				dir += 1;
				if (dir > 3) {
					dir = 0;
				}
			}
		}
		
		return x == 0 && y == 0 || dir != 0;
	}
	
	public boolean isRobotBounded2(String ins) {
		int x = 0, y = 0, i = 0, d[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		for (int j = 0; j < ins.length(); ++j)
			if (ins.charAt(j) == 'R')
				i = (i + 1) % 4;
			else if (ins.charAt(j) == 'L')
				i = (i + 3) % 4;
			else {
				x += d[i][0];
				y += d[i][1];
			}
		return x == 0 && y == 0 || i > 0;
	}
}
