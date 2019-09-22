package algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Yangxiao on 4/20/2019.
 */
public class Two_City_Scheduling {
	
	public static void main(String[] args) {
		Two_City_Scheduling cs = new Two_City_Scheduling();
		int[][] x = {{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}};
		System.out.println(cs.twoCitySchedCost(x));
	}
	
	public int twoCitySchedCost(int[][] costs) {
		int r = costs.length;
		
		ArrayList<Integer> A = new ArrayList<>();
		ArrayList<Integer> B = new ArrayList<>();
		
		int costA = 0;
		int costB = 0;
		int countA = 0, countB = 0;
		for (int i = 0; i < r; i++) {
			if (costs[i][0] > costs[i][1]) {
				B.add(costs[i][0] - costs[i][1]);
				countB++;
				costB += costs[i][1];
			} else {
				A.add(costs[i][1] - costs[i][0]);
				costA += costs[i][0];
				countA++;
			}
		}
		int res = costA + costB;
		if (countA == countB) {
			return res;
		} else if (countA < countB) {
			int diff = (countB - countA) / 2;
			Collections.sort(B);
			for (int i = 0; i < diff; i++) {
				res += B.get(i);
			}
		} else {
			int diff = (countA - countB) / 2;
			Collections.sort(A);
			for (int i = 0; i < diff; i++) {
				res += A.get(i);
			}
		}
		return res;
	}
	
	public int twoCitySchedCost_Sort(int[][] costs) {
		int r = costs.length;
		Arrays.sort(costs, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return (a[1] - a[0]) - (b[1] - b[0]);
			}
		});
		int cost = 0;
		for (int i = 0; i < r / 2; i++) {
			cost += costs[i][1] + costs[r - i][0];
		}
		return cost;
	}
}
