package algorithms.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/sentence-similarity-ii/
 *
 * @author Yangxiao Wang on 2019-09-05
 * @see algorithms.hashtable.Sentence_Similarity
 */
public class Sentence_Similarity_II {
	
	public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
		int n = words1.length, m = words2.length;
		if (n != m)
			return false;
		if (n == 0)
			return true;
		Map<String, Integer> map = new HashMap<>();
		UnionFind unionFind = new UnionFind(2 * pairs.size());
		int cnt = 0;
		for (int i = 0; i < pairs.size(); i++) {
			List<String> mm = pairs.get(i);
			map.putIfAbsent(mm.get(0), cnt++);
			map.putIfAbsent(mm.get(1), cnt++);
			unionFind.union(map.get(mm.get(0)), map.get(mm.get(1)));
		}
		
		
		for (int i = 0; i < n; i++) {
			String s1 = words1[i], s2 = words2[i];
			if (s1.equals(s2))
				continue;
			if (!map.containsKey(s1) || !map.containsKey(s2))
				return false;
			if (unionFind.find(map.get(s1)) != unionFind.find(map.get(s2))) {
				return false;
			}
		}
		
		return true;
	}
	
	class UnionFind {
		int[] parent;
		
		public UnionFind(int n) {
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}
		
		private int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}
			return parent[x];
		}
		
		private void union(int x, int y) {
			int xset = find(x);
			int yset = find(y);
			parent[xset] = yset;
		}
	}
	
	/**
	 * This is a good use case for Union-Find, compare to Sentence Similarity I, here the similarity between words are
	 * transitive, so all the connected(similar) words should be group into an union represented by their ultimate
	 * parent(or family holder, you name it).
	 * The connections can be represented by an parent map Map<String, String> m, which record the direct parent-ship we
	 * learned in each pair, but not the ultimate-parent. To build it, go through the input pairs, for each pair<w1,
	 * w2>, use the recursive find() method to find the ultimate-parent for both word - parent1, parent2, if they are
	 * different, assign parent1 as parent of parent2(or the other way around), so that the to families are merged.
	 * The classic find(x) method will find the ultimate-parent of x. I modified it a little bit, make it do a little of
	 * extra initialization work - assign x itself as its parent when it is not initialize - so that we don't have to
	 * explicitly initialize the map at the beginning.
	 */
	class Solution {
		public boolean areSentencesSimilarTwo(String[] a, String[] b, String[][] pairs) {
			if (a.length != b.length)
				return false;
			Map<String, String> m = new HashMap<>();
			for (String[] p : pairs) {
				String parent1 = find(m, p[0]), parent2 = find(m, p[1]);
				if (!parent1.equals(parent2))
					m.put(parent1, parent2);
			}
			
			for (int i = 0; i < a.length; i++)
				if (!a[i].equals(b[i]) && !find(m, a[i]).equals(find(m, b[i])))
					return false;
			
			return true;
		}
		
		private String find(Map<String, String> m, String s) {
			if (!m.containsKey(s))
				m.put(s, s);
			return s.equals(m.get(s)) ? s : find(m, m.get(s));
		}
	}
	
	class Solution_Fast {
		class Node {
			Node parent;
			int size;
			
			public Node() {
				this.parent = this;
				this.size = 1;
			}
		}
		
		class UnionFind {
			Map<String, Node> map;
			
			public UnionFind() {
				this.map = new HashMap();
			}
			
			private Node root(Node a) {
				Node cur = a;
				while (cur.parent != cur) {
					cur.parent = cur.parent.parent;
					cur = cur.parent;
				}
				a.parent = cur;
				return cur;
			}
			
			public void union(String s1, String s2) {
				Node a = map.get(s1);
				if (a == null) {
					a = new Node();
					map.put(s1, a);
				}
				Node b = map.get(s2);
				if (b == null) {
					b = new Node();
					map.put(s2, b);
				}
				Node rootA = root(a);
				Node rootB = root(b);
				if (rootA == rootB)
					return;
				if (rootA.size < rootB.size) {
					rootA.parent = rootB;
					rootB.size += rootA.size;
				} else {
					rootB.parent = rootA;
					rootA.size += rootB.size;
				}
			}
			
			public boolean find(String s1, String s2) {
				if (s1.equals(s2))
					return true;
				Node a = map.get(s1);
				Node b = map.get(s2);
				if (a == null || b == null)
					return false;
				return root(a) == root(b);
			}
		}
		
		class Solution {
			public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
				if (words1.length != words2.length)
					return false;
				UnionFind uf = new UnionFind();
				for (List<String> pair : pairs) {
					uf.union(pair.get(0), pair.get(1));
				}
				for (int i = 0; i < words1.length; i++) {
					if (!uf.find(words1[i], words2[i]))
						return false;
				}
				return true;
			}
		}
	}
}
