# [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/description/)

## Description
Given the `root` of a binary tree, return *the level order traversal of its nodes' values*. (i.e., from left to right, level by level).

**Example 1:**

![Exmaple 1](./example_1.jpg)
```
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
```

**Example 2:**
```
Input: root = [1]
Output: [[1]]
```

**Example 3:**
```
Input: root = []
Output: []
```

**Constraints:**
+ The number of nodes in the tree is in the range `[0, 2000]`.
+ `-1000 <= Node.val <= 1000`

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return result;
        }

        List<Deque<TreeNode>> deques = new ArrayList<>();
        Deque<TreeNode> producer, consumer;
        deques.add(new ArrayDeque<>());
        deques.add(new ArrayDeque<>());
        deques.get(0).add(root);
        TreeNode current;
        int layerNum = 1;
        int qIndex;
        while (!deques.get(0).isEmpty() || !deques.get(1).isEmpty()) {
            List<Integer> layerValues = new ArrayList<>();
            layerNum++;
            qIndex = layerNum % 2;
            producer = deques.get(qIndex);
            consumer = deques.get(1 - qIndex);
            while (!producer.isEmpty()) {
                current = producer.poll();
                layerValues.add(current.val);
                if (null != current.left) {
                    consumer.offer(current.left);
                }
                if (null != current.right) {
                    consumer.offer(current.right);
                }
            }
            result.add(layerValues);
        }

        return result;
    }

}
```

### CPP
```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    vector<vector<int>> levelOrder(TreeNode* root) {
        vector<vector<int>> result;
        if (nullptr == root) {
            return result;
        }

        deque<TreeNode*> prevLevel;
        deque<TreeNode*> currentLevel;
        prevLevel.push_back(root);
        while (!prevLevel.empty()) {
            vector<int> prevValues;
            while (!prevLevel.empty()) {
                const auto node = prevLevel.front();
                prevValues.push_back(node->val);
                if (nullptr != node->left) {
                    currentLevel.push_back(node->left);
                }
                if (nullptr != node->right) {
                    currentLevel.push_back(node->right);
                }
                prevLevel.pop_front();
            }
            result.push_back(prevValues);
            swap(prevLevel, currentLevel);
        }

        return result;
    }
};
```
