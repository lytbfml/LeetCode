package design;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yangxiao Wang on 2019-07-03
 */
public class LRU_Cache {

	class LRUCache {
		int size;
		int cnt;
		Map<Integer, Node> map;
		Node root;
		Node last;

		public LRUCache(int capacity) {
			size = capacity;
			cnt = 0;
			map = new HashMap<>();
			root = new Node(0, 0);
			last = new Node(0, 0);
			root.next = last;
			last.prev = root;
		}

		public int get(int key) {
			if (!map.containsKey(key))
				return -1;
			Node node = map.get(key);
			node.prev.next = node.next;
			node.next.prev = node.prev;
			insertToLast(node);
			return node.val;
		}

		public void put(int key, int value) {
			if (map.containsKey(key)) {
				Node node = map.remove(key);
				node.prev.next = node.next;
				node.next.prev = node.prev;
				cnt--;
			}

			Node inserted = new Node(key, value);
			insertToLast(inserted);
			if (cnt < size) {
				cnt++;
			} else {
				map.remove(root.next.key);
				root.next = root.next.next;
				root.next.prev = root;
			}
			map.put(key, inserted);
		}

		private void insertToLast(Node inserted) {
			last.prev.next = inserted;
			inserted.prev = last.prev;
			inserted.next = last;
			last.prev = inserted;
		}

		class Node {
			Node prev;
			Node next;
			int key;
			int val;

			Node(int key, int val) {
				this.key = key;
				this.val = val;
			}
		}
	}

	class LRUCache2 extends LinkedHashMap<Integer, Integer> {
		private int capacity;

		public LRUCache2(int capacity) {
			super(capacity, 0.75f, true);
			this.capacity = capacity;
		}

		public int get(int key) {
			return getOrDefault(key, -1);
		}

		public void put(int key, int value) {
			super.put(key, value);
		}

		@Override
		protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
			return size() > capacity;
		}
	}

	class LRUCache3 {
		private LinkedHashMap<Integer, Integer> map;
		private int SIZE;

		public LRUCache3(int capacity) {
			map = new LinkedHashMap<>();
			SIZE = capacity;
		}

		public int get(int key) {
			if (map.containsKey(key)) {
				int value = map.remove(key);
				map.put(key, value);
				return value;
			}
			return -1;
		}

		public void put(int key, int value) {
			if (map.containsKey(key)) {
				map.remove(key);
			} else if (map.size() + 1 > SIZE) {
				map.remove(map.keySet().iterator().next());
			}
			map.put(key, value);
		}
	}
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */