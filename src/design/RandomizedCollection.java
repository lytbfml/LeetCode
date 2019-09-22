package design;

import java.util.*;

/**
 * @author Yangxiao Wang on 2019-06-27
 */
public class RandomizedCollection {
	
	Map<Integer, LinkedList<Integer>> map;
	List<Integer> list;
	
	/**
	 * Initialize your data structure here.
	 */
	public RandomizedCollection() {
		map = new HashMap<>();
		list = new ArrayList<>();
	}
	
	/**
	 * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
	 */
	public boolean insert(int val) {
		list.add(val);
		if (map.containsKey(val)) {
			map.get(val).add(list.size() - 1);
			return false;
		} else {
			map.put(val, new LinkedList<>(Arrays.asList(list.size() - 1)));
			return true;
		}
	}
	
	/**
	 * Removes a value from the collection. Returns true if the collection contained the specified element.
	 */
	public boolean remove(int val) {
		System.out.print(list + " --- ");
		if (!map.containsKey(val)) {
			return false;
		}
		
		int index = map.get(val).getLast();
		System.out.print("index = " + index);
		if (index < list.size() - 1) {
			int temp = list.get(list.size() - 1);
			list.set(index, temp);
			
			if (map.get(temp).size() > 1) {
				map.get(temp).removeLast();
				int secondLast = map.get(temp).getLast();
				if (index < secondLast) {
					map.get(temp).removeLast();
					map.get(temp).addLast(index);
					map.get(temp).addLast(secondLast);
				} else {
					map.get(temp).addLast(index);
				}
			} else {
				map.get(temp).removeLast();
				map.get(temp).add(index);
			}
		}
		
		if (map.get(val).size() > 1) {
			map.get(val).removeLast();
		} else {
			map.remove(val);
		}
		list.remove(list.size() - 1);
		System.out.println(list + ", " + val);
		return true;
	}
	
	/**
	 * Get a random element from the collection.
	 */
	public int getRandom() {
		int n = list.size();
		System.out.println(list.size());
		int m = (int) (Math.random() * n);
		return list.get(m);
	}
	
	public class RandomizedCollection_ga {
		ArrayList<Integer> lst;
		HashMap<Integer, Set<Integer>> map;
		java.util.Random rand = new java.util.Random();
		
		public RandomizedCollection_ga() {
			list = new ArrayList<Integer>();
			map = new HashMap<Integer, Set<Integer>>();
		}
		
		public boolean insert(int val) {
			if (!map.containsKey(val))
				map.put(val, new LinkedHashSet<Integer>());
			map.get(val).add(lst.size());
			lst.add(val);
			return map.get(val).size() == 1;
		}
		
		public boolean remove(int val) {
			if (!map.containsKey(val) || map.get(val).size() == 0)
				return false;
			int remove_idx = map.get(val).iterator().next();
			map.get(val).remove(remove_idx);
			int last = lst.get(lst.size() - 1);
			lst.set(remove_idx, last);
			map.get(last).add(remove_idx);
			map.get(last).remove(lst.size() - 1);
			
			lst.remove(lst.size() - 1);
			return true;
		}
		
		public int getRandom() {
			return lst.get(rand.nextInt(lst.size()));
		}
	}
}
