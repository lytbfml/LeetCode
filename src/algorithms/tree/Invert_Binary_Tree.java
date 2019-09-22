package algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Yangxiao on 3/30/2019.
 */
public class Invert_Binary_Tree {
	
	public static TreeNode helper(TreeNode root) {
		if (root == null) {
			return root;
		}
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;
		helper(root.left);
		helper(root.right);
		return root;
	}
	
	public TreeNode invertTree(TreeNode root) {
		return helper(root);
	}
	
	public TreeNode invertTree_Sol(TreeNode root) {
		if (root == null) { return null; }
		TreeNode left = root.left;
		// TreeNode right = root.right;
		root.left = invertTree_Sol(root.right);
		root.right = invertTree_Sol(left);
		return root;
	}
	
	public TreeNode invertTree_BFS(TreeNode root) {
		if (root == null) {
			return null;
		}
		
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			final TreeNode node = queue.poll();
			final TreeNode left = node.left;
			node.left = node.right;
			node.right = left;
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		return root;
	}
	
	
}
