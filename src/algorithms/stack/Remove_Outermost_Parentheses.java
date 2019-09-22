package algorithms.stack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author Yangxiao on 4/6/2019.
 */
public class Remove_Outermost_Parentheses {
	
	public static void main(String[] args) {
		Remove_Outermost_Parentheses cs = new Remove_Outermost_Parentheses();
		System.out.println(cs.removeOuterParentheses("(()())(())"));
		
	}
	
	public String removeOuterParentheses(String S) {
		if (S == null || S.length() == 0) {
			return S;
		}
		LinkedList<Character> stack = new LinkedList<>();
		char[] chars = S.toCharArray();
		Set<Integer> inds = new HashSet<>();
		int ind = 0;
		int intern = 0;
		for (char temp : chars) {
			if (temp == '{') {
				stack.push('}');
			} else if (temp == '[') {
				stack.push(']');
			} else if (temp == '(') {
				stack.push(')');
			} else if (stack.pop() == temp && stack.isEmpty()) {
				inds.add(ind - intern);
				inds.add(ind);
				intern = -1;
			}
			intern++;
			ind++;
		}
		// System.out.println(inds);
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			if (!inds.contains(i)) {
				res.append(chars[i]);
			}
		}
		return res.toString();
	}
}
