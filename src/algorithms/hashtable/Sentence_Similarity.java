package algorithms.hashtable;

import algorithms.unionfind.Sentence_Similarity_II;

import java.util.*;

/**
 * @author Yangxiao on 8/31/2019.
 *
 * @see Sentence_Similarity_II
 */
public class Sentence_Similarity {
	
	public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
		Map<String, Set<String>> map = new HashMap<>();
		
		for (int i = 0; i < pairs.size(); i++) {
			List<String> mm = pairs.get(i);
			map.putIfAbsent(mm.get(0), new HashSet<>());
			map.get(mm.get(0)).add(mm.get(1));
			
			map.putIfAbsent(mm.get(1), new HashSet<>());
			map.get(mm.get(1)).add(mm.get(0));
		}
		
		int n = words1.length, m = words2.length;
		if (n != m)
			return false;
		for (int i = 0; i < n; i++) {
			String s1 = words1[i];
			String s2 = words2[i];
			if (s1.equals(s2)) {
				continue;
			}
			if (!map.containsKey(s1)) {
				return false;
			} else {
				if (!map.get(s1).contains(s2)) {
					return false;
				}
			}
		}
		return true;
	}
}
