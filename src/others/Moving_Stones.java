package others;

import java.util.Arrays;

/**
 * @author Yangxiao Wang on 4/27/2019
 */
public class Moving_Stones {
	
	public static void main(String[] args) {
		Moving_Stones cs = new Moving_Stones();
		cs.numMovesStones(1, 2, 5);
		cs.numMovesStones(4, 3, 2);
		cs.numMovesStones(1, 4, 3);
	}
	
	public int[] numMovesStones(int a, int b, int c) {
		int[] x = {a, b, c};
		Arrays.sort(x);
		
		
		int max = x[1] - x[0] - 1 + x[2] - x[1] - 1;
		int min = 0;
		if (x[1] - x[0] == 2 || x[2] - x[1] == 2) {
			min = 1;
		} else {
			if (x[1] - x[0] > 2)
				min++;
			if (x[2] - x[1] > 2)
				min++;
		}
		
		return new int[]{min, max};
	}
}
