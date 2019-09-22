package algorithms.hashtable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangxiao on 8/31/2019.
 * <p>
 * wc152
 * Bit Manipulation
 */
public class Number_Valid_Words_for_Each_Puzzle {
	
	public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
		List<Integer> res = new ArrayList<>();
		int n = words.length, m = puzzles.length;
		int[] mem = new int[n];
		boolean[][] bMem = new boolean[n][26];
		for (int i = 0; i < n; i++) {
			for (char c : words[i].toCharArray()) {
				bMem[i][c - 'a'] = true;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < 26; k++) {
				mem[i] <<= 1;
				if (bMem[i][k]) {
					mem[i] |= 1;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			boolean[] englishMem = new boolean[26];
			int firstC = puzzles[i].charAt(0) - 'a';
			int puzBit = 0;
			for (int j = 0; j < puzzles[i].length(); j++) {
				englishMem[puzzles[i].charAt(j) - 'a'] = true;
			}
			
			for (int j = 0; j < 26; j++) {
				puzBit <<= 1;
				if (englishMem[j])
					puzBit |= 1;
			}
			
			int cnt = 0;
			for (int j = 0; j < n; j++) {
				int reTmp = mem[j] & puzBit;
				if (reTmp != mem[j]) {
					continue;
				}
				if (!bMem[j][firstC]) {
					continue;
				}
				
				cnt++;
			}
			res.add(cnt);
		}
		return res;
	}
	
	public List<Integer> findNumOfValidWords_Sol(String[] words, String[] puzzles) {
		int[][] puz = new int[puzzles.length][2];
		for (int i = 0; i < puzzles.length; i++) {
			puz[i][0] = (int) (puzzles[i].charAt(0) - 'a');
			for (int j = 0; j < puzzles[i].length(); j++) {
				int bit = 1 << (puzzles[i].charAt(j) - 'a');
				puz[i][1] |= bit;
			}
			
		}
		int[] wor = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				int bit = 1 << (words[i].charAt(j) - 'a');
				wor[i] |= bit;
			}
		}
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < puz.length; i++) {
			int count = 0;
			for (int j = 0; j < wor.length; j++) {
				int orRes = puz[i][1] | wor[j];
				if (orRes != puz[i][1])
					continue;
				int moves = 1 << puz[i][0];
				if ((wor[j] & moves) != 0) {
					count++;
				}
			}
			res.add(count);
		}
		return res;
	}
	
	public List<Integer> findNumOfValidWords_YANGDE(String[] words, String[] puzzles) {
		List<Integer> list = new ArrayList<>();
		boolean[][] wd = new boolean[words.length][26];
		int[] bmap = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			for (char c : words[i].toCharArray()) {
				wd[i][c - 'a'] = true;
			}
		}
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < 26; j++) {
				bmap[i] <<= 1;
				if (wd[i][j]) {
					bmap[i] |= 1;
				}
			}
		}
		
		for (String p : puzzles) {
			boolean[] hash = new boolean[26];
			char h = p.charAt(0);
			int count = 0;
			for (char c : p.toCharArray()) {
				hash[c - 'a'] = true;
			}
			
			int pmap = 0;
			for (int i = 0; i < 26; i++) {
				pmap <<= 1;
				if (hash[i])
					pmap |= 1;
			}
			
			for (int i = 0; i < words.length; i++) {
				int temp = bmap[i] & pmap;
				if (temp != bmap[i])
					continue;
				if (!wd[i][h - 'a'])
					continue;
				count++;
				
			}
			
			list.add(count);
		}
		return list;
	}
}
