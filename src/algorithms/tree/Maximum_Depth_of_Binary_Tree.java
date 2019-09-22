package algorithms.tree;

/**
 * @author Yangxiao on 12/17/2018.
 * <p>
 * Given a binary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the root node down to the
 * farthest leaf node.
 * Note: A leaf is a node with no children.
 *
 * Given binary tree [3,9,20,null,null,15,7],
 * <p>
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 * return its depth = 3.
 */
public class Maximum_Depth_of_Binary_Tree {
	
	
	public int maxDepth(TreeNode root) {
		
		if (root == null) {
			return 0;
		}
		int depthL = 1;
		int depthR = 1;
		depthL += maxDepth(root.left);
		depthR += maxDepth(root.right);
		return depthL > depthR ? depthL : depthR;
	}
	
	public int maxDepth_Sol1(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(maxDepth_Sol1(root.left), maxDepth_Sol1(root.right)) + 1;
	}
}
