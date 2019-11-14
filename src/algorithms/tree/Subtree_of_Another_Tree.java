package algorithms.tree;

import java.util.Stack;

/**
 * @author Yangxiao on 11/10/2019.
 */
public class Subtree_of_Another_Tree {
	
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null && t == null) return true;
		return traversal(s, t);
	}
	
	private boolean traversal(TreeNode s, TreeNode t) {
		if (s == null) return false;
		return compare(s, t) || traversal(s.left, t) || traversal(s.right, t);
	}
	
	private boolean compare(TreeNode s, TreeNode t) {
		if (s == null && t == null) return true;
		if (s == null || t == null) return false;
		return s.val == t.val && compare(s.left, t.left) && compare(s.right, t.right);
	}
	
	public class Solution {
		public boolean isSubtree(TreeNode s, TreeNode t) {
			String spreorder = generatepreorderString(s);
			String tpreorder = generatepreorderString(t);
			return spreorder.contains(tpreorder);
		}
		
		public String generatepreorderString(TreeNode s) {
			StringBuilder sb = new StringBuilder();
			Stack<TreeNode> stacktree = new Stack();
			stacktree.push(s);
			while (!stacktree.isEmpty()) {
				TreeNode popelem = stacktree.pop();
				if (popelem == null)
					sb.append(",#"); // Appending # inorder to handle same values but not subtree cases
				else
					sb.append("," + popelem.val);
				if (popelem != null) {
					stacktree.push(popelem.right);
					stacktree.push(popelem.left);
				}
			}
			return sb.toString();
		}
	}
	
	public class Solution2 {
		public boolean isSubtree(TreeNode s, TreeNode t) {
			if (s == null) return false;
			if (isSame(s, t)) return true;
			return isSubtree(s.left, t) || isSubtree(s.right, t);
		}
		
		private boolean isSame(TreeNode s, TreeNode t) {
			if (s == null && t == null) return true;
			if (s == null || t == null) return false;
			
			if (s.val != t.val) return false;
			
			return isSame(s.left, t.left) && isSame(s.right, t.right);
		}
	}
	
}
