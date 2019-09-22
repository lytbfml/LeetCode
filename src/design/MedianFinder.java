package design;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Yangxiao Wang on 2019-07-09
 */
class MedianFinder {
	
	PriorityQueue<Integer> pLow;
	PriorityQueue<Integer> pMax;
	
	/**
	 * initialize your data structure here.
	 */
	public MedianFinder() {
		pLow = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		// pLow = new PriorityQueue<>(Collections.reverseOrder());
		pMax = new PriorityQueue<>();
	}
	
	public void addNum(int num) {
		pLow.offer(num);
		pMax.offer(pLow.poll());
		if (pLow.size() < pMax.size()) {
			pLow.offer(pMax.poll());
		}
	}
	
	public double findMedian() {
		if (pLow.size() > pMax.size()) {
			return (double) pLow.peek();
		} else {
			return (pLow.peek() + pMax.peek()) * 0.5;
		}
	}
}
