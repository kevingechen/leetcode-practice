class PriorityQueueNode:
    def __init__(self, num: int, nums_idx: int, pq_idx: int):
        self.num = num
        self.nums_idx = nums_idx
        self.pq_idx = pq_idx

class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
	pq = []
        results = []
        nums_pq_map = {}
        for i in range(k):
            node = PriorityQueueNode(nums[i], i, i)
            nums_pq_map[i] = node
            pq.append(node)
            self.adjustUp(pq, i, nums_pq_map)
        results.append(pq[0].num)
        for i in range(k, len(nums)):
            node = nums_pq_map[i - k]
            node.num = nums[i]
            node.nums_idx = i
            nums_pq_map[i] = node
            del nums_pq_map[i - k]
            self.adjustUp(pq, node.pq_idx, nums_pq_map)
            results.append(pq[0].num)
        return results

    def adjustUp(self, pq: List[int], pq_idx: int, nums_pq_map: Dict<int, int>):
        if pq_idx == 0: return
        parent_idx = int((pq_idx - 1) / 2)
        while parent_idx > 0:
            node = pq[pq_idx]
            pnode = pq[parent_idx]
            if pnode.num >= node.num:
                break
            pq[parent_idx], pq[pq_idx] = pq[pq_idx], pq[parent_idx]
            # TODO
            pq_idx = parent_idx
            parent_idx = int((pq_idx - 1) / 2)
        return
