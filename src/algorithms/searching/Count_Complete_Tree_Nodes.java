package algorithms.searching;

import algorithms.tree.TreeNode;

/**
 * @author Yangxiao Wang on 2019-07-22
 */
public class Count_Complete_Tree_Nodes {
	
	public int countNodes(TreeNode root) {
		return root == null ? 0 : 1 + countNodes(root.left) + countNodes(root.right);
	}
	
	public class Solution_2 {
		
		public int countNodes(TreeNode root) {
			
			int leftDepth = leftDepth(root);
			int rightDepth = rightDepth(root);
			
			if (leftDepth == rightDepth)
				return (1 << leftDepth) - 1;
			else
				return 1 + countNodes(root.left) + countNodes(root.right);
			
		}
		
		private int rightDepth(TreeNode root) {
			int dep = 0;
			while (root != null) {
				root = root.right;
				dep++;
			}
			return dep;
		}
		
		private int leftDepth(TreeNode root) {
			int dep = 0;
			while (root != null) {
				root = root.left;
				dep++;
			}
			return dep;
		}
	}
	
	class Solution {
		int height(TreeNode root) {
			return root == null ? -1 : 1 + height(root.left);
		}
		
		public int countNodes(TreeNode root) {
			int h = height(root);
			return h < 0 ? 0 :
			       height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
			                                   : (1 << h - 1) + countNodes(root.left);
		}
	}
	
	class Solution_iterative {
		int height(TreeNode root) {
			return root == null ? -1 : 1 + height(root.left);
		}
		
		public int countNodes(TreeNode root) {
			int nodes = 0, h = height(root);
			while (root != null) {
				if (height(root.right) == h - 1) {
					nodes += 1 << h;
					root = root.right;
				} else {
					nodes += 1 << h - 1;
					root = root.left;
				}
				h--;
			}
			return nodes;
		}
	}
	
	public int countNodes_binarySearch(TreeNode root) {
		if (root == null) return 0;
		int num = 1;
		TreeNode left = root.left;
		TreeNode right = root.left;
		
		while (right != null) {
			left = left.left;
			right = right.right;
			num = num << 1;
		}
		
		return num + ((left == null) ? countNodes(root.right) : countNodes(root.left));
	}
}
