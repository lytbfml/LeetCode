package algorithms.tree;

/**
 * @author Yangxiao on 11/10/2018.
 */

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 * <p>
 * Given the root node of a binary search tree, return the sum of values of all nodes with value
 * between L and R (inclusive).
 * <p>
 * The binary search tree is guaranteed to have unique values.
 */

class Range_Sum_of_BST {
	
	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null) {
			return 0;
		}
		
		int sum = 0;
		if (root.val <= R && root.val >= L) {
			sum += root.val;
		}
		if (root.val < R) {
			sum += rangeSumBST(root.right, L, R);
		}
		if (root.val > L) {
			sum += rangeSumBST(root.left, L, R);
		}
		
		return sum;
	}
	
	public int rangeSumBST_Sol1(TreeNode root, int L, int R) {
		return dfs(root, L, R);
	}
	
	int dfs(TreeNode root, int L, int R) {
		if (root == null)
			return 0;
		return dfs(root.left, L, R) + dfs(root.right, L, R) +
				(L <= root.val && root.val <= R ? root.val : 0);
	}
	
	public int rangeSumBST_Sol2(TreeNode root, int L, int R) {
		int ans = 0;
		Stack<TreeNode> stack = new Stack();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			if (node != null) {
				if (L <= node.val && node.val <= R)
					ans += node.val;
				if (L < node.val)
					stack.push(node.left);
				if (node.val < R)
					stack.push(node.right);
			}
		}
		return ans;
	}
	
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) { val = x; }
	}
}
