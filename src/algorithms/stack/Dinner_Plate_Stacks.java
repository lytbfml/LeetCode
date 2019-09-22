package algorithms.stack;

import java.util.*;

/**
 * @author Yangxiao Wang on 8/27/2019
 */
public class Dinner_Plate_Stacks {
	
	/**
	 * Primary idea is to maintain 2 pointers(indexes) to keep track of where to push or pop a element.
	 * And whenever push/pop is invoked, update the pointers accordingly.
	 */
	class DinnerPlates {
		
		Map<Integer, Stack<Integer>> map;
		int cap;
		int curr;
		int last;
		int count;
		
		public DinnerPlates(int capacity) {
			cap = capacity;
			curr = 0; //where to push element
			last = 0; //where to pop element
			count = 0; //number of elements
			map = new HashMap<>();
			map.put(curr, new Stack<>());
		}
		
		public void push(int val) {
			//do some preprocessing to update current index
			while (map.containsKey(curr) && map.get(curr).size() == cap) {
				curr++;
			}
			if (!map.containsKey(curr)) {
				map.put(curr, new Stack<>());
			}
			map.get(curr).push(val);
			last = Math.max(last, curr);
			count++;
		}
		
		public int pop() {
			if (count == 0) return -1;
			while (last >= 0 && map.get(last).isEmpty()) {
				last--;
			}
			count--;
			curr = Math.min(curr, last);
			return map.get(last).pop();
		}
		
		public int popAtStack(int index) {
			if (!map.containsKey(index) || map.get(index).isEmpty()) {
				return -1;
			}
			count--;
			curr = Math.min(curr, index);
			return map.get(index).pop();
		}
	}
	
	
	class DinnerPlates2 {
		int capacity;
		List<Stack<Integer>> stacks;
		TreeSet<Integer> pushable;
		TreeSet<Integer> popable;
		
		
		public DinnerPlates2(int cap) {
			capacity = cap;
			stacks = new ArrayList<>();
			pushable = new TreeSet<>();
			popable = new TreeSet<>();
		}
		
		public void push(int val) {
			if (pushable.isEmpty()) {
				pushable.add(stacks.size());
				stacks.add(new Stack<>());
			}
			int index = pushable.first();
			Stack<Integer> stack = stacks.get(index);
			stack.push(val);
			popable.add(index);
			if (stack.size() == capacity) {
				pushable.remove(index);
			}
		}
		
		public int pop() {
			if (popable.size() == 0) {
				return -1;
			}
			int index = popable.last();
			return popAtStack(index);
		}
		
		public int popAtStack(int index) {
			if (!popable.contains(index)) {
				return -1;
			}
			Stack<Integer> stack = stacks.get(index);
			int res = stack.pop();
			pushable.add(index);
			if (stack.size() == 0) {
				popable.remove(index);
			}
			return res;
		}
	}
	
	
	class DinnerPlates_Fast {
		private int unit;
		private ArrayList<Integer> deque;
		private PriorityQueue<Integer> pq;
		
		public DinnerPlates_Fast(int capacity) {
			unit = capacity;
			deque = new ArrayList<>();
			pq = new PriorityQueue<>();
			//System.out.println("null");
		}
		
		public void push(int val) {
			if (!pq.isEmpty()) {
				deque.set(pq.poll(), val);
			} else {
				deque.add(val);
			}
			//System.out.println("null");
		}
		
		public int pop() {
			
			if (deque.isEmpty()) {
				//System.out.println(-1);
				return -1;
			} else {
				while (deque.get(deque.size() - 1) == null) {
					pq.remove(deque.size() - 1);
					deque.remove(deque.size() - 1);
				}
				int res = deque.remove(deque.size() - 1);
				//System.out.println(res);
				return res;
			}
			
		}
		
		public int popAtStack(int index) {
			int end = (index + 1) * unit - 1;
			int start = index * unit;
			Integer res = null;
			for (int i = end; i >= start; i--) {
				if (deque.get(i) != null) {
					res = deque.get(i);
					deque.set(i, null);
					pq.add(i);
					break;
				}
			}
			if (res != null) {
				//System.out.println(res);
				return res;
			} else {
				//System.out.println(-1);
				return -1;
			}
			
			
		}
	}
}
