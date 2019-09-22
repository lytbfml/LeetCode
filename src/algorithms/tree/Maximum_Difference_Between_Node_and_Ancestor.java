package algorithms.tree;

/**
 * @author Yangxiao on 4/13/2019.
 */
public class Maximum_Difference_Between_Node_and_Ancestor {
	
	public int maxAncestorDiff(TreeNode root) {
		return helper(root, root.val, root.val);
	}
	
	public int helper(TreeNode root, int max, int min) {
		if (root == null) { return 0;}
		int diff = Math.max(max - root.val, root.val - min);
		max = Math.max(max, root.val);
		min = Math.min(min, root.val);
		diff = Math.max(diff, helper(root.left, max, min));
		diff = Math.max(diff, helper(root.right, max, min));
		return diff;
	}
	
	
	public int maxAncestorDiff_S(TreeNode root) {
		return dfs(root, root.val, root.val);
	}
	
	public int dfs(TreeNode root, int mx, int mn) {
		if (root == null) { return 0; }
		int res = Math.max(mx - root.val, root.val - mn);
		mx = Math.max(mx, root.val);
		mn = Math.min(mn, root.val);
		res = Math.max(res, dfs(root.left, mx, mn));
		res = Math.max(res, dfs(root.right, mx, mn));
		return res;
	}
}
