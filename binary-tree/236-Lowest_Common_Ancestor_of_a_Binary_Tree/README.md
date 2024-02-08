# [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/)

## Description
Given a binary tree, find the lowest common ancestor (LCA) node of two given nodes in the tree.

According to the [definition of LCA on Wikipedia](https://en.wikipedia.org/wiki/Lowest_common_ancestor): "lowest common ancestor is defined between two nodes `p` and `q` as the lowest node in `T` that has both `p` and `q` as descendants (where we allow **a node to be a descendant of itself**)."

**Example 1:**
```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```

**Example 2:**
```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
```

**Example 3**
```
Input: root = [1,2], p = 1, q = 2
Output: 1
```

**Constraints:**
+ The number of nodes in the tree is in the range `[2, 10^5]`.
+ `-10^9 <= Node.val <= 10^9`
+ All `Node.val` are **unique**.
+ `p != q`
+ `p` and `q` will exist in the BST.


## Solution
### Java
#### Recursive
```java
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
```

#### Non-Recursive
```java
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
```
