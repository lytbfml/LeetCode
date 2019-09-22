package algorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Yangxiao on 12/18/2018.
 */
public class Inorder {
	
	
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null) {
			return list;
		}
		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.empty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			list.add(root.val);
			root = root.right;
		}
		return list;
	}
	
	/* Given a binary tree, print its nodes in inorder*/
	void printInorder(TreeNode node) {
		if (node == null)
			return;
		/* first recur on left child */
		printInorder(node.left);
		/* then print the data of node */
		System.out.print(node.val + " ");
		/* now recur on right child */
		printInorder(node.right);
	}
}
