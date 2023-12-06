/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxPathSum(TreeNode root) {
        if (null == root) {
            return 0;
        }

        int result = root.val +
                        Math.max(maxSubPath(root.left), 0) +
                        Math.max(maxSubPath(root.right), 0);
        if (null != root.left) {
            result = Math.max(result, maxPathSum(root.left));
        }

        if (null != root.right) {
            result = Math.max(result, maxPathSum(root.right));
        }
        
        return result;
    }

    private int maxSubPath(TreeNode root) {
        if (null == root) {
            return 0;
        }

        return Math.max(
                root.val + Math.max(maxSubPath(root.left), 0),
                root.val + Math.max(maxSubPath(root.right), 0)
        );
    }
}
