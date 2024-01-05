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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return result;
        }

        Deque<TreeNode> prevLevel = new ArrayDeque<>();
        Deque<TreeNode> currentLevel = new ArrayDeque<>();
        Deque<TreeNode> tmp;
        prevLevel.add(root);
        while (!prevLevel.isEmpty()) {
            List<Integer> prevValues = new ArrayList<>();
            while (!prevLevel.isEmpty()) {
                TreeNode current = prevLevel.pollFirst();
                prevValues.add(current.val);
                if (null != current.left) {
                    currentLevel.offerLast(current.left);
                }
                if (null != current.right) {
                    currentLevel.offerLast(current.right);
                }
            }
            result.add(prevValues);
            tmp = prevLevel;
            prevLevel = currentLevel;
            currentLevel = tmp;
        }

        return result;
    }

}
