package algorithms.sort;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author Yangxiao Wang on 6/26/2019
 */
public class CarFleet {
	
	public int carFleet1(int target, int[] pos, int[] speed) {
		if (pos == null || speed == null || pos.length == 0) {
			return 0;
		}
		
		int n = pos.length;
		TreeMap<Integer, Double> map = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			map.put(-pos[i], (double) (target - pos[i]) / speed[i]);
		}
		Double max = -1.0;
		int res = 0;
		for (Double x : map.values()) {
			if (x > max) {
				res++;
				max = x;
			}
		}
		
		return res;
	}
	
	
	public int carFleet(int target, int[] pos, int[] speed) {
		int N = pos.length, res = 0;
		double[][] cars = new double[N][2];
		for (int i = 0; i < N; ++i)
			cars[i] = new double[]{pos[i], (double) (target - pos[i]) / speed[i]};
		Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));
		double cur = 0;
		for (int i = N - 1; i >= 0; --i) {
			if (cars[i][1] > cur) {
				cur = cars[i][1];
				res++;
			}
		}
		return res;
	}
	
	public int carFleet_Smart(int target, int[] pos, int[] speed) {
		TreeMap<Integer, Double> m = new TreeMap<>();
		for (int i = 0; i < pos.length; ++i)
			m.put(-pos[i], (double) (target - pos[i]) / speed[i]);
		int res = 0;
		double cur = 0;
		for (double time : m.values()) {
			if (time > cur) {
				cur = time;
				res++;
			}
		}
		return res;
	}
	
	public int carFleet_withoutSort(int target, int[] position, int[] speed) {
		int fleets = 0;
		double max = -1;
		
		double[] distribution = new double[target + 1];
		
		Arrays.fill(distribution, -1);
		
		for (int i = 0; i < position.length; i++) {
			distribution[position[i]] = (double) (target - position[i]) / speed[i];
		}
		
		for (int i = distribution.length - 1; i >= 0; i--) {
			if (distribution[i] > max) {
				max = distribution[i];
				fleets++;
			}
		}
		
		return fleets;
	}
}
