package algorithms.dynamic_programming;

import java.util.*;

/**
 * @author Yangxiao on 10/30/2019.
 */
public class Maximum_Profit_in_Job_Scheduling {
	
	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int n = startTime.length;
		int[][] jobs = new int[n][3];
		for (int i = 0; i < n; i++) {
			jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
		}
		
		Arrays.sort(jobs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		TreeMap<Integer, Integer> dp = new TreeMap<>();
		dp.put(0, 0);
		for (int i = 0; i < n; i++) {
			int cur = dp.floorEntry(jobs[i][0]).getValue() + jobs[i][2];
			if (cur > dp.lastEntry().getValue())
				dp.put(jobs[i][1], cur);
		}
		
		return dp.lastEntry().getValue();
	}
	
	
	public int jobScheduling_arrList(int[] startTime, int[] endTime, int[] profit) {
		// sort by endTime
		int[][] items = new int[startTime.length][3];
		for (int i = 0; i < startTime.length; i++) {
			items[i] = new int[]{startTime[i], endTime[i], profit[i]};
		}
		Arrays.sort(items, (a1, a2) -> a1[1] - a2[1]);
		List<Integer> dpEndTime = new ArrayList<>();
		List<Integer> dpProfit = new ArrayList<>();
		// init value to avoid IndexOutBoundExp
		dpEndTime.add(0);
		dpProfit.add(0);
		for (int[] item : items) {
			int s = item[0], e = item[1], p = item[2];
			// find previous endTime index
			int prevIdx = Collections.binarySearch(dpEndTime, s + 1);
			if (prevIdx < 0) {
				prevIdx = -prevIdx - 1;
			}
			prevIdx--;
			int currProfit = dpProfit.get(prevIdx) + p, maxProfit = dpProfit.get(dpProfit.size() - 1);
			if (currProfit > maxProfit) {
				dpProfit.add(currProfit);
				dpEndTime.add(e);
			}
		}
		return dpProfit.get(dpProfit.size() - 1);
	}
}
