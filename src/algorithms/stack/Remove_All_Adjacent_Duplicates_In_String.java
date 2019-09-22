package algorithms.stack;

/**
 * @author Yangxiao Wang on 5/18/2019
 */
public class Remove_All_Adjacent_Duplicates_In_String {
	
	public String removeDuplicates(String S) {
		
		boolean has = true;
		
		while (has) {
			has = false;
			for (int i = 1; i < S.length(); i++) {
				if (S.charAt(i) == S.charAt(i - 1)) {
					S = S.substring(0, i - 1) + S.substring(i + 1);
					has = true;
					break;
				}
			}
		}
		
		return S;
	}
	
	public String removeDuplicates_stack(String S) {
		int n = S.length();
		char[] stack = new char[n];
		int top = -1;
		for (int i = 0; i < n; i++) {
			if (top >= 0 && S.charAt(i) == stack[top])
				top--;
			else
				stack[++top] = S.charAt(i);
		}
		return new String(stack, 0, top + 1);
	}
}
