package algorithms.tree;

import java.util.Stack;

/**
 * @author Yangxiao on 12/18/2018.
 */
public class Kth_Smallest_Element_in_a_BST {
	
	public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (--k == 0) {
				break;
			}
			root = root.right;
		}
		return root.val;
	}
	
	class Solution_Rec {
		
		int k = 0;
		int res = 0;
		public int kthSmallest_rec(TreeNode root, int k) {
			this.k = k;
			inOrder(root);
			return res;
		}
		
		private void inOrder(TreeNode root) {
			if (k < 0) {
				return;
			}
			
			if (root == null) {
				return;
			}
			inOrder(root.left);
			k--;
			if (k == 0) {
				res = root.val;
				return;
			}
			inOrder(root.right);
		}
	}
	
	
}
