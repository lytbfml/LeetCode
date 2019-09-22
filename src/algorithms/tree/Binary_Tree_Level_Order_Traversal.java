package algorithms.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangxiao on 12/19/2018.
 *
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class Binary_Tree_Level_Order_Traversal {
	
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> list = new ArrayList<>();
		if (root == null) {
			return list;
		}
		levelOrder(root, 1, list);
		return list;
	}
	
	private void levelOrder(TreeNode root, int level, List<List<Integer>> list) {
		if (root == null) {
			return;
		}
		if (list.size() == level) {
			list.add(new ArrayList<>());
		}
		list.get(level).add(root.val);
		levelOrder(root.left, level+1, list);
		levelOrder(root.right, level+1, list);
	}
}
