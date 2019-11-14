package algorithms.backtracking;

/**
 * bitwise
 * @author Yangxiao on 11/9/2019.
 */
public class Maximum_Score_Words_Formed_by_Letters {
	
	class Solution {
		public int maxScoreWords(String[] words, char[] letters, int[] score) {
			if (words == null || words.length == 0 || letters == null || letters.length == 0 || score == null ||
					score.length == 0) return 0;
			int[] count = new int[score.length];
			for (char ch : letters) {
				count[ch - 'a']++;
			}
			int res = backtrack(words, count, score, 0);
			return res;
		}
		
		int backtrack(String[] words, int[] count, int[] score, int index) {
			int max = 0;
			for (int i = index; i < words.length; i++) {
				int res = 0;
				boolean isValid = true;
				for (char ch : words[i].toCharArray()) {
					count[ch - 'a']--;
					res += score[ch - 'a'];
					if (count[ch - 'a'] < 0) isValid = false;
				}
				if (isValid) {
					res += backtrack(words, count, score, i + 1);
					max = Math.max(res, max);
				}
				for (char ch : words[i].toCharArray()) {
					count[ch - 'a']++;
					res = 0;
				}
			}
			return max;
		}
	}
	
	class Solution_fast_bit {
		int getBit(int x, int k) {
			return (x >> k) & 1;
		}
		
		boolean canFeed(String word, int[] cnt) {
			for (int i = 0; i < word.length(); i++) {
				if (cnt[word.charAt(i) - 'a']-- <= 0) // prune
					return false;
			}
			return true;
		}
		
		public int maxScoreWords(String[] words, char[] letters, int[] score) {
			int cnt[] = new int[26];
			for (char c : letters) cnt[c - 'a']++;
			
			int n = words.length;
			int[] scores = new int[n]; // Calculate the score of each word
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < words[i].length(); j++)
					scores[i] += score[words[i].charAt(j) - 'a'];
			}
			
			int cntClone[] = new int[26];
			int maxScore = 0, bound = (int) Math.pow(2, n);
			for (int i = 0; i <= bound; i++) { // O(15 * 14 * 2^14)
				System.arraycopy(cnt, 0, cntClone, 0, 26);
				int totalScore = 0;
				for (int j = 0; j < n; j++) { // O(15 * 14)
					if (getBit(i, j) == 1) { // prune a haft of the number of combinations
						if (canFeed(words[j], cntClone)) { // O(15)
							totalScore += scores[j];
						} else {
							break; // prune
						}
					}
				}
				maxScore = Math.max(maxScore, totalScore);
			}
			
			return maxScore;
		}
	}
}
