package algorithms.string;

import java.util.Stack;

/**
 * @author Yangxiao Wang on 2019-07-05
 */
public class Parsing_A_Boolean_Expression {
	//'(', ')', '&', '|', '!', 't', 'f', ','
	public boolean parseBoolExpr(String exp) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			if (c == ')') {
				boolean hasTrue = false;
				boolean hasFalse = false;
				while (stack.peek() != '(') {
					char next = stack.pop();
					if (next == 't') hasTrue = true;
					if (next == 'f') hasFalse = true;
				}
				
				stack.pop();
				
				char expOut = stack.pop();
				if (expOut == '!') {
					stack.push(hasTrue ? 'f' : 't');
				} else if (expOut == '|') {
					stack.push(hasTrue ? 't' : 'f');
				} else if (expOut == '&') {
					stack.push(hasFalse ? 'f' : 't');
				}
			} else if (c != ',') {
				stack.push(c);
			}
		}
		
		return stack.pop() == 't';
	}
	
	class Solution_fast {
		private int pos;
		
		public boolean parseBoolExpr(String expression) {
			// if(expression.length ==)
			pos = 0;
			return parse(expression);
		}
		
		private boolean parse(String exp) {
			char c = exp.charAt(pos);
			pos++;
			if (c == 't') {
				return true;
			} else if (c == 'f') {
				return false;
			}
			
			pos++;
			if (c == '!') {
				boolean ans = parse(exp);
				pos++;
				return !ans;
			} else {
				boolean ans = c == '&';
				while (true) {
					boolean temp = parse(exp);
					if (c == '&') {
						ans &= temp;
					} else if (c == '|') {
						ans |= temp;
					}
					
					if (exp.charAt(pos) == ',') {
						pos++;
					} else if (exp.charAt(pos) == ')') {
						pos++;
						return ans;
					}
				}
			}
			
		}
	}
}
