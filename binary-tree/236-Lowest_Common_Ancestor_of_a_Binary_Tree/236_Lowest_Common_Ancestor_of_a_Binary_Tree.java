/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = getPathToNode(root, p);
        List<TreeNode> pathQ = getPathToNode(root, q);

        return locateLowestCommonAncestorFromPaths(pathP, pathQ);
    }

    private List<TreeNode> getPathToNode(TreeNode root, TreeNode n) {
        List<TreeNode> path = new ArrayList<>();
        if (null == root) {
            return path;
        }

        path.add(root);
        if (root == n) {
            return path;
        }

        List<TreeNode> leftPath = getPathToNode(root.left, n);
        if (!leftPath.isEmpty()) {
            path.addAll(leftPath);
        } else {
            List<TreeNode> rightPath = getPathToNode(root.right, n);
            if (!rightPath.isEmpty()) {
                path.addAll(rightPath);
            } else {
                path.remove(0);
            }
        }

        return path;
    }

    private TreeNode locateLowestCommonAncestorFromPaths(List<TreeNode> pathP, List<TreeNode> pathQ) {
        TreeNode lca = null;
        for (int i = 0 ; i < Math.min(pathP.size(), pathQ.size()); i ++) {
            if (pathP.get(i) == pathQ.get(i)) {
                lca = pathP.get(i);
            } else {
                break;
            }
        }

        return lca;
    }
}
