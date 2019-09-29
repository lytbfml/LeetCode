package algorithms.stack;

import java.util.Stack;

/**
 * @author Yangxiao on 9/28/2019.
 */
public class Remove_All_Adjacent_Duplicates_in_String_II {
	
	public static void main(String[] args) {
		Remove_All_Adjacent_Duplicates_in_String_II cs = new Remove_All_Adjacent_Duplicates_in_String_II();
		System.out.println(cs.removeDuplicates("abcd", 2));
		System.out.println(cs.removeDuplicates("deeedbbcccbdaa", 3));
		System.out.println(cs.removeDuplicates("pbbcggttciiippooaais", 2));
	}
	
	public String removeDuplicates(String s, int k) {
		int n = s.length();
		char[] cs = s.toCharArray();
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[]{cs[0], 1});
		for (int i = 1; i < n; i++) {
			if (!stack.isEmpty()) {
				int[] peek = stack.peek();
				if (peek[0] == cs[i]) {
					peek[1]++;
					if (peek[1] == k) {
						stack.pop();
					}
				} else {
					stack.push(new int[]{cs[i], 1});
				}
			} else {
				stack.push(new int[]{cs[i], 1});
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			int[] peek = stack.pop();
			for (int i = 0; i < peek[1]; i++) {
				sb.append((char) peek[0]);
			}
		}
		
		return sb.reverse().toString();
	}
	
}
