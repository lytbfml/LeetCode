package design;

import algorithms.tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
	
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		if (root == null) {
			return "";
		}
		helper(root, sb);
		return sb.toString();
	}
	
	private void helper(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append("null,");
		} else {
			sb.append(node.val).append(",");
			helper(node.left, sb);
			helper(node.right, sb);
		}
	}
	
	
	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data.isEmpty()) {
			return null;
		}
		Queue<String> queue = new LinkedList<>();
		queue.addAll(Arrays.asList(data.split(",")));
		return deHelper(queue);
	}
	
	private TreeNode deHelper(Queue<String> queue) {
		String val = queue.poll();
		if (val == null) {
			return null;
		}
		if (val.equals("null")) {
			return null;
		} else {
			TreeNode node = new TreeNode(Integer.parseInt(val));
			node.left = deHelper(queue);
			node.right = deHelper(queue);
			return node;
		}
	}
	
	class codecLevelOrder {
		public String serialize(TreeNode root) {
			StringBuilder sb = new StringBuilder();
			if (root == null) {
				return "";
			}
			Queue<TreeNode> q = new LinkedList<>();
			q.offer(root);
			while (!q.isEmpty()) {
				TreeNode node = q.poll();
				if (node == null) {
					sb.append("N,");
				} else {
					sb.append(node.val).append(",");
					q.offer(node.left);
					q.offer(node.right);
				}
			}
			return sb.toString();
		}
		// 1,2,3,N,N,4,5,N,N,N,N
		
		public TreeNode deserialize(String data) {
			if (data.isEmpty()) {
				return null;
			}
			Queue<TreeNode> queue = new LinkedList<>();
			String[] dd = data.split(",");
			TreeNode root = new TreeNode(Integer.valueOf(dd[0]));
			queue.offer(root);
			for (int i = 1; i < dd.length; i++) {
				TreeNode temp = queue.poll();
				if (!dd[i].equals("N")) {
					TreeNode leftNode = new TreeNode(Integer.valueOf(dd[i]));
					temp.left = leftNode;
					queue.offer(leftNode);
				}
				if (!dd[++i].equals("N")) {
					TreeNode rightNode = new TreeNode(Integer.valueOf(dd[i]));
					temp.right = rightNode;
					queue.offer(rightNode);
				}
			}
			
			return root;
		}
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));