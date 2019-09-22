package algorithms.searching;

import java.util.*;

/**
 * @author Yangxiao on 10/29/2018.
 */

/**
 * Breadth first search
 */
class Word_Ladder {
	
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if(!wordList.contains(endWord)) {
			return 0;
		}
		int size = wordList.size();
		ArrayDeque<String> q = new ArrayDeque<>();
		HashSet<String> set = new HashSet<>(wordList);
		HashMap<String, Integer> dist = new HashMap<>();
		
		for (String word : wordList) {
			dist.put(word, 0);
		}
		dist.put(beginWord, 1);
		q.add(beginWord);
		while (!q.isEmpty()) {
			String temp = q.poll();
			int dis = dist.getOrDefault(temp, 0);
			List<String> words = new LinkedList<>();
			for (String word : set) {
				if (isTransferable(word, temp)) {
					if (word.equals(endWord)) {
						return dist.get(temp) + 1;
					}
					q.add(word);
					words.add(word);
					dist.put(word, dis + 1);
				}
			}
			set.removeAll(words);
		}
		return 0;
	}
	
	
	private boolean isTransferable(String s, String d) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != d.charAt(i)) {
				if (++count > 1) {
					return false;
				}
			}
		}
		
		return count == 1;
	}
	
	public int ladderLength_Sol(String beginWord, String endWord, List<String> wordList) {
		Set<String> words = new HashSet<>(wordList);
		Set<String> begins = new HashSet<>();
		Set<String> ends = new HashSet<>();
		if (!words.contains(endWord))
			return 0;
		begins.add(beginWord);
		ends.add(endWord);
		words.remove(beginWord);
		words.remove(endWord);
		int ans = 1;
		while (!begins.isEmpty() && !ends.isEmpty()) {
			ans++;
			if (begins.size() <= ends.size()) {
				if (next(begins, ends, words))
					return ans;
			} else {
				if (next(ends, begins, words))
					return ans;
			}
		}
		return 0;
		
	}
	
	public boolean next(Set<String> from, Set<String> to, Set<String> word) {
		String[] fromStrings = from.toArray(new String[from.size()]);
		for (String s : fromStrings) {
			char[] sc = s.toCharArray();
			for (int i = 0; i < sc.length; i++) {
				char ci = sc[i];
				for (char c = 'a'; c <= 'z'; c++) {
					sc[i] = c;
					String scs = String.valueOf(sc);
					if (to.contains(scs))
						return true;
					if (word.contains(scs)) {
						from.add(scs);
						word.remove(scs);
					}
				}
				sc[i] = ci;
			}
			from.remove(s);
		}
		return false;
	}
}
