package algorithms.tree;

/**
 * @author Yangxiao Wang on 5/16/2019
 */
public class Populating_Next_Right_Pointers_in_Each_Node {
	
	public Node connect(Node root) {
		if (root == null) {
			return root;
		}
		Node node = root;
		while (node != null) {
			Node next = node;
			
			while (next != null) {
				if (next.left != null) {
					next.left.next = next.right;
				}
				if (next.right != null && next.next != null) {
					next.right.next = next.next.left;
				}
				next = next.next;
			}
			node = node.left;
		}
		return root;
	}
	
	public Node connect_rec(Node root) {
		if (root == null)
			return null;
		helper(root.left, root.right);
		return root;
	}
	
	private void helper(Node left, Node right) {
		if (left == null || right == null) {
			return;
		}
		
		left.next = right;
		helper(left.left, left.right);
		helper(left.right, right.left);
		helper(right.left, right.right);
	}
	
	
	class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;
		
		public Node() {}
		
		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	}
}
