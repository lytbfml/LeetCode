package algorithms.tree;

import java.util.Stack;

/**
 * @author Yangxiao on 4/13/2019.
 */
public class Recover_a_Tree_From_Preorder_Traversal {
	
	int x = 1323;
	
	public TreeNode recoverFromPreorder(String S) {
		Stack<TreeNode> stack = new Stack<>();
		int n = S.length();
		int i = 0;
		while (i < n) {
			int depth = 0;
			String valS = "";
			while (i < n && S.charAt(i) == '-') {
				depth++;
				i++;
			}
			while (i < n && S.charAt(i) != '-') {
				valS += S.charAt(i);
				i++;
			}
			while (stack.size() > depth) {
				stack.pop();
			}
			int x = Integer.parseInt(valS);
			TreeNode cur = new TreeNode(x);
			if (!stack.empty() && stack.peek().left == null) {
				stack.peek().left = cur;
			} else if (!stack.empty()) {
				stack.peek().right = cur;
			}
			stack.push(cur);
		}
		TreeNode x = null;
		while (!stack.isEmpty()) {
			x = stack.pop();
		}
		return x;
	}
	
	int index = 0;
	
	public TreeNode recoverFromPreorder_Sol(String S) {
		return helper(S, 0);
	}
	
	public TreeNode helper(String s, int depth) {
		int numDash = 0;
		while (index + numDash < s.length() && s.charAt(index + numDash) == '-') {
			numDash++;
		}
		if (numDash != depth)
			return null;
		int next = index + numDash;
		while (next < s.length() && s.charAt(next) != '-')
			next++;
		int val = Integer.parseInt(s.substring(index + numDash, next));
		index = next;
		TreeNode root = new TreeNode(val);
		root.left = helper(s, depth + 1);
		root.right = helper(s, depth + 1);
		return root;
	}
	
	
}
