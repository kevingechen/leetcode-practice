# [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/description/)

## Description
Given the `root` of a binary tree, *check whether it is a mirror of itself* (i.e., symmetric around its center).

**Example 1:**
```
Input: root = [1,2,2,3,4,4,3]
Output: true
```

**Example 2:**
```
Input: root = [1,2,2,null,3,null,3]
Output: false
```

**Constraints:**
+ The number of nodes in the tree is in the range `[1, 1000]`.
+ `-100 <= Node.val <= 100`


## Solution

### Java
```java
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
```
