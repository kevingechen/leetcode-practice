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
