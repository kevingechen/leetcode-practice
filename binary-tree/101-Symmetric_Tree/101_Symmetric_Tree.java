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
    public boolean isSymmetric(TreeNode root) {
        return isValueEqualTree(root, buildMirrorTree(root));
    }

    public boolean isValueEqualTree(TreeNode root1, TreeNode root2) {
        if (null == root1 && null == root2) {
            return true;
        }
        if (null == root1 || null == root2) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }

        return isValueEqualTree(root1.left, root2.left) && isValueEqualTree(root1.right, root2.right);
    }

    public TreeNode buildMirrorTree(TreeNode root) {
        if (null == root) {
            return null;
        }

        return new TreeNode(root.val, buildMirrorTree(root.right), buildMirrorTree(root.left));
    }
}
