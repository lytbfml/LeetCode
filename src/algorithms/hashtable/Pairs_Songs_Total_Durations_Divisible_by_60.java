package algorithms.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * wc128
 *
 * @author Yangxiao on 9/19/2019.
 */
public class Pairs_Songs_Total_Durations_Divisible_by_60 {
	
	public static void main(String[] args) {
		Pairs_Songs_Total_Durations_Divisible_by_60 cs = new Pairs_Songs_Total_Durations_Divisible_by_60();
		System.out.println(cs.numPairsDivisibleBy60(new int[]{30, 20, 150, 100, 40}));
		System.out.println(cs.numPairsDivisibleBy60(new int[]{60, 60, 60}));
	}
	
	public int numPairsDivisibleBy60(int[] time) {
		int n = time.length;
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.computeIfAbsent(time[i] % 60, k -> new ArrayList<>()).add(time[i]);
		}
		int cnt = 0;
		for (int e : map.keySet()) {
			int need = 0;
			if (e != 0) {
				need = 60 - e;
			}
			if (!map.containsKey(need)) {
				continue;
			}
			int sizeS = map.get(e).size();
			int sizeN = map.get(need).size();
			if (e == 0 || e == 30) {
				cnt += sizeS * (sizeN - 1);
			} else {
				cnt += sizeN * sizeS;
			}
		}
		return cnt / 2;
	}
	
	/**
	 * t % 60 gets the remainder 0 ~ 59.
	 * We count the occurrence of each remainders in a array/hashmap c.
	 * we want to know that, for each t, how many x satisfy (t + x) % 60 = 0.
	 * <p>
	 * One idea is take x % 60 = 60 - t % 60, which is valid for the most cases.
	 * But if t % 60 = 0, x % 60 = 0 instead of 60.
	 * 60 - t % 60 will get a number in range 1 ~ 60.
	 * (60 - t % 60) % 60 can get number in range 0 ~ 59, which is what we want.
	 * <p>
	 * Another idea is that x % 60 = (600 - t) % 60.
	 * Not sure which one is more straight forward.
	 */
	public int numPairsDivisibleBy60_Sol(int[] time) {
		int c[] = new int[60], res = 0;
		for (int t : time) {
			res += c[(600 - t) % 60];
			c[t % 60] += 1;
		}
		return res;
	}
}
