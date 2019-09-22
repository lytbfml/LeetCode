package algorithms.tree;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Yangxiao Wang on 4/30/2019
 */
public class Binary_Tree_Zigzag_Level_Order_Traversal {
	
	public static void main(String[] args) {
	
	}
	
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new LinkedList<>();
		if (root == null)
			return res;
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		boolean left = true;
		int size = 1;
		
		while (!q.isEmpty()) {
			List<Integer> temp = new LinkedList<>();
			
			for (int i = 0; i < size; i++) {
				TreeNode node = q.poll();
				if (left) {
					temp.add(node.val);
				} else {
					temp.add(0, node.val);
				}
				if (node.left != null) {
					q.offer(node.left);
				}
				if (node.right != null) {
					q.offer(node.right);
				}
			}
			res.add(temp);
			size = q.size();
			left = !left;
		}
		return res;
	}
	
}
