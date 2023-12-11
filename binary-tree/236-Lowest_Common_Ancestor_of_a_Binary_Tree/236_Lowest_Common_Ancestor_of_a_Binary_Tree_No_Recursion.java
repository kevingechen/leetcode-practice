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
        List<TreeNode> pathP = getPathToNodeNoRecursion(root, p);
        List<TreeNode> pathQ = getPathToNodeNoRecursion(root, q);

        return locateLowestCommonAncestorFromPaths(pathP, pathQ);
    }

    private List<TreeNode> getPathToNodeNoRecursion(TreeNode root, TreeNode n) {
        Deque<TreeNode> dfsStack = new ArrayDeque<>();
        Set<TreeNode> visitedNodeSet = new HashSet<>();

        TreeNode move = root;
        TreeNode current;
        while (null != move) {
            dfsStack.push(move);
            move = move.left;
        }

        while (!dfsStack.isEmpty()) {
            current = dfsStack.peekFirst();
            if (visitedNodeSet.contains(current) || isLeaf(current)) {
                if (n == current) {
                    return new ArrayList<>(dfsStack);
                }
                dfsStack.pop();
                continue;
            }
            visitedNodeSet.add(current);
            move = current.right;
            while (null != move) {
                dfsStack.push(move);
                move = move.left;
            }

        }

        // should not arrive here
        throw new IllegalStateException("target node not found in the tree");
    }

    private TreeNode locateLowestCommonAncestorFromPaths(List<TreeNode> pathP, List<TreeNode> pathQ) {
        TreeNode lca = null;
        boolean pathPIsLonger = pathP.size() > pathQ.size();
        int sizeDiff = Math.abs(pathP.size() - pathQ.size());
        for (int i = 0 ; i < Math.min(pathP.size(), pathQ.size()); i ++) {
            if (pathPIsLonger && pathP.get(i+sizeDiff) == pathQ.get(i)) {
                lca = pathP.get(i+sizeDiff);
                break;
            } else if (!pathPIsLonger && pathP.get(i) == pathQ.get(i+sizeDiff)) {
                lca = pathP.get(i);
                break;
            }
        }

        return lca;
    }

    private boolean isLeaf(TreeNode node) {
        return null == node.left && null == node.right;
    }
}
