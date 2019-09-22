package algorithms.searching;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Yangxiao on 5/14/2019.
 */
public class Longest_Duplicate_Substring {
	
	public class Suffix implements Comparable<Suffix> {
		int index = 0;
		int[] rank = new int[2];
		
		public Suffix() {
		
		}
		
		public Suffix(int index, int[] rank) {
			this.index = index;
			this.rank = rank;
		}
		
		@Override
		public int compareTo(Suffix other) {
			if (rank[0] == other.rank[0])
				return rank[1] - other.rank[1];
			return rank[0] - other.rank[0];
		}
	}
	
	public int[] buildSuffix(String s, int n) {
		Suffix[] suffix = new Suffix[n];
		for (int i = 0; i < n; i++) {
			suffix[i] = new Suffix(i, new int[]{s.charAt(i) - 'a',
			                                    i < n - 1 ? s.charAt(i + 1) - 'a' : -1});
		}
		Arrays.sort(suffix);
		int[] ind = new int[n];
		for (int k = 4; k < 2 * n; k *= 2) {
			int rank = 0;
			int prev_rank = suffix[0].rank[0];
			suffix[0].rank[0] = rank;
			ind[suffix[0].index] = 0;
			
			for (int i = 1; i < n; i++) {
				Suffix curSuf = suffix[i];
				if (curSuf.rank[0] == prev_rank && curSuf.rank[1] == suffix[i - 1].rank[1]) {
					curSuf.rank[0] = rank;
				} else {
					prev_rank = curSuf.rank[0];
					curSuf.rank[0] = ++rank;
				}
				ind[curSuf.index] = i;
			}
			
			for (int i = 0; i < n; i++) {
				int nextindex = suffix[i].index + k / 2;
				suffix[i].rank[1] = (nextindex < n) ? suffix[ind[nextindex]].rank[0] : -1;
			}
			Arrays.sort(suffix);
		}
		// Store indexes of all sorted suffixes in the suffix array
		int[] suffixArr = new int[n];
		for (int i = 0; i < n; i++)
			suffixArr[i] = suffix[i].index;
		// Return the suffix array
		return suffixArr;
	}
	
	public int[] kasai(String s, int[] suffixArr) {
		int n = suffixArr.length;
		int[] lcp = new int[n];
		int[] invSuff = new int[n];
		for (int i = 0; i < n; i++) {
			invSuff[suffixArr[i]] = i;
		}
		int lenPrev = 0;
		
		for (int i = 0; i < n; i++) {
			if (invSuff[i] == n - 1) {
				lenPrev = 0;
				continue;
			}
			
			int indexOfNext = suffixArr[invSuff[i] + 1];
			
			while (i + lenPrev < n && indexOfNext + lenPrev < n &&
					s.charAt(i + lenPrev) == s.charAt(indexOfNext + lenPrev))
				lenPrev++;
			
			lcp[invSuff[i]] = lenPrev;// lcp for the present suffix.
			
			// Deleting the starting character from the string.
			if (lenPrev > 0)
				lenPrev--;
		}
		return lcp;
	}
	
	public String longestDupSubstring(String S) {
		int[] suffixArr = buildSuffix(S, S.length());
		int n = suffixArr.length;
		int[] lcp = kasai(S, suffixArr);
		
		int ans = 0;
		int start = 0;
		
		for (int i = 0; i < n; i++) {
			if (lcp[i] > ans) {
				ans = lcp[i];
				start = suffixArr[i];
			}
		}
		
		if (ans == 0)
			return "";
		return S.substring(start, start + ans);
	}
	
	public String longestDupSubstring_BinarySearch(String s) {
		int left = 1, right = s.length();
		long mod = Long.MAX_VALUE / 26;
		String ans = "";
		while (left <= right) {
			Set<Long> set = new HashSet<>();
			int mid = left + (right - left) / 2, flag = 0;
			long hash = 0, p = 1;
			for (int i = 0; i < mid; i++) {
				hash = (26 * hash + (s.charAt(i) - 'a')) % mod;
				p = (p * 26) % mod;
			}
			set.add(hash);
			for (int i = 0; i + mid < s.length(); i++) {
				hash = (hash * 26 + (s.charAt(i + mid) - 'a') - ((s.charAt(i) - 'a') * p)) % mod;
				if (hash < 0)
					hash += mod;
				if (set.contains(hash)) {
					flag = 1;
					ans = s.substring(i + 1, i + mid + 1);
					break;
				}
				set.add(hash);
			}
			if (flag == 1) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}
	
	class Solution {
		
		public class Suffix implements Comparable<Suffix> {
			
			int index = 0;
			int[] rank = new int[2];
			
			public Suffix(int index, int rank0, int rank1) {
				this.index = index;
				this.rank = new int[]{rank0, rank1};
			}
			
			@Override
			public int compareTo(Suffix o2) {
				if (this.rank[0] == o2.rank[0]) {
					return this.rank[1] - o2.rank[1];
				}
				return this.rank[0] - o2.rank[0];
			}
		}
		
		public int[] buildSuffixArray(String S, int n) {
			Suffix[] suf = new Suffix[n];
			for (int i = 0; i < n; i++) {
				if (i == n - 1) {
					suf[i] = new Suffix(i, S.charAt(i) - 'a', -1);
				} else {
					suf[i] = new Suffix(i, S.charAt(i) - 'a', S.charAt(i+1) - 'a');
				}
			}
			
			Arrays.sort(suf);
			int[] inds = new int[n];
			
			for (int k = 2; k < n; k *=2) {
				int rank = 0;
				int prev = suf[0].rank[0];
				suf[0].rank[0] = 0;
				inds[suf[0].index] = 0;
				for (int i = 1; i < n; i++) {
					if(suf[i].rank[0] == prev && suf[i].rank[1] == suf[i-1].rank[1]) {
						suf[i].rank[0] = rank;
					} else {
						prev = suf[i].rank[0];
						suf[i].rank[0] = ++rank;
					}
					inds[suf[i].index] = i;
				}
				
				for (int i = 0; i < n; i++) {
					int nextIndex = suf[i].index + k;
					suf[i].rank[1] = (nextIndex < n) ? suf[inds[nextIndex]].rank[0] : -1;
				}
				
				Arrays.sort(suf);
			}
			
			int[] sufArr = new int[n];
			for (int i = 0; i < n; i++) {
				sufArr[i] = suf[i].index;
			}
			
			return sufArr;
		}
		
		public int[] LCP(String S, int[] suf) {
			int n = suf.length;
			int[] lcp = new int[n];
			int[] inv = new int[n];
			
			for (int i = 0; i < n; i++) {
				inv[suf[i]] = i;
			}
			
			int lenPrev = 0;
			
			for (int i = 0; i < n; i++) {
				
				if(inv[i] == n-1) {
					lenPrev = 0;
					continue;
				}
				
				int indNext = suf[inv[i] + 1];
				
				while (i + lenPrev < n && indNext + lenPrev < n && S.charAt(i+lenPrev) == S.charAt(indNext + lenPrev)) {
					lenPrev++;
				}
				
				lcp[inv[i]] = lenPrev;
				
				if(lenPrev > 0) {
					lenPrev--;
				}
			}
			
			return lcp;
		}
		
		public String longestDupSubstring(String S) {
			int[] suf = buildSuffixArray(S, S.length());
			int[] lcp = LCP(S, suf);
			int ind = 0;
			int len = 0;
			for (int i = 0; i < S.length(); i++) {
				if(lcp[i] > len) {
					len = lcp[i];
					ind = suf[i];
				}
			}
			if(len == 0)
				return "";
			
			return S.substring(ind, len + ind);
		}
	}
}
