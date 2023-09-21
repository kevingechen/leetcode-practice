# 378. Kth Smallest Element In a Sorted Matrix

## Description
Given an `n x n` `matrix` where each of the rows and columns is sorted in ascending order, return the `$k^{th}$` smallest element in the matrix.

Note that it is the `$k^{th}$` smallest element in the sorted order, not the `$k^{th}$`  distinct element.

You must find a solution with a memory complexity better than `O(n^2)`.

**Example 1:**
> Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
> Output: 13
> Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

**Example 2:**
> Input: matrix = [[-5]], k = 1
> Output: -5

**Constraints:**
+ `n == matrix.length == matrix[i].length`
+ `1 <= n <= 300`
+ `-109 <= matrix[i][j] <= 109`
+ All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
+ `1 <= k <= n2`


## Solution in Python3
```python
class HeapNode:
    def __init__(self, num: int, row: int, col:int):
        self.num = num
        self.row = row
        self.col = col

class Solution:
    def kthSmallest(self, matrix: List[List[int]], k: int) -> int:
        n = len(matrix)
        heap = [HeapNode(matrix[0][0], 0, 0)]
        hsize = 1
        for i in range(1, k):
            if heap[0].col == 0 and heap[0].row < n-1:
                heap.append(HeapNode(matrix[heap[0].row + 1][0], heap[0].row+1, 0))
                hsize += 1
                self.adjustUp(heap, hsize-1)
            if heap[0].col == n - 1:
                heap[0] = heap[hsize - 1]
                heap.pop()
                hsize -= 1
            else:
                heap[0].col += 1
                heap[0].num = matrix[heap[0].row][heap[0].col]
            self.adjustDown(heap, hsize, 0)
        return heap[0].num
    
    def adjustUp(self, heap: List[int], idx: int):
        parent_idx = int((idx - 1) / 2)
        while parent_idx >= 0:
            if heap[parent_idx].num <= heap[idx].num:
                break
            heap[parent_idx], heap[idx] = heap[idx], heap[parent_idx]
            idx = parent_idx
            parent_idx = int((idx - 1) / 2)
    
    def adjustDown(self, heap: List[int], hsize: int, idx: int):
        left_child_idx = idx * 2 + 1
        right_child_idx = idx * 2 + 2
        while left_child_idx < hsize:
            next_idx = idx
            if heap[left_child_idx].num < heap[next_idx].num:
                 next_idx = left_child_idx
            if right_child_idx < hsize and heap[right_child_idx].num < heap[next_idx].num:
                next_idx = right_child_idx
            if next_idx == idx: break
            heap[idx], heap[next_idx] = heap[next_idx], heap[idx]
            idx = next_idx
            left_child_idx = idx * 2 + 1
            right_child_idx = idx * 2 + 2
```
