package algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Yangxiao Wang on 2019-08-03
 */
public class Binary_Tree_Coloring_Game {
	
	public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode firs = null;
		int cnt = 0;
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode cur = q.poll();
			if (cur.val == x) {
				firs = cur;
				continue;
			}
			if (cur.left != null) {
				q.add(cur.left);
			}
			if (cur.right != null) {
				q.add(cur.right);
			}
			cnt++;
		}
		
		int lef = 0, rig = 0;
		Queue<TreeNode> qq = new LinkedList<>();
		if (firs.left != null) {
			qq.add(firs.left);
			while (!qq.isEmpty()) {
				TreeNode cur = qq.poll();
				if (cur.left != null) {
					qq.add(cur.left);
				}
				
				if (cur.right != null) {
					qq.add(cur.right);
				}
				lef++;
			}
		}
		
		Queue<TreeNode> qr = new LinkedList<>();
		if (firs.right != null) {
			qr.add(firs.right);
			while (!qr.isEmpty()) {
				TreeNode cur = qr.poll();
				if (cur.left != null) {
					qr.add(cur.left);
				}
				
				if (cur.right != null) {
					qr.add(cur.right);
				}
				rig++;
			}
		}
		
		System.out.println(lef + " " + rig + " " + firs.val);
		
		if (firs.val == root.val) {
			if (lef > rig + 1 || rig > lef + 1) {
				return true;
			} else return false;
		}
		
		if (cnt < (rig + lef + 1)) {
			if (rig < (cnt + lef + 1) && lef < (cnt + rig + 1)) {
				return false;
			}
		}
		
		return true;
	}
	
	class Solution {
		
		boolean res = true;
		
		public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
			helper(root, n, x);
			return res;
		}
		
		private int helper(TreeNode node, int n, int x) {
			if (node == null) return 0;
			int lef = helper(node.left, n, x);
			int rig = helper(node.right, n, x);
			int cnt = 1 + lef + rig;
			if (node.val == x) {
				if (cnt > n - cnt && lef < n - lef && rig < n - rig) {
					res = false;
				}
			}
			
			return cnt;
		}
	}
}
