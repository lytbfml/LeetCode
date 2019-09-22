package algorithms.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Yangxiao Wang on 2019-07-03
 */
public class Implement_Magic_Dictionary {
	
	
	class MagicDictionary {
		
		Map<Integer, Set<String>> map;
		
		/**
		 * Initialize your data structure here.
		 */
		public MagicDictionary() {
			map = new HashMap<>();
		}
		
		/**
		 * Build a dictionary through a list of words
		 */
		public void buildDict(String[] dict) {
			for (String x : dict) {
				int length = x.length();
				if (!map.containsKey(length)) {
					map.put(length, new HashSet<>());
				}
				map.get(length).add(x);
			}
		}
		
		/**
		 * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
		 */
		public boolean search(String word) {
			if (!map.containsKey(word.length())) return false;
			int n = word.length();
			Set<String> set = map.get(n);
			for (int i = 0; i < n; i++) {
				for (char j = 'a'; j <= 'z'; j++) {
					if (word.charAt(i) == j) continue;
					if (set.contains(word.substring(0, i) + j + word.substring(i + 1))) return true;
				}
			}
			return false;
		}
		
		public boolean search_2(String word) {
			int n = word.length();
			if (!map.containsKey(n)) return false;
			Set<String> set = map.get(n);
			for (String s : set) {
				int diff = 0;
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) != word.charAt(i)) diff++;
				}
				if (diff == 1) return true;
			}
			return false;
		}
	}
	
	class MagicDictionary_trie {
		
		
		class TrieNode {
			TrieNode[] children = new TrieNode[26];
			boolean isWord;
			
			public TrieNode() {
			}
		}
		
		TrieNode root;
		
		public MagicDictionary_trie() {
			root = new TrieNode();
		}
		
		public void buildDict(String[] dict) {
			for (String x : dict) {
				TrieNode node = root;
				for (char c : x.toCharArray()) {
					if (node.children[c - 'a'] == null) node.children[c - 'a'] = new TrieNode();
					node = node.children[c - 'a'];
				}
				node.isWord = true;
			}
		}
		
		public boolean search(String word) {
			int n = word.length();
			TrieNode node = root;
			for (int i = 0; i < n; i++) {
				for (char j = 'a'; j <= 'z'; j++) {
					if (word.charAt(i) == j) continue;
					if (help(word.substring(0, i) + j + word.substring(i + 1), root)) return true;
				}
			}
			
			return false;
		}
		
		public boolean help(String t, TrieNode node) {
			for (char c : t.toCharArray()) {
				if (node.children[c - 'a'] == null) return false;
				node = node.children[c - 'a'];
			}
			
			return node.isWord;
		}
	}
	
	/**
	 * Your MagicDictionary object will be instantiated and called as such:
	 * MagicDictionary obj = new MagicDictionary();
	 * obj.buildDict(dict);
	 * boolean param_2 = obj.search(word);
	 */
}
