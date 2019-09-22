package algorithms.tree;

/**
 * @author Yangxiao Wang on 5/16/2019
 */
public class Inorder_Successor_in_BST {
	
	TreeNode find = null;
	int yes = 0;
	TreeNode sec = null;
	
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		find = p;
		inorder(root);
		return sec;
	}
	
	private void inorder(TreeNode root) {
		if (root == null || yes == 2) {
			return;
		}
		
		if (root.val == find.val) {
			yes = 1;
			return;
		}
		
		inorder(root.left);
		if (yes == 1) {
			sec = root;
			yes = 2;
		}
		inorder(root.right);
	}
	
	public TreeNode successor(TreeNode root, TreeNode p) {
		if (root == null)
			return null;
		
		if (root.val <= p.val) {
			return successor(root.right, p);
		} else {
			TreeNode left = successor(root.left, p);
			return (left != null) ? left : root;
		}
	}
	
	public TreeNode predecessor(TreeNode root, TreeNode p) {
		if (root == null)
			return null;
		
		if (root.val >= p.val) {
			return predecessor(root.left, p);
		} else {
			TreeNode right = predecessor(root.right, p);
			return (right != null) ? right : root;
		}
	}
}
