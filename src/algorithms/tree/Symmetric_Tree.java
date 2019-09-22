package algorithms.tree;

import java.util.*;

/**
 * @author Yangxiao on 12/18/2018.
 * <p>
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */
public class Symmetric_Tree {
	
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		Stack<TreeNode> qL = new Stack<>();
		Stack<TreeNode> qR = new Stack<>();
		qL.push(root.left);
		qR.push(root.right);
		while (!qL.isEmpty() && !qR.isEmpty()) {
			TreeNode l = qL.pop();
			TreeNode r = qR.pop();
			if (l == null && r == null) {
				continue;
			}
			if (l == null || r == null) {
				return false;
			}
			if (l.val != r.val) {
				return false;
			}
			qL.push(l.left);
			qL.push(l.right);
			qR.push(r.right);
			qR.push(r.left);
		}
		return true;
	}
	
	public boolean isSymmetric2(TreeNode root) {
		if (root == null) {
			return true;
		}
		
		List<TreeNode> lls = new ArrayList<>();
		List<TreeNode> rls = new ArrayList<>();
		syValid(root.left, lls, true);
		syValid(root.right, rls, false);
		if (lls.size() != rls.size()) {
			return false;
		}
		
		for (int i = 0; i < lls.size(); i++) {
			TreeNode l = lls.get(i);
			TreeNode r = rls.get(i);
			if (l == null && r == null) {
				continue;
			}
			if (l == null || r == null) {
				return false;
			}
			if (l.val != r.val) {
				return false;
			}
		}
		
		return true;
	}
	
	private void syValid(TreeNode root, List<TreeNode> list, boolean isLeft) {
		list.add(root);
		if (root == null) {
			return;
		}
		if (isLeft) {
			syValid(root.left, list, isLeft);
			syValid(root.right, list, isLeft);
		} else {
			syValid(root.right, list, isLeft);
			syValid(root.left, list, isLeft);
		}
	}
	
	public boolean isSymmetric_Sol1(TreeNode root) {
		if (root == null) { return true; }
		return isEqual(root.left, root.right);
	}
	
	private boolean isEqual(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) { return true; }
		if (root1 == null || root2 == null) { return false; }
		
		return root1.val == root2.val && isEqual(root1.left, root2.right) &&
				isEqual(root1.right, root2.left);
	}
}
