package algorithms.tree;

import java.util.*;

/**
 * @author Yangxiao on 12/18/2018.
 * <p>
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class Validate_Binary_Search_Tree {
	
	
	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		Stack<TreeNode> q = new Stack<>();
		TreeNode pre = null;
		while (root != null || !q.isEmpty()) {
			while (root != null) {
				q.push(root);
				root = root.left;
			}
			root = q.pop();
			
			if (pre != null && root.val <= pre.val) { return false; }
			pre = root;
			root = root.right;
		}
		return true;
	}
	
	
	public boolean isValidBST_Sol1(TreeNode root) {
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}
	
	public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
		if (root == null) return true;
		if (root.val >= maxVal || root.val <= minVal) return false;
		return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
	}
}
