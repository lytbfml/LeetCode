package algorithms.dynamic_programming;

import java.util.*;

/**
 * Bit manipulation
 * @author Yangxiao Wang on 7/13/2019
 * <p>
 * Set cover Vertex cover <=p set cover
 * <p>
 * Edge = skill vertex = individual subset find minimum vertices that cover all edges
 */
public class Smallest_Sufficient_Team {
	
	public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
		int n = req_skills.length;
		int k = people.size();
		Map<String, Integer> indexMap = new HashMap<>();
		Map<String, List<Integer>> ocurMap = new HashMap<>();
		int[] memOccurTimes = new int[n];
		for (int i = 0; i < n; i++) indexMap.put(req_skills[i], i);
		for (int i = 0; i < k; i++) {
			List<String> tmpl = people.get(i);
			for (String x : tmpl) {
				memOccurTimes[indexMap.get(x)]++;
				if (ocurMap.containsKey(x)) {
					ocurMap.get(x).add(i);
				} else {
					List<Integer> lis = new ArrayList<>();
					lis.add(i);
					ocurMap.put(x, lis);
				}
			}
		}
		
		List<Integer> res = new ArrayList<>();
		int curTime = 0;
		while (indexMap.size() > 0) {
			curTime++;
			List<Integer> curTimes = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if (memOccurTimes[i] == curTime) {
					List<Integer> indInPeople = ocurMap.get(req_skills[i]);
					curTimes.addAll(indInPeople);
					ocurMap.remove(req_skills[i]);
				}
			}
			
			TreeMap<Integer, Integer> treeMap = new TreeMap<>(Comparator.reverseOrder());
			
			for (int ind : curTimes) {
				int cnt = 0;
				for (String x : people.get(ind)) {
					if (indexMap.containsKey(x)) cnt++;
				}
				treeMap.put(cnt, ind);
			}
			for (Map.Entry<Integer, Integer> e : treeMap.entrySet()) {
			
			}
			
			for (int ind : curTimes) {
				res.add(ind);
				for (String x : people.get(ind)) {
					memOccurTimes[indexMap.get(x)] = 0;
					indexMap.remove(x);
					ocurMap.remove(x);
				}
			}
		}
		
		return res.stream().mapToInt(i -> i).toArray();
	}
	
	class Solution_bitmask {
		public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
			int n = req_skills.length, np = people.size();
			HashMap<String, Integer> map = new HashMap<>();
			for (int i = 0; i < n; ++i) map.put(req_skills[i], i);
			List<Integer>[] suff = new List[1 << n]; // 2^n
			suff[0] = new ArrayList<>();
			for (int i = 0; i < np; ++i) {
				int skill = 0;
				for (String s : people.get(i)) skill |= (1 << map.get(s));
				for (int prev = 0; prev < suff.length; ++prev) {
					if (suff[prev] == null) continue;
					int comb = prev | skill;
					if (suff[comb] == null || suff[prev].size() + 1 < suff[comb].size()) {
						suff[comb] = new ArrayList<>(suff[prev]);
						suff[comb].add(i);
					}
				}
			}
			List<Integer> res = suff[(1 << n) - 1];
			int[] arr = new int[res.size()];
			for (int i = 0; i < arr.length; ++i) arr[i] = res.get(i);
			return arr;
		}
	}
	
	class Solution_ {
		List<Integer> sol = new ArrayList<>();
		public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
			Map<String, Integer> idx = new HashMap<>();
			int n = 0;
			for (String s : req_skills) idx.put(s, n++);///skills are represented by 0, 1, 2....
			int[] pe = new int[people.size()];
			for (int i = 0; i < pe.length; i++) {
				for (String p : people.get(i)) {
					int skill = idx.get(p);
					pe[i] += 1 << skill;
				}
			} // each person is transferred to a number, of which the bits of 1 means the guy has the skill
			search(0, pe, new ArrayList<Integer>(), n);
			int[] ans = new int[sol.size()];
			for (int i = 0; i < sol.size(); i++) ans[i] = sol.get(i);
			return ans;
		}
		
		public void search(int cur, int[] pe, List<Integer> onesol, int n) {
			if (cur == (1<<n) - 1) {  // when all bits are 1, all skills are coverred
				if (sol.size() == 0 || onesol.size() < sol.size()) {
					sol = new ArrayList<>(onesol);
				}
				return;
			}
			if (sol.size() != 0 && onesol.size() >= sol.size()) return;    //pruning
			int zeroBit = 0;
			while (((cur>>zeroBit)&1) == 1) zeroBit++;
			for (int i = 0; i < pe.length; i++) {
				int per = pe[i];
				if (((per>>zeroBit)&1) == 1) {
					onesol.add(i); // when a person can cover a zero bit in the current number, we can add him
					search(cur|per, pe, onesol, n);
					onesol.remove(onesol.size() - 1);  //search in a backtracking way
				}
			}
		}
	}
}
