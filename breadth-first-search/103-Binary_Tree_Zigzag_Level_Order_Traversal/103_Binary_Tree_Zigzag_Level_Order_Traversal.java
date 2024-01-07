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
