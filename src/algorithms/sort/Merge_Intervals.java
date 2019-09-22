package algorithms.sort;

import java.util.*;

/**
 * @author Yangxiao on 3/28/2019.
 */
public class Merge_Intervals {
	
	public static void main(String[] args) {
		Merge_Intervals cs = new Merge_Intervals();
		Interval x1 = new Interval(1, 4);
		Interval x2 = new Interval(2, 3);
		Interval x3 = new Interval(8, 10);
		Interval x4 = new Interval(5, 18);
		List<Interval> xs = new ArrayList<>(Arrays.asList(x1, x2, x3, x4));
		System.out.println(cs.merge(xs));
	}
	
	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> list = new ArrayList<>();
		if (intervals == null || intervals.size() == 0) {
			return list;
		}
		Collections.sort(intervals, Comparator.comparingInt(o -> o.start));
		PriorityQueue<Interval> pq = new PriorityQueue<>((o1, o2) -> o2.start - o1.start);
		for (Interval i : intervals) {
			if (!pq.isEmpty() && pq.peek().end >= i.start) {
				Interval temp = pq.poll();
				pq.add(new Interval(Math.min(temp.start, i.start), Math.max(temp.end, i.end)));
			} else {
				pq.add(i);
			}
		}
		List<Interval> re = new ArrayList<>(pq);
		return re;
	}
	
	public List<Interval> merge_2(List<Interval> intervals) {
		intervals.sort(Comparator.comparingInt(o -> o.start));
		LinkedList<Interval> list = new LinkedList<>();
		for (Interval i : intervals) {
			if (!list.isEmpty() && list.getLast().end >= i.start) {
				list.getLast().end = Math.max(list.getLast().end, i.end);
			} else {
				list.add(i);
			}
		}
		return list;
	}
	
	public List<Interval> merge_Sol1(List<Interval> intervals) {
		LinkedList<Interval> res = new LinkedList<>();
		if (intervals.size() <= 1) { return intervals; }
		// 对每个区间按照start由小到大排序
		Collections.sort(intervals, Comparator.comparingInt(i2 -> i2.start));
		int s = intervals.get(0).start, e = intervals.get(0).end;
		for (int i = 1; i < intervals.size(); i++) {
			// 后一个区间的s小于等于前一个区间的e时，证明两个区间有重叠  此时应该计算最新的e
			if (intervals.get(i).start <= e) {
				e = Math.max(intervals.get(i).end, e);
			} else {
				res.add(new Interval(s, e));
				s = intervals.get(i).start;
				e = intervals.get(i).end;
			}
		}
		res.add(new Interval(s, e));
		return res;
	}
	
	public List<Interval> merge_fast(List<Interval> intervals) {
		if (intervals == null || intervals.size() == 0 || intervals.size() == 1) {
			return intervals;
		}
		List<Interval> res = new ArrayList<>();
		int n = intervals.size();
		int[] starts = new int[n];
		int[] ends = new int[n];
		for (int i = 0; i < n; i++) {
			starts[i] = intervals.get(i).start;
			ends[i] = intervals.get(i).end;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		
		for (int i = 0, j = 0; i < n; i++) {
			if (i == n - 1 || starts[i + 1] > ends[i]) {
				res.add(new Interval(starts[j], ends[i]));
				j = i + 1;
			}
		}
		return res;
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
		
		public String toString() {
			return start + ", " + end;
		}
	}
}
