package algorithms.greedy;

import java.util.*;

/**
 * @author Yangxiao Wang on 5/18/2019
 */
public class Last_Stone_Weight {
	
	
	public static void main(String[] args) {
		Last_Stone_Weight cs = new Last_Stone_Weight();
		int[] x = {2, 7, 4, 1, 8, 1};
		cs.lastStoneWeight(x);
	}
	
	public int lastStoneWeight(int[] stones) {
		int n = stones.length;
		
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			list.add(stones[i]);
		}
		Collections.sort(list);
		while (list.size() > 1) {
			int y = list.removeLast();
			int x = list.removeLast();
			if (x != y) {
				int index = Collections.binarySearch(list, y - x);
				if (index < 0) {
					index = -index - 1;
				}
				list.add(index, y - x);
			}
		}
		
		return list.size() == 0 ? 0 : list.get(0);
	}
	
	public int lastStoneWeight_queue(int[] stones) {
		int res = 0;
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> (b - a));
		for (int s : stones)
			priorityQueue.offer(s);
		while (priorityQueue.size() > 1) {
			int a = priorityQueue.poll();
			int b = priorityQueue.poll();
			if (a - b > 0)
				priorityQueue.offer(a - b);
		}
		return priorityQueue.size() == 0 ? 0 : priorityQueue.poll();
	}
}
