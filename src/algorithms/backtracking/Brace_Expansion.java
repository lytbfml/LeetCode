package algorithms.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yangxiao Wang on 2019-08-16
 */
public class Brace_Expansion {
	
	public String[] expand(String S) {
		List<String> list = new ArrayList<>();
		helper(S.toCharArray(), 0, "", list);
		Collections.sort(list);
		return list.toArray(new String[0]);
	}
	
	private void helper(char[] c, int i, String sb, List<String> list) {
		if (i >= c.length) {
			list.add(sb);
			return;
		}
		if (c[i] == '{') {
			int close = i + 1;
			while (c[close] != '}')
				close++;
			int x = i + 1;
			while (x < close) {
				helper(c, close + 1, sb + c[x], list);
				x += 2;
			}
		} else {
			helper(c, i + 1, sb + c[i], list);
		}
	}
	
	class Solution_StringBuilder {
		public String[] expand(String S) {
			List<String> list = new ArrayList<>();
			helper(S.toCharArray(), new StringBuilder(), list, 0);
			Collections.sort(list);
			return list.toArray(new String[0]);
		}
		
		
		public void helper(char[] cc, StringBuilder sb, List<String> list, int i) {
			if (i == cc.length) {
				list.add(sb.toString());
			} else {
				if (cc[i] == '{') {
					int close = i + 1, idx = i + 1;
					while (cc[close] != '}') {
						close++;
					}
					while (idx < close) {
						int len = sb.length();
						sb.append(cc[idx]);
						helper(cc, sb, list, close + 1);
						sb.setLength(len);
						idx += 2;
					}
				} else {
					int len = sb.length();
					sb.append(cc[i]);
					helper(cc, sb, list, i + 1);
					sb.setLength(len);
				}
			}
		}
	}
	
}
