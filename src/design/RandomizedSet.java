package design;

import java.util.*;

/**
 * @author Yangxiao Wang on 6/26/2019
 */
public class RandomizedSet {
	
	Map<Integer, Integer> map;
	List<Integer> list;
	
	/**
	 * Initialize your data structure here.
	 */
	public RandomizedSet() {
		map = new HashMap<>();
		list = new ArrayList<>();
	}
	
	/**
	 * Inserts a value to the set. Returns true if the set did not already contain the specified element.
	 */
	public boolean insert(int val) {
		if (map.containsKey(val)) {
			return false;
		}
		list.add(val);
		map.put(val, list.size() - 1);
		return true;
	}
	
	/**
	 * Removes a value from the set. Returns true if the set contained the specified element.
	 */
	public boolean remove(int val) {
		if (!map.containsKey(val)) {
			return false;
		}
		int index = map.get(val);
		int tempVal = list.get(list.size() - 1);
		list.set(index, tempVal);
		list.remove(list.size() - 1);
		map.put(tempVal, index);
		map.remove(val);
		return true;
	}
	
	/**
	 * Get a random element from the set.
	 */
	public int getRandom() {
		int max = list.size();
		int min = 0;
		int ind = (int)(Math.random() * (max - min) + min);
		return list.get(ind);
	}
}
