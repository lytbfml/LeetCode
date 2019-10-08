package algorithms.tree;

import algorithms.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yangxiao on 10/5/2019.
 */
public class Two_Sum_BSTs {
	
	public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
		Set<Integer> set = new HashSet<>();
		tra1(root1, set, target);
		return tra2(root2, set);
	}
	
	public void tra1(TreeNode root, Set<Integer> set, int target) {
		if (root == null) {
			return;
		}
		set.add(target - root.val);
		tra1(root.left, set, target);
		tra1(root.right, set, target);
	}
	
	public boolean tra2(TreeNode root, Set<Integer> set) {
		if (root == null) {
			return false;
		}
		if (set.contains(root.val)) {
			return true;
		}
		return tra2(root.left, set) || tra2(root.right, set);
	}
}
