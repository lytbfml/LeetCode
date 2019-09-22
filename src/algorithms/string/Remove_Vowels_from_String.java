package algorithms.string;

/**
 * https://leetcode.com/problems/remove-vowels-from-a-string/
 *
 * @author Yangxiao on 9/14/2019.
 */
public class Remove_Vowels_from_String {
	
	public String removeVowels(String S) {
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == 'a' || S.charAt(i) == 'e' || S.charAt(i) == 'i' || S.charAt(i) == 'o' ||
					S.charAt(i) == 'u') {
				continue;
			} else {
				ans = ans.append(S.charAt(i));
			}
		}
		return ans.toString();
	}
	
	public String removeVowels2(String S) {
		return S.replaceAll("[aeiou]", "");
	}
	
	public String removeVowels3(String S) {
		return S.replaceAll("a|e|i|o|u", "");
	}
}
