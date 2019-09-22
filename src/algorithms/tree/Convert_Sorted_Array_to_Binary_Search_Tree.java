package algorithms.tree;

/**
 * @author Yangxiao on 12/19/2018.
 * <p>
 * Given an array where elements are sorted in ascending order, convert it to a height balanced
 * BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of
 * the two subtrees of every node never differ by more than 1.
 * <p>
 * Given the sorted array: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
public class Convert_Sorted_Array_to_Binary_Search_Tree {
	
	public TreeNode sortedArrayToBST(int[] nums) {
		return construct(nums, 0, nums.length - 1);
	}
	
	private TreeNode construct(int[] nums, int left, int right) {
		if (left > right) {
			return null;
		}
		if (left == right) {
			return new TreeNode(nums[left]);
		}
		int mid = left + (right - left) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = construct(nums, left, mid - 1);
		root.right = construct(nums, mid + 1, right);
		return root;
	}
	
	public TreeNode sortedArrayToBST_Sol(int[] nums) {
		return bst(nums, 0, nums.length - 1);
	}
	
	public TreeNode bst(int[] arr, int start, int end) {
		if (start > end) { return null; }
		int mid = (start + end) / 2;
		TreeNode node = new TreeNode(arr[mid]);
		node.left = bst(arr, start, mid - 1);
		node.right = bst(arr, mid + 1, end);
		return node;
	}
}






