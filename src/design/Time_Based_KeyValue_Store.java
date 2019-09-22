package design;

import java.util.*;

/**
 * @author Yangxiao Wang on 2019-08-06
 */
public class Time_Based_KeyValue_Store {
	
	class TimeMap {
		
		Map<String, TreeMap<Integer, String>> map;
		
		/**
		 * Initialize your data structure here.
		 */
		public TimeMap() {
			map = new HashMap<>();
		}
		
		public void set(String key, String value, int timestamp) {
			if (!map.containsKey(key)) {
				map.put(key, new TreeMap<>());
			}
			map.get(key).put(timestamp, value);
		}
		
		public String get(String key, int timestamp) {
			String res = "";
			if (map.containsKey(key)) {
				if (map.get(key).containsKey(timestamp)) {
					res = map.get(key).get(timestamp);
				} else {
					Map.Entry<Integer, String> val = map.get(key).floorEntry(timestamp);
					res = val == null ? "" : val.getValue();
				}
			}
			return res;
		}
	}
	
	/**
	 * Your TimeMap object will be instantiated and called as such:
	 * TimeMap obj = new TimeMap();
	 * obj.set(key,value,timestamp);
	 * String param_2 = obj.get(key,timestamp);
	 */
	
	
	class TimeMap_binarySearch {
		
		Map<String, List<Data>> map;
		
		public TimeMap_binarySearch() {
			map = new HashMap<>();
		}
		
		public void set(String key, String value, int timestamp) {
			if (!map.containsKey(key)) map.put(key, new ArrayList<Data>());
			map.get(key).add(new Data(value, timestamp));
		}
		
		public String get(String key, int timestamp) {
			if (!map.containsKey(key)) return "";
			return binarySearch(map.get(key), timestamp);
		}
		
		protected String binarySearch(List<Data> list, int time) {
			int low = 0, high = list.size() - 1;
			while (low < high) {
				int mid = (low + high) >> 1;
				if (list.get(mid).time == time) return list.get(mid).val;
				if (list.get(mid).time < time) {
					if (list.get(mid + 1).time > time) return list.get(mid).val;
					low = mid + 1;
				} else high = mid - 1;
			}
			return list.get(low).time <= time ? list.get(low).val : "";
		}
		
		class Data {
			String val;
			int time;
			
			Data(String val, int time) {
				this.val = val;
				this.time = time;
			}
		}
	}
}
