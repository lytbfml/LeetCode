package algorithms.sort;

import java.util.Arrays;

/**
 * @author Yangxiao on 12/7/2018.
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), determine if a person could attend all meetings.
 */
public class Meeting_Rooms {
	
	
	public boolean canAttendMeetings(Interval[] intervals) {
		int n = intervals.length;
		
		for (int i = 0; i < n; i++) {
			Interval x = intervals[i];
			for (int j = i + 1; j < n; j++) {
				Interval y = intervals[j];
				if (x.start >= y.end || y.start >= x.end) {
					continue;
				}
				return false;
			}
		}
		return true;
	}
	
	public boolean canAttendMeetings_Sol1(Interval[] intervals) {
		Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
		for (int i = 0; i < intervals.length - 1; i++) {
			if (intervals[i].end > intervals[i + 1].start) { return false; }
		}
		return true;
	}
	
	public boolean canAttendMeetings_Sol2(Interval[] intervals) {
		int len = intervals.length;
		if (len == 0) {
			return true;
		}
		int[] begin = new int[len];
		int[] stop = new int[len];
		for (int i = 0; i < len; i++) {
			begin[i] = intervals[i].start;
			stop[i] = intervals[i].end;
		}
		Arrays.sort(begin);
		Arrays.sort(stop);
		int endT = 0;
		for (int i = 1; i < len; i++) {
			if (begin[i] < stop[i - 1]) {
				return false;
			}
		}
		return true;
	}
	
	class Solution {
		public boolean canAttendMeetings(Interval[] intervals) {
			if (intervals.length == 0) { return true; }
			
			// sort according to the starting value
			quicksort(intervals, 0, intervals.length - 1);
			for (int i = 1; i < intervals.length; ++i) {
				if (intervals[i].start < intervals[i - 1].end) {
					return false;
				}
			}
			return true;
		}
		
		private void quicksort(Interval[] arr, int left, int right) {
			int index = partition(arr, left, right);
			if (left < index - 1) {
				quicksort(arr, left, index - 1);
			}
			if (index < right) {
				quicksort(arr, index, right);
			}
		}
		
		private int partition(Interval[] arr, int left, int right) {
			int pivot = arr[(left + right) / 2].start;
			while (left <= right) {
				while (arr[left].start < pivot)
					++left;
				
				while (arr[right].start > pivot)
					--right;
				
				if (left <= right) {
					swap(arr, left, right);
					++left;
					--right;
				}
			}
			return left;
		}
		
		private void swap(Interval[] arr, int left, int right) {
			Interval tmp = arr[left];
			arr[left] = arr[right];
			arr[right] = tmp;
		}
	}
	
	public static class Interval {
		int start;
		int end;
		
		Interval() { start = 0; end = 0; }
		
		Interval(int s, int e) { start = s; end = e; }
	}
	
	public static void main(String[] args) {
		Meeting_Rooms mr = new Meeting_Rooms();
		Interval x1 = new Interval(2, 8);
		Interval x2 = new Interval(2, 4);
		Interval[] xs = {x1, x2};
		System.out.println(mr.canAttendMeetings(xs));
	}
}
