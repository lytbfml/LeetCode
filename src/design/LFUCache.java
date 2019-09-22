package design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author Yangxiao Wang on 2019-07-29
 */
public class LFUCache {
	
	private Map<Integer, Integer> mapVal;
	private Map<Integer, Integer> mapFreqs;
	private Map<Integer, LinkedHashSet<Integer>> map;
	private int capacity;
	private int minFreq;
	
	public LFUCache(int capacity) {
		this.capacity = capacity;
		mapVal = new HashMap<>(capacity);
		mapFreqs = new HashMap<>(capacity);
		map = new HashMap<>(capacity);
		map.put(1, new LinkedHashSet<>());
		minFreq = 0;
	}
	
	/**
	 * Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
	 */
	public int get(int key) {
		if (mapVal.containsKey(key)) {
			int org = mapFreqs.get(key);
			mapFreqs.put(key, org + 1);
			map.get(org).remove(key);
			if (org == minFreq && map.get(org).size() == 0) minFreq++;
			if (!map.containsKey(org + 1)) map.put(org + 1, new LinkedHashSet<>());
			map.get(org + 1).add(key);
			return mapVal.get(key);
		}
		return -1;
	}
	
	/**
	 * Set or insert the value if the key is not already present.
	 * When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a
	 * new item.
	 * For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency),
	 * the least recently used key would be evicted.
	 */
	public void put(int key, int value) {
		if (capacity <= 0) return;
		if (mapVal.containsKey(key)) {
			mapVal.put(key, value);
			get(key);
			return;
		}
		
		if (mapVal.size() >= capacity) {
			int remove = map.get(minFreq).iterator().next();
			map.get(minFreq).remove(remove);
			mapVal.remove(remove);
		}
		
		mapVal.put(key, value);
		mapFreqs.put(key, 1);
		minFreq = 1;
		map.get(1).add(key);
	}
	
	// Two HashMap and One DoubleLinkedList
	class LFUCache_ {
		class Node {
			int key, val, cnt;
			Node prev, next;
			
			Node(int key, int val) {
				this.key = key;
				this.val = val;
				cnt = 1;
			}
		}
		
		class DLList {
			Node head, tail;
			int size;
			
			DLList() {
				head = new Node(0, 0);
				tail = new Node(0, 0);
				head.next = tail;
				tail.prev = head;
				size = 0;
			}
			
			void addToHead(Node node) {
				head.next.prev = node;
				node.next = head.next;
				head.next = node;
				node.prev = head;
				size++;
			}
			
			void remove(Node node) {
				node.prev.next = node.next;
				node.next.prev = node.prev;
				size--;
			}
		}
		
		int capacity;
		int min;
		Map<Integer, Node> nodeMap;
		Map<Integer, DLList> countMap;
		
		public LFUCache_(int capacity) {
			nodeMap = new HashMap<>();
			countMap = new HashMap<>();
			this.capacity = capacity;
			min = -1;
		}
		
		public int get(int key) {
			if (!nodeMap.containsKey(key))
				return -1;
			Node node = nodeMap.get(key);
			DLList oldList = countMap.get(node.cnt);
			oldList.remove(node);
			if (node.cnt == min && oldList.size == 0)
				min++;
			node.cnt++;
			countMap.putIfAbsent(node.cnt, new DLList());
			countMap.get(node.cnt).addToHead(node);
			return node.val;
		}
		
		public void put(int key, int value) {
			if (capacity <= 0)
				return;
			
			if (nodeMap.containsKey(key)) {
				Node node = nodeMap.get(key);
				node.val = value;
				get(key);
			} else {
				if (nodeMap.size() == capacity) {
					DLList lastList = countMap.get(min);
					Node evit = lastList.tail.prev;
					lastList.remove(evit);
					nodeMap.remove(evit.key);
				}
				Node node = new Node(key, value);
				nodeMap.put(key, node);
				min = 1;
				countMap.putIfAbsent(1, new DLList());
				countMap.get(1).addToHead(node);
			}
		}
	}
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */