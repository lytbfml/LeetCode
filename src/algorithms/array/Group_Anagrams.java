package algorithms.array;

import java.util.*;

/**
 * @author Yangxiao Wang on 5/16/2019
 */
public class Group_Anagrams {
	
	public static void main(String[] args) {
		String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
		Group_Anagrams cs = new Group_Anagrams();
		// cs.groupAnagrams(s);
		
		String q1 = "jar";
		int ha = 1;
		for (int j = 0; j < q1.length(); j++) {
			ha *= q1.charAt(j) - 'a' + 1;
		}
		System.out.println(ha);
		
		String q2 = "cod";
		int ga = 1;
		for (int j = 0; j < q2.length(); j++) {
			ga *= q2.charAt(j) - 'a' + 1;
		}
		System.out.println(ga);
	}
	
	public List<List<String>> groupAnagrams(String[] str) {
		if (str == null || str.length == 0) {
			return null;
		}
		int n = str.length;
		List<List<String>> res = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		
		int index = 0;
		for (int i = 0; i < n; i++) {
			String org = str[i];
			char[] temp = org.toCharArray();
			Arrays.sort(temp);
			String s = new String(temp);
			if (map.containsKey(s)) {
				res.get(map.get(s)).add(org);
			} else {
				res.add(new ArrayList<>(Arrays.asList(org)));
				map.put(s, index);
				index++;
			}
		}
		
		return res;
	}
	
	public List<List<String>> groupAnagrams_(String[] strs) {
		int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
		List<List<String>> res = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (String str: strs) {
			int key = 1;
			for (char ch: str.toCharArray()) {
				key *= primes[ch - 'a'];
			}
			List<String> t;
			if (map.containsKey(key)) {
				t = res.get(map.get(key));
			} else {
				t = new ArrayList<>();
				res.add(t);
				map.put(key, res.size() - 1);
			}
			t.add(str);
		}
		return res;
	}
	
	public class Solution {
		public List<List<String>> groupAnagrams(String[] strs) {
			if (strs == null || strs.length == 0)
				return new ArrayList<>();
			Map<String, List<String>> map = new HashMap<>();
			for (String s : strs) {
				char[] ca = s.toCharArray();
				Arrays.sort(ca);
				String keyStr = String.valueOf(ca);
				if (!map.containsKey(keyStr))
					map.put(keyStr, new ArrayList<>());
				map.get(keyStr).add(s);
			}
			return new ArrayList<>(map.values());
		}
	}
	
	// Not use
	public class StrClass {
		int code;
		int len;
		
		public StrClass(int code, int len) {
			this.code = code;
			this.len = len;
		}
		
		@Override
		public int hashCode() {
			int result = 1;
			result = 31 * result + code;
			result = 31 * result + len;
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			StrClass other = (StrClass) obj;
			if (code != other.code || len != other.len)
				return false;
			return true;
		}
		
		public String toString() {
			return "(code: " + code + ", len: " + len + ")";
		}
	}
	
}
