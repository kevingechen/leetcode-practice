# [103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/)

## Description
Given the `root` of a binary tree, return *the zigzag level order traversal of its nodes' values*. (i.e., from left to right, then right to left for the next level and alternate between).

**Example 1:**

![Example 1](./example_1.jpg)
```
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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
                if (0 == qIndex) {
                    current = producer.pop();
                    layerValues.add(current.val);
                    if (null != current.left) {
                        consumer.push(current.left);
                    }
                    if (null != current.right) {
                        consumer.push(current.right);
                    }
                } else {
                    current = producer.pop();
                    layerValues.add(current.val);
                    if (null != current.right) {
                        consumer.push(current.right);
                    }
                    if (null != current.left) {
                        consumer.push(current.left);
                    }
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
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        vector<vector<int>> result;
        if (nullptr == root) {
            return result;
        }
        
        deque<TreeNode*> deques[2];
        deques[0].push_back(root);
        int layerNum = 0;
        int qIndex;

        while (!deques[0].empty() || !deques[1].empty()) {
            vector<int> layerValues;
            qIndex = layerNum % 2;
            auto& producer = deques[qIndex];
            auto& consumer = deques[1 - qIndex];
            while (!producer.empty()) {
                TreeNode* current = producer.front();
                producer.pop_front();
                layerValues.push_back(current->val);
                if (0 == qIndex) {
                    if (nullptr != current->left) {
                        consumer.push_front(current->left);
                    }
                    if (nullptr != current->right) {
                        consumer.push_front(current->right);
                    }
                } else {
                    if (nullptr != current->right) {
                        consumer.push_front(current->right);
                    }
                    if (nullptr != current->left) {
                        consumer.push_front(current->left);
                    }
                }
            }
            result.push_back(layerValues);
            layerNum++;
        }

        return result;
    }
};
```
