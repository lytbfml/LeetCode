package algorithms.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao Wang on 8/17/2019
 */
public class Maximum_Level_Sum_of_Binary_Tree {
	
	public int maxLevelSum(TreeNode root) {
        Map<Integer, Integer> res = new HashMap<>();
        helper(root, 1, res);
        int resVal = Integer.MIN_VALUE, resLevel = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> e : res.entrySet()) {
            if (e.getValue() > resVal) {
                resVal = e.getValue();
                resLevel = e.getKey();
            } else if (e.getValue() == resVal) {
                resLevel = Math.min(e.getKey(), resLevel);
            }
        }
        return resLevel;
    }
    
    private void helper(TreeNode node, int level, Map<Integer, Integer> res) {
        if (node == null) return;
        res.put(level, res.getOrDefault(level, 0) + node.val);
        helper(node.left, level + 1, res);
        helper(node.right, level + 1, res);
    }
}
