# 239. Sliding Window Maximum

## Description
You are given an array of integers `nums`, there is a sliding window of size `k` which is moving from the very left of the array to the very right. You can only see the `k` numbers in the window. Each time the sliding window moves right by one position.

Return *the max sliding window*.

**Example 1:**
```
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```


**Example 2:**
```
Input: nums = [1], k = 1
Output: [1]
```

**Constraints:**
+ `1 <= nums.length <= 10^5`
+ `-10^4 <= nums[i] <= 10^4`
+ `1 <= k <= nums.length`


## Solution

### Python3
```python
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
            self.adjustUp(pq, i)
        results.append(pq[0].num)
        for i in range(k, len(nums)):
            node = nums_pq_map[i - k]
            node.num = nums[i]
            node.nums_idx = i
            nums_pq_map[i] = node
            del nums_pq_map[i - k]
            self.adjustUp(pq, node.pq_idx)
            self.adjustDown(pq, k, node.pq_idx)
            results.append(pq[0].num)
        return results

    def adjustUp(self, pq: List[int], pq_idx: int):
        parent_idx = int((pq_idx - 1) / 2)
        while parent_idx >= 0:
            node = pq[pq_idx]
            pnode = pq[parent_idx]
            if pnode.num >= node.num:
                break
            pq[parent_idx], pq[pq_idx] = pq[pq_idx], pq[parent_idx]
            node.pq_idx, pnode.pq_idx = parent_idx, pq_idx
            pq_idx = parent_idx
            parent_idx = int((pq_idx - 1) / 2)
        return
    
    def adjustDown(self, pq: List[int], pq_size: int, pq_idx: int):
        left_child_idx = 2 * pq_idx + 1
        right_child_idx = 2 * pq_idx + 2
        while left_child_idx < pq_size:
            next_idx = pq_idx
            if pq[left_child_idx].num > pq[next_idx].num:
                next_idx = left_child_idx
            if right_child_idx < pq_size and pq[right_child_idx].num > pq[next_idx].num:
                next_idx = right_child_idx
            if next_idx == pq_idx: break
            pq[pq_idx], pq[next_idx] = pq[next_idx], pq[pq_idx]
            pq[pq_idx].pq_idx, pq[next_idx].pq_idx = pq_idx, next_idx
            pq_idx = next_idx
            left_child_idx = 2 * pq_idx + 1
            right_child_idx = 2 * pq_idx + 2
```
