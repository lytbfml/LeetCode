package algorithms.hashtable;

import java.util.*;

/**
 * @author Yangxiao Wang on 2019-08-15
 * <p>
 * Hash table, Trie
 */
public class Palindrome_Pairs {
	
	public static void main(String[] args) {
		Solution_Trie cs = new Solution_Trie();
		System.out.println(cs.palindromePairs(new String[]{"bat", "tab", "cat"}));
		System.out.println(cs.palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"}));
		cs.gg("", cs.root);
	}
	
	class Solution_bruteForce {
		
		public List<List<Integer>> palindromePairs(String[] words) {
			List<List<Integer>> res = new ArrayList<>();
			int n = words.length;
			for (int i = 0; i < n; i++) {
				for (int j = i; j < n; j++) {
					if (i == j) continue;
					if (isPalindrome(words[i] + words[j])) res.add(new ArrayList<>(Arrays.asList(i, j)));
					if (isPalindrome(words[j] + words[i])) res.add(new ArrayList<>(Arrays.asList(j, i)));
				}
			}
			return res;
		}
		
		private boolean isPalindrome(String s) {
			int i = 0, j = s.length() - 1;
			while (i < j) {
				if (s.charAt(i++) != s.charAt(j--)) return false;
			}
			return true;
		}
	}
	
	static class Solution_Trie {
		
		Trie root = new Trie();
		
		public List<List<Integer>> palindromePairs(String[] words) {
			List<List<Integer>> res = new ArrayList<>();
			buildTrie(words);
			for (int i = 0; i < words.length; i++) {
				findPair(words[i], i, res);
			}
			return res;
		}
		
		public void gg(String s, Trie node) {
			if (node.index != -1) {
				System.out.println(s);
			}
			for (int i = 0; i < 26; i++) {
				if (node.children[i] != null) {
					gg(s + (char) (i + 'a'), node.children[i]);
				}
			}
		}
		
		private void buildTrie(String[] words) {
			for (int i = 0; i < words.length; i++) insert(words[i], i);
		}
		
		private void insert(String s, int index) {
			Trie node = root;
			int n = s.length();
			for (int i = n - 1; i >= 0; i--) {
				int j = s.charAt(i) - 'a';
				if (node.children[j] == null) {
					node.children[j] = new Trie();
				}
				
				if (isPalindrome(s, 0, i)) {
					node.list.add(index);
				}
				
				node = node.children[j];
			}
			
			node.list.add(index);
			node.index = index;
		}
		
		
		private void findPair(String s, int index, List<List<Integer>> res) {
			int n = s.length();
			Trie node = root;
			for (int j = 0; j < n; j++) {
				if (node.index >= 0 && node.index != index && isPalindrome(s, j, n - 1)) {
					res.add(Arrays.asList(index, node.index));
				}
				node = node.children[s.charAt(j) - 'a'];
				if (node == null) return;
			}
			
			for (int j : node.list) {
				if (index == j) continue;
				res.add(Arrays.asList(index, j));
			}
		}
		
		
		class Trie {
			Trie[] children = new Trie[26];
			List<Integer> list = new ArrayList<>();
			int index = -1;
			
			public Trie() { }
			
		}
		
		private boolean isPalindrome(String s, int i, int j) {
			while (i <= j) {
				if (s.charAt(i++) != s.charAt(j--)) return false;
			}
			return true;
		}
	}
	
	class Solution_map {
		
		public List<List<Integer>> palindromePairs(String[] words) {
			List<List<Integer>> res = new ArrayList<>();
			if (words == null || words.length == 0) return res;
			Map<String, Integer> map = new HashMap<>();
			int n = words.length;
			for (int i = 0; i < n; i++) map.put(words[i], i);
			for (int i = 0; i < n; i++) {
				int l = 0, r = 0;
				// first check any string m: (0-r) + m is palindrome and (r - end) is palindrome
				// then check any string m: m + (l-end) is palindrome and (0-l) is palindrome
				while (l <= r) {
					String cur = words[i].substring(l, r);
					Integer j = map.get(new StringBuilder(cur).reverse().toString());
					if (j != null && i != j &&
							isPalindrome(words[i], l == 0 ? r : 0, l == 0 ? words[i].length() - 1 : l - 1)) {
						res.add(Arrays.asList(l == 0 ? new Integer[]{i, j} : new Integer[]{j, i}));
					}
					if (r < words[i].length()) r++;
					else l++;
				}
			}
			
			return res;
		}
		
		private boolean isPalindrome(String s, int i, int j) {
			while (i < j) {
				if (s.charAt(i++) != s.charAt(j--)) return false;
			}
			return true;
		}
	}
	
	class Solution_map2 {
		
		public List<List<Integer>> palindromePairs(String[] words) {
			List<List<Integer>> ret = new ArrayList<>();
			if (words == null || words.length < 2) return ret;
			Map<String, Integer> map = new HashMap<>();
			for (int i = 0; i < words.length; i++) map.put(words[i], i);
			for (int i = 0; i < words.length; i++) {
				// System.out.println(words[i]);
				for (int j = 0; j <= words[i].length(); j++) {
					// notice it should be "j <= words[i].length()"
					String str1 = words[i].substring(0, j);
					String str2 = words[i].substring(j);
					if (isPalindrome(str1)) {
						String str2rvs = new StringBuilder(str2).reverse().toString();
						if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
							List<Integer> list = new ArrayList<>();
							list.add(map.get(str2rvs));
							list.add(i);
							ret.add(list);
							// System.out.printf("isPal(str1): %s\n", list.toString());
						}
					}
					if (isPalindrome(str2)) {
						String str1rvs = new StringBuilder(str1).reverse().toString();
						// check "str.length() != 0" to avoid duplicates
						if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length() != 0) {
							List<Integer> list = new ArrayList<>();
							list.add(i);
							list.add(map.get(str1rvs));
							ret.add(list);
							// System.out.printf("isPal(str2): %s\n", list.toString());
						}
					}
				}
			}
			return ret;
		}
		
		private boolean isPalindrome(String str) {
			int left = 0;
			int right = str.length() - 1;
			while (left <= right) {
				if (str.charAt(left++) != str.charAt(right--)) return false;
			}
			return true;
		}
	}
	
}
