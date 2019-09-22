package algorithms.dfs;

import java.util.*;

/**
 * @author Yangxiao Wang on 2019-07-06
 */
public class Delete_Nodes_And_Return_Forest {
	
	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		int n = to_delete.length;
		Set<Integer> set = new HashSet<>();
		for (int x : to_delete) {
			set.add(x);
		}
		List<TreeNode> res = new ArrayList<>();
		if (!set.contains(root.val)) {
			res.add(root);
		} else {
			if (root.left != null && !set.contains(root.left.val)) {
				res.add(root.left);
			}
			
			if (root.right != null && !set.contains(root.right.val)) {
				res.add(root.right);
			}
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode cur = q.poll();
			TreeNode left = cur.left;
			TreeNode right = cur.right;
			
			if (left != null && set.contains(left.val)) {
				find(left, set, res, q);
				cur.left = null;
			} else if (left != null && !set.contains(left.val)) {
				q.add(left);
			}
			
			if (right != null && set.contains(right.val)) {
				find(right, set, res, q);
				cur.right = null;
			} else if (right != null && !set.contains(right.val)) {
				q.add(right);
			}
		}
		
		return res;
	}
	
	private void find(TreeNode node, Set<Integer> set, List<TreeNode> res, Queue<TreeNode> q) {
		if (node == null) {
			return;
		}
		
		if (!set.contains(node.val)) {
			res.add(node);
			q.add(node);
		} else {
			find(node.left, set, res, q);
			find(node.right, set, res, q);
		}
	}
	
	class Solution_good {
		public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
			List<TreeNode> forest = new ArrayList<>();
			if (root == null) return forest;
			Set<Integer> set = new HashSet<>();
			for (int i : to_delete) {
				set.add(i);
			}
			deleteNodes(root, set, forest);
			if (!set.contains(root.val)) {
				forest.add(root);
			}
			return forest;
		}
		
		private TreeNode deleteNodes(TreeNode node, Set<Integer> set, List<TreeNode> forest) {
			if (node == null) return null;
			
			node.left = deleteNodes(node.left, set, forest);
			node.right = deleteNodes(node.right, set, forest);
			
			if (set.contains(node.val)) {
				if (node.left != null) forest.add(node.left);
				if (node.right != null) forest.add(node.right);
				return null;
			}
			
			return node;
		}
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) { val = x; }
	}
}
