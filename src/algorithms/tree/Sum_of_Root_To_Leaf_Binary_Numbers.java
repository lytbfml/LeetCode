package algorithms.tree;


/**
 * @author Yangxiao on 4/6/2019.
 */
public class Sum_of_Root_To_Leaf_Binary_Numbers {
	
	int mod = 1000000007;
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(0);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(1);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(1);
		Sum_of_Root_To_Leaf_Binary_Numbers cs = new Sum_of_Root_To_Leaf_Binary_Numbers();
		System.out.println(cs.sumRootToLeaf(root));
		String m1 = "11100001011100011011011010100011011";
		String m2 = "111000010111000110010110110111111011";
		long x1 = cs.convert(m1);
		long x2 = cs.convert(m2);
		System.out.println(x1);
		System.out.println(x2);
		System.out.println((x1 % 1000000007 + x2 % 1000000007));
		
	}
	
	public int sumRootToLeaf(TreeNode root) {
		return helper(root, "");
	}
	
	public int helper(TreeNode root, String str) {
		int left = 0, right = 0;
		if (root.right == null && root.left == null) {
			return convert(str + root.val);
		} else {
			if (root.left != null) {
				left = helper(root.left, str + root.val);
			}
			if (root.right != null) {
				right = helper(root.right, str + root.val);
			}
		}
		return (left + right) % 1000000007;
	}
	
	public int convert(String s) {
		int n = s.length();
		int res = 0;
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '1') {
				res += Math.pow(2, n - i - 1) % 1000000007;
			}
		}
		return res;
	}
	
	public int sumRootToLeaf_Sol(TreeNode root) {
		return dfs(root, 0);
	}
	
	public int dfs(TreeNode root, int val) {
		if (root == null) { return 0; }
		val = (val * 2 + root.val) % mod;
		return (root.left == root.right ? val : dfs(root.left, val) + dfs(root.right, val)) % mod;
	}
	
	
	public static class TreeNode {
		
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) { val = x; }
	}
}
