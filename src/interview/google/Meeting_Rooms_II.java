package interview.google;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Yangxiao on 12/7/2018.
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), find the minimum number of conference rooms required.
 */
public class Meeting_Rooms_II {
	
	public static void main(String[] args) {
		Meeting_Rooms_II mr = new Meeting_Rooms_II();
		int[] S = {9, 4, 4};
		int[] E = {10, 9, 17};
		
		int[] S2 = {1, 2, 6, 5, 3};
		int[] E2 = {5, 5, 7, 6, 8};
		
		System.out.println(mr.minMeetingRooms(S, E));
		System.out.println(mr.minMeetingRooms_Sol1(S, E));
		
		System.out.println(mr.minMeetingRooms(S2, E2));
		System.out.println(mr.minMeetingRooms_Sol1(S2, E2));
	}
	
	public int minMeetingRooms(int[] S, int[] E) {
		if (S == null || S.length == 0 || E == null || E.length == 0) {
			return 0;
		}
		ArrayList<Integer> endLis = new ArrayList<>();
		
		Arrays.sort(S);
		Arrays.sort(E);
		
		endLis.add(E[0]);
		for (int i = 0; i < S.length - 1; i++) {
			boolean set = false;
			for (int j = 0; j < endLis.size(); j++) {
				if (endLis.get(j) <= S[i + 1]) {
					endLis.set(j, E[i + 1]);
					set = true;
					break;
				}
			}
			if (!set) {
				endLis.add(E[i + 1]);
			}
		}
		return endLis.size();
	}
	
	public int minMeetingRooms_Sol1(int[] S, int[] E) {
		if (S == null || S.length == 0 || E == null || E.length == 0) {
			return 0;
		}
		int n = S.length;
		Arrays.sort(S);
		Arrays.sort(E);
		
		int count = 0, j = 0;
		for (int i = 0; i < n; i++) {
			if (S[i] < E[j]) {
				count++;
			} else {
				j++;
			}
		}
		return count;
	}
	
	// public int minMeetingRooms_Sol2(int[] S, int[] E) {
	// 	Arrays.sort(intervals, (a, b) -> (a.start - b.start));
	// 	PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> (a.end - b.end));
	// 	for (Interval i : intervals) {
	// 		if (!pq.isEmpty() && pq.peek().end <= i.start) {
	// 			pq.poll();
	// 		}
	// 		pq.add(i);
	// 	}
	// 	return pq.size();
	// }
	
}
