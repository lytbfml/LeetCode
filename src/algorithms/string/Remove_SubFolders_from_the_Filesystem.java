package algorithms.string;

import java.util.*;

/**
 * @author Yangxiao Wang on 10/29/2019
 */
public class Remove_SubFolders_from_the_Filesystem {
	
	
	public List<String> removeSubfolders_map(String[] folder) {
		Arrays.sort(folder, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});
		Set<String> seen = new HashSet<>();
		outer:
		for (String f : folder) {
			for (int i = 2; i < f.length(); ++i)
				if (f.charAt(i) == '/' && seen.contains(f.substring(0, i)))
					continue outer;
			seen.add(f);
		}
		return new ArrayList<>(seen);
	}
	
	public List<String> removeSubfolders(String[] folder) {
		Arrays.sort(folder);
		String cur = folder[0];
		List<String> res = new ArrayList<>();
		for (int i = 1; i < folder.length; i++) {
			if (!folder[i].startsWith(cur + "/")) {
				res.add(folder[i]);
				cur = folder[i];
			}
		}
		return res;
	}
	
	
	class Solution {
		
		class Trie {
			Trie[] next = new Trie[27];
			int index = -1;
		}
		
		public List<String> removeSubfolders(String[] folder) {
			Trie root = new Trie();
			for (int i = 0; i < folder.length; i++) {
				Trie cur = root;
				for (char c : folder[i].toCharArray()) {
					int idx = c == '/' ? 26 : c - 'a';
					if (cur.next[idx] == null) {
						cur.next[idx] = new Trie();
					}
					cur = cur.next[idx];
				}
				cur.index = i;
			}
			return bfs(root, folder);
		}
		
		private List<String> bfs(Trie t, String[] folder) {
			List<String> ans = new ArrayList<>();
			Queue<Trie> q = new LinkedList<>();
			q.offer(t);
			while (!q.isEmpty()) { // BFS search.
				t = q.poll();
				if (t.index >= 0) { // found a parent folder, but there might be more.
					ans.add(folder[t.index]);
					if (t.next[26] !=
							null) // found all parent folders in current trie branch due to encountering char '/'.
						continue;
				}
				for (int i = 0; i < 27; ++i)
					if (t.next[i] != null)
						q.offer(t.next[i]);
			}
			return ans;
		}
	}
}
