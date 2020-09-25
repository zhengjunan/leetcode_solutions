import java.util.HashMap;
import java.util.Map;
/*
 * @lc app=leetcode.cn id=106 lang=java
 *
 * [106] 从中序与后序遍历序列构造二叉树
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    int post_idx;
    int[] postorder;
    int[] inorder;
    Map<Integer, Integer> idx_map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;

        post_idx = postorder.length - 1;
        //
        int index = 0;
        for (Integer val : inorder) {
            idx_map.put(val, index++);
        }
        return helper(0, inorder.length - 1);
    }

    public TreeNode helper(int in_left, int in_right) {
        if (in_left > in_right) {
            return null;
        }
        //
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);
        //
        int index = idx_map.get(root_val);
        post_idx--;
        root.right = helper(index + 1, in_right);
        root.left = helper(in_left, index - 1);
        return root;
    }
}
// @lc code=end
