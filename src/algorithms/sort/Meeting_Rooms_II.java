package algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Yangxiao on 12/7/2018.
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), find the minimum number of conference rooms required.
 */
public class Meeting_Rooms_II {
	
	public static void main(String[] args) {
		Meeting_Rooms_II mr = new Meeting_Rooms_II();
		Meeting_Rooms_II.Interval x1 = new Meeting_Rooms_II.Interval(9, 10);
		Meeting_Rooms_II.Interval x2 = new Meeting_Rooms_II.Interval(4, 9);
		Meeting_Rooms_II.Interval x3 = new Meeting_Rooms_II.Interval(4, 17);
		Meeting_Rooms_II.Interval[] xs = {x1, x2, x3};
		System.out.println(mr.minMeetingRooms(xs));
	}
	
	
	public int minMeetingRooms_N(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		int n = intervals.length;
		
		int[] start = new int[n];
		int[] end = new int[n];
		for (int i = 0; i < n; i++) {
			start[i] = intervals[i].start;
			end[i] = intervals[i].end;
		}
		Arrays.sort(start);
		Arrays.sort(end);
		int count = 0, j = 0;
		for (int i = 0; i < n; i++) {
			if (start[i] < end[j]) {
				count++;
			} else {
				j++;
			}
		}
		
		return count;
	}
	
	public int minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		ArrayList<Integer> endLis = new ArrayList<>();
		Arrays.sort(intervals, Comparator.comparingInt(o -> o.start));
		endLis.add(intervals[0].end);
		for (int i = 0; i < intervals.length - 1; i++) {
			boolean set = false;
			for (int j = 0; j < endLis.size(); j++) {
				if (endLis.get(j) <= intervals[i + 1].start) {
					endLis.set(j, intervals[i + 1].end);
					set = true;
					break;
				}
			}
			if (!set) {
				endLis.add(intervals[i + 1].end);
			}
		}
		return endLis.size();
	}
	
	public int minMeetingRooms_Sol1(Interval[] intervals) {
		int N = intervals.length;
		int[] start = new int[N], end = new int[N];
		for (int i = 0; i < N; i++) {
			start[i] = intervals[i].start;
			end[i] = intervals[i].end;
		}
		
		Arrays.sort(start);
		Arrays.sort(end);
		
		int cnt = 0;
		int i = 0, j = 0;
		for (; i < N; i++) {
			if (start[i] < end[j]) {
				cnt++;
			} else {
				j++;
			}
		}
		return cnt;
	}
	
	public int minMeetingRooms_Sol2(Interval[] intervals) {
		Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
		PriorityQueue<Interval> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));
		for (Interval i : intervals) {
			if (!pq.isEmpty() && pq.peek().end <= i.start) {
				pq.poll();
			}
			pq.add(i);
		}
		return pq.size();
	}
	
	public static class Interval {
		int start;
		int end;
		
		Interval() {
			start = 0;
			end = 0;
		}
		
		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}
}
