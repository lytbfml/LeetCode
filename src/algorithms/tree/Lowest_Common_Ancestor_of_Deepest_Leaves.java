package algorithms.tree;


/**
 * wc145
 * @author Yangxiao Wang on 7/13/2019
 */
public class Lowest_Common_Ancestor_of_Deepest_Leaves {
	
	int max = 0;
	TreeNode nodeR = null;
	int[] dd = new int[1001];
	int num = 0;
	private boolean nodefind = true;
	
	public TreeNode lcaDeepestLeaves(TreeNode root) {
		if (root == null) {
			return null;
		}
		helper(root, 0);
		num = dd[max];
		System.out.println(max);
		for (int i = 0; i < 5; i++) {
			System.out.println(dd[i]);
		}
		helper2(root, 0);
		return nodeR;
	}
	
	private void helper(TreeNode node, int depth) {
		if (node == null)
			return;
		if (depth >= max) {
			max = depth;
		}
		dd[depth]++;
		helper(node.left, depth + 1);
		helper(node.right, depth + 1);
	}
	
	private int helper2(TreeNode node, int depth) {
		if (node == null)
			return 0;
		if (depth == max) {
			if (nodefind && num == 1) {
				nodeR = node;
				nodefind = false;
			}
			return 1;
		}
		
		int s1 = helper2(node.left, depth + 1);
		int s2 = helper2(node.right, depth + 1);
		int sum = s1 + s2;
		if (nodefind && sum == num) {
			nodeR = node;
			nodefind = false;
		}
		return sum;
	}
	
	
	public class LCADeepestLeaves {
		
		private class NodeVal {
			public TreeNode lowestCommonAncestor;//The lowestCommonAncestor of the deepest leaves on the current subtree
			public int nodeDepth;   //The nodeDepth of the deepest leaves on the current subtree
			
			NodeVal(int d, TreeNode n) {
				nodeDepth = d;
				lowestCommonAncestor = n;
			}
		}
		
		public TreeNode lcaDeepestLeaves(TreeNode root) {
			NodeVal res = find(root);
			return res.lowestCommonAncestor;
		}
		
		private NodeVal find(TreeNode root) {
			if (root == null) {
				return new NodeVal(0, null);
			} else {
				NodeVal left = find(root.left);
				NodeVal right = find(root.right);
				
				if (left.nodeDepth == right.nodeDepth) {
					return new NodeVal(left.nodeDepth + 1, root);
				} else {
					return new NodeVal(Math.max(right.nodeDepth, left.nodeDepth) + 1, right.nodeDepth > left.nodeDepth ?
					                                                                  right.lowestCommonAncestor : left.lowestCommonAncestor);
				}
			}
		}
	}
}
