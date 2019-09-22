package algorithms.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangxiao on 4/21/2019.
 */
public class StreamChecker {
	
	public class TrieNode {
		boolean isend = false;
		TrieNode[] next = new TrieNode[26];
	}
	
	private TrieNode root = new TrieNode();
	private StringBuilder sb = new StringBuilder();
	
	public StreamChecker(String[] words) {
		for (String word : words) {
			insert(word);
		}
	}
	
	public boolean query(char letter) {
		sb.insert(0, letter);
		TrieNode p = root;
		for (char ch : sb.toString().toCharArray()) {
			p = p.next[ch - 'a'];
			if (p == null) {
				return false;
			}
			if (p.isend) {
				return true;
			}
		}
		return false;
	}
	
	public boolean query_2(char letter) {
		sb.append(letter);
		TrieNode node = root;
		for (int i = sb.length() - 1; i >= 0 && node != null; i--) {
			char c = sb.charAt(i);
			node = node.next[c - 'a'];
			if (node != null && node.isend) {
				return true;
			}
		}
		return false;
	}
	
	private void insert(String word) {
		TrieNode tmp = root;
		int len = word.length();
		for (int i = 0; i < len; i++) {
			char ch = word.charAt(word.length() - 1 - i);
			if (tmp.next[ch - 'a'] == null) {
				tmp.next[ch - 'a'] = new TrieNode();
			}
			tmp = tmp.next[ch - 'a'];
		}
		tmp.isend = true;
	}
	
	class Solution {
		class TrieNode {
			boolean is_word;
			TrieNode[] children;
			
			public TrieNode() {
				is_word = false;
				children = new TrieNode[26];
			}
		}
		
		
		class StreamChecker_Sol {
			TrieNode root;
			List<Character> stream;
			
			public StreamChecker_Sol(String[] words) {
				stream = new ArrayList<>();
				root = new TrieNode();
				for (String word : words) {
					int m = word.length();
					TrieNode cur = root;
					for (int i = m - 1; i >= 0; --i) {
						if (cur.children[word.charAt(i) - 'a'] == null) {
							cur.children[word.charAt(i) - 'a'] = new TrieNode();
						}
						cur = cur.children[word.charAt(i) - 'a'];
					}
					cur.is_word = true;
				}
			}
			
			public boolean query(char letter) {
				stream.add(letter);
				int m = stream.size();
				TrieNode cur = root;
				for (int i = m - 1; i >= 0; --i) {
					cur = cur.children[stream.get(i) - 'a'];
					if (cur == null)
						return false;
					else if (cur.is_word)
						return true;
				}
				return false;
			}
		}
	}
}
