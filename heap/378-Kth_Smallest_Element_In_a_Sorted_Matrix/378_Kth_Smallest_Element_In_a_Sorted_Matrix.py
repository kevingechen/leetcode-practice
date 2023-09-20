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
