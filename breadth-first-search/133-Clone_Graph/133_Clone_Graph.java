/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (null == node) {
            return null;
        }

        Map<Integer, Node> clonedNodeMap = new HashMap<>();
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(node);
        Node deepClonedRoot = new Node(node.val);
        clonedNodeMap.put(node.val, deepClonedRoot);
        Node current, clonedNode, clonedNeighbor;
        while (!queue.isEmpty()) {
            current = queue.poll();
            clonedNode = clonedNodeMap.get(current.val);
            for (Node neighbor : current.neighbors) {
                clonedNeighbor = clonedNodeMap.get(neighbor.val);
                if (null == clonedNeighbor) {
                    clonedNeighbor = new Node(neighbor.val);
                    clonedNodeMap.put(neighbor.val, clonedNeighbor);
                    queue.offer(neighbor);
                }
                clonedNode.neighbors.add(clonedNeighbor);
            }
        }

        return deepClonedRoot;
    }
}
