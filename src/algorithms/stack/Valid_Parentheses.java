package algorithms.stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Yangxiao on 10/31/2018.
 */

class Valid_Parentheses {
	
	public boolean isValid(String s) {
		if (s.isEmpty()) {
			return true;
		}
		Stack<Character> stack = new Stack<>();
		char[] ca = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			
			if (ca[i] == '(' || ca[i] == '[' || ca[i] == '{') {
				stack.push(ca[i]);
			} else {
				if (stack.isEmpty())
					return false;
				char x = stack.pop();
				
				switch (x) {
					case '(':
						if (ca[i] == ')') {
							break;
						} else {
							return false;
						}
					case '{':
						if (ca[i] == '}') {
							break;
						} else {
							return false;
						}
					case '[':
						if (ca[i] == ']') {
							break;
						} else {
							return false;
						}
				}
			}
		}
		return stack.isEmpty();
	}
	
	
	public boolean isValid_Sol(String s) {
		LinkedList<Character> stack = new LinkedList<>();
		char[] c = s.toCharArray();
		for (char cc : c) {
			if (cc == '{') {
				stack.push('}');
			} else if (cc == '[') {
				stack.push(']');
			} else if (cc == '(') {
				stack.push(')');
			} else if (stack.isEmpty() || stack.pop() != cc) {
				return false;
			}
		}
		return stack.isEmpty();
	}
}
