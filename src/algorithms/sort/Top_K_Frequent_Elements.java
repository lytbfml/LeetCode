package algorithms.sort;

import java.util.*;

/**
 * @author Yangxiao on 4/2/2019.
 */
public class Top_K_Frequent_Elements {
	
	public List<Integer> topKFrequent(int[] nums, int k) {
		int n = nums.length;
		List<Integer> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i : nums) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
		
		List<Map.Entry<Integer, Integer>> lists = new ArrayList<>(map.entrySet());
		lists.sort(Map.Entry.comparingByValue());
		int entrys = lists.size();
		for (int i = 0; i < k; i++) {
			list.add(lists.get(entrys - i - 1).getKey());
		}
		
		return list;
	}
	
	public List<Integer> topKFrequent_Bucket_Sort(int[] nums, int k) {
		
		List<Integer>[] bucket = new List[nums.length + 1];
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		
		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}
		
		for (int key : frequencyMap.keySet()) {
			int frequency = frequencyMap.get(key);
			if (bucket[frequency] == null) {
				bucket[frequency] = new ArrayList<>();
			}
			bucket[frequency].add(key);
		}
		
		List<Integer> res = new ArrayList<>();
		
		for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
			if (bucket[pos] != null) {
				res.addAll(bucket[pos]);
			}
		}
		return res;
	}
	
}
