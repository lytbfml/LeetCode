package algorithms.hashtable;

import java.util.Arrays;

/**
 * @author Yangxiao Wang on 8/17/2019
 */
public class Find_Words_That_Can_Be_Formed_by_Characters {
	
	public int countCharacters(String[] words, String chars) {
        int n = words.length, m = chars.length();
        int[] mem = new int[26];
        char[] c = chars.toCharArray();
        for (char cc: c)  {
            mem[cc - 'a']++;
        }
        int res =0;
        int[] tmp;
        for (int i = 0; i < n; i++) {
            tmp = Arrays.copyOf(mem, 26);
            int cur = 0;
            for (int j = 0; j < words[i].length(); j++) {
                if (tmp[words[i].charAt(j) - 'a']-- == 0) {
                    cur = 0;
                    break;
                }
                cur++;
            }
            res += cur;
        }
        return res;
    }
}
