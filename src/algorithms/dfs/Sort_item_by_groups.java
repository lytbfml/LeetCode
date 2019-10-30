package algorithms.dfs;

import java.util.*;

/**
 * @author Yangxiao Wang on 9/26/2019
 */
public class Sort_item_by_groups {
	
	class Solution1 {
		public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
			Map<Integer, Set<Integer>> groupG = new HashMap<>();
			Map<Integer, Set<Integer>> itemG = new HashMap<>();
			Map<Integer, Integer> groupD = new HashMap<>(); // in degree
			Map<Integer, Integer> itemD = new HashMap<>();
			
			for (int i = 0; i < n; i++) {
				itemG.putIfAbsent(i, new HashSet<>());
			}
			for (int i = 0; i < n; i++) {
				groupG.putIfAbsent(group[i], new HashSet<>());
			}
			
			for (int i = 0; i < n; i++) {
				List<Integer> cur = beforeItems.get(i);
				if (cur != null && cur.size() != 0) {
					for (int from : cur) {
						itemG.get(from).add(i);
						itemD.put(i, itemD.getOrDefault(i, 0) + 1);
						int fromGroup = group[from];
						int toGroup = group[i];
						if (fromGroup != toGroup && groupG.get(fromGroup).add(toGroup)) {
							groupD.put(toGroup, groupD.getOrDefault(toGroup, 0) + 1);
						}
					}
				}
			}
			
			List<Integer> groupRes = topologicalSort(groupG, groupD, groupG.size());
			List<Integer> itemRes = topologicalSort(itemG, itemD, n);
			
			if (itemRes.size() == 0 || groupRes.size() == 0) {
				return new int[0];
			}
			
			Map<Integer, List<Integer>> map = new HashMap<>();
			for (int i = 0; i < itemRes.size(); i++) {
				int g = group[itemRes.get(i)];
				map.putIfAbsent(g, new ArrayList<>());
				map.get(g).add(itemRes.get(i));
			}
			
			int[] res = new int[n];
			int i = 0;
			
			for (int j = 0; j < groupRes.size(); j++) {
				List<Integer> list = map.get(groupRes.get(j));
				for (int k = 0; k < list.size(); k++) {
					res[i++] = list.get(k);
				}
			}
			
			return res;
		}
		
		private List<Integer> topologicalSort(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> inDegree, int count) {
			List<Integer> res = new ArrayList<>();
			Queue<Integer> queue = new LinkedList<>();
			for (int key : graph.keySet()) {
				if (inDegree.getOrDefault(key, 0) == 0) {
					queue.add(key);
				}
			}
			
			while (!queue.isEmpty()) {
				int pop = queue.poll();
				count--;
				res.add(pop);
				for (int to : graph.get(pop)) {
					int ins = inDegree.get(to);
					inDegree.put(to, ins - 1);
					if (inDegree.get(to) == 0) {
						queue.add(to);
					}
				}
			}
			
			return count == 0 ? res : new ArrayList<>();
		}
	}
	
	class Solution2 {
		public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
			Map<Integer, List<Integer>> groupToItems = new HashMap<>();
			Map<Integer, Integer> groupIn = new HashMap<>();
			Map<Integer, Set<Integer>> groupAdj = new HashMap<>();
			Map<Integer, Integer> itemIn = new HashMap<>();
			Map<Integer, Set<Integer>> itemAdj = new HashMap<>();
			
			int minGroup = m;
			
			for (int i = 0; i < n; i++) {
				if (group[i] == -1) {
					group[i] = minGroup++;
				}
				List<Integer> l = groupToItems.getOrDefault(group[i], new ArrayList<>());
				l.add(i);
				groupToItems.put(group[i], l);
			}
			
			for (int i = 0; i < beforeItems.size(); i++) {
				int toGroup = group[i];
				for (int from : beforeItems.get(i)) {
					int fromGroup = group[from];
					if (toGroup != fromGroup) {
						Set<Integer> set = groupAdj.getOrDefault(fromGroup, new HashSet<>());
						if (!set.contains(toGroup)) {
							set.add(toGroup);
							groupAdj.put(fromGroup, set);
							groupIn.put(toGroup, groupIn.getOrDefault(toGroup, 0) + 1);
						}
					} else {
						Set<Integer> set = itemAdj.getOrDefault(from, new HashSet<>());
						set.add(i);
						itemAdj.put(from, set);
						itemIn.put(i, itemIn.getOrDefault(i, 0) + 1);
					}
				}
			}
			
			// Verify group order
			Queue<Integer> q = new LinkedList<>();
			List<Integer> groupAns = new ArrayList<>();
			
			for (int i = 0; i < minGroup; i++) {
				if (groupIn.getOrDefault(i, 0) == 0) {
					q.offer(i);
					groupAns.add(i);
				}
			}
			
			while (!q.isEmpty()) {
				int curGroup = q.poll();
				for (int next : groupAdj.getOrDefault(curGroup, new HashSet<>())) {
					groupIn.put(next, groupIn.get(next) - 1);
					if (groupIn.get(next) == 0) {
						q.offer(next);
						groupAns.add(next);
					}
				}
			}
			
			if (groupAns.size() != minGroup) return new int[0];
			
			
			// Verify item order
			List<Integer> ans = new ArrayList<>();
			
			for (int groupId : groupAns) {
				List<Integer> items = groupToItems.getOrDefault(groupId, new ArrayList());
				int num = 0;
				for (int item : items) {
					if (itemIn.getOrDefault(item, 0) == 0) {
						q.offer(item);
						ans.add(item);
						num++;
					}
				}
				
				while (!q.isEmpty()) {
					int curItem = q.poll();
					for (int next : itemAdj.getOrDefault(curItem, new HashSet<>())) {
						itemIn.put(next, itemIn.get(next) - 1);
						if (itemIn.get(next) == 0) {
							q.offer(next);
							ans.add(next);
							num++;
						}
					}
				}
				if (num != items.size()) return new int[0];
			}
			return ans.stream().mapToInt(i -> i).toArray();
		}
	}
}
