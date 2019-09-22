package algorithms.dynamic_programming;

import java.util.Arrays;

/**
 * @author Yangxiao Wang on 6/26/2019
 */
public class Stickers_Spell_Word {
	
	public int minStickers(String[] stickers, String target) {
		
		
		return 0;
	}
	
	
	class Solution {
		public int minStickers(String[] stickers, String target) {
			int n = target.length(), m =
					1 << n; // if target has n chars, there will be m=2^n-1 subset of characters in target
			int[] dp = new int[m];
			for (int i = 0; i < m; i++)
				dp[i] = Integer.MAX_VALUE; // use index 0 - 2^n-1 as bitmaps to represent each subset of all chars in target
			dp[0] = 0; // first thing we know is : dp[empty set] requires 0 stickers,
			for (int i = 0; i < m; i++) { // for every subset i, start from 000...000
				if (dp[i] == Integer.MAX_VALUE)
					continue;
				for (String s : stickers) { // try use each sticker as an char provider to populate 1 of its superset, to do that:
					int sup = i;
					for (char c : s.toCharArray()) { // for each char in the sticker, try apply it on a missing char in the subset of target
						for (int r = 0; r < n; r++) {
							if (target.charAt(r) == c && ((sup >> r) & 1) == 0) {
								sup |= 1 << r;
								break;
							}
						}
					}
					// after you apply all possible chars in a sticker, you get an superset that take 1 extra sticker than subset
					// would take, so you can try to update the superset's minsticker number with dp[sub]+1;
					dp[sup] = Math.min(dp[sup], dp[i] + 1);
				}
			}
			return dp[m - 1] != Integer.MAX_VALUE ? dp[m - 1] : -1;
		}
	}
	
	class Solution_c {
		public int minStickers(String[] stickers, String target) {
			int[] minNeeded = new int[1 << target.length()];
			Arrays.fill(minNeeded, Integer.MAX_VALUE);
			minNeeded[0] = 0; // first thing we know is : dp[empty set] requires 0 stickers
			
			for (int i = 0; i < minNeeded.length; i++) { // for every subset i, start from 000...000
				// 在 statue从 0 至i - 1的状态下，试过所有sticker都没有达到 i 的状态。这个状态是不可能的。
				if (minNeeded[i] == Integer.MAX_VALUE)
					continue;
				//目前我们的状态是i, 在这个基础上，我们再使用任何一个sticker去填补target.
				//尽量地使用sticker里面的每一个字母（但每个字母每次只能用一次），
				//看用完这个sticker我们到达哪个新状态
				//我们对所有的sticker 都做这个步凑。
				for (String sticker : stickers) {
					int statue = i;  // 以状态i 为基础
					for (char stickerLetter : sticker.toCharArray()) {
						for (int pos = 0; pos < target.length(); pos++) {
							if (target.charAt(pos) == stickerLetter && (statue >> pos & 1) == 0) {
								statue = statue | 1 << pos;  // set the bit on pos to 1
								break; //每个ticker letter在当前状态下只能用一次。如果用多次，就是增加多张这个sticker了
								// 用完这个sicker letter ,我们就去尝试用下一个sticker letter
							}
						}
					}
					// 尽可能使用完sticker上所有字母后，从 i 状态得到了新的状态 statue.
					// 更新 minNeeded[statue]
					minNeeded[statue] = Math.min(minNeeded[statue], minNeeded[i] + 1);
				}
			}
			return minNeeded[minNeeded.length - 1] != Integer.MAX_VALUE ? minNeeded[minNeeded.length - 1] : -1;
		}
	}
}
