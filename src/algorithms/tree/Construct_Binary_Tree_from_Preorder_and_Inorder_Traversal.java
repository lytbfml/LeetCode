package algorithms.tree;

import java.util.*;

/**
 * @author Yangxiao on 5/15/2019.
 */
public class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
	
	public static void main(String[] args) {
		Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal cs = new Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal();
		int[] pre = {3, 9, 20, 15, 7};
		int[] pp = {9, 3, 15, 20, 7};
		
		// cs.buildTree(pre, pp);
	}
	
	class Solution {
		
		public TreeNode buildTree(int[] pre, int[] in) {
			if (pre == null || in == null) {
				return null;
			}
			int n = pre.length;
			if (n == 0 || in.length != n) {
				return null;
			}
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < n; i++) {
				map.put(in[i], i);
			}
			
			return helper(pre, map, 0, n, 0);
		}
		
		public TreeNode helper(int[] pre, Map<Integer, Integer> map, int left, int right, int i) {
			TreeNode cur = new TreeNode(pre[i]);
			int ind = map.get(pre[i]);
			int leftN = ind - left;
			if (leftN > 0) {
				cur.left = helper(pre, map, left, ind, i + 1);
			}
			if (right - ind > 1) {
				cur.right = helper(pre, map, ind + 1, right, i + leftN + 1);
			}
			
			return cur;
		}
	}
	
	class Solution_fast {
		int pIndex = 0;
		int iIndex = 0;
		
		public TreeNode buildTree(int[] preorder, int[] inorder) {
			if (preorder.length == 0)
				return null;
			return buildTree(preorder, inorder, 19260817);
		}
		
		private TreeNode buildTree(int[] preorder, int[] inorder, int rootVal) {
			if (pIndex >= preorder.length || iIndex >= inorder.length || rootVal == inorder[iIndex])
				return null;
			TreeNode root = new TreeNode(preorder[pIndex++]);
			root.left = buildTree(preorder, inorder, root.val);
			iIndex++;
			root.right = buildTree(preorder, inorder, rootVal);
			return root;
		}
	}
	
	class Solution_nomap {
		private int in = 0;
		private int pre = 0;
		
		public TreeNode buildTree(int[] preorder, int[] inorder) {
			return build(preorder, inorder, Integer.MIN_VALUE);
		}
		
		private TreeNode build(int[] preorder, int[] inorder, int stop) {
			if (pre >= preorder.length)
				return null;
			if (inorder[in] == stop) {
				in++;
				return null;
			}
			TreeNode node = new TreeNode(preorder[pre++]);
			node.left = build(preorder, inorder, node.val);
			node.right = build(preorder, inorder, stop);
			return node;
		}
	}
}
