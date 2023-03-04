class Element:
    def __init__(self, value, count, index):
        self.val = value
        self.cnt = count
        self.idx = index

    def inc(self):
        self.cnt += 1

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        num_map = {}
        for num in nums:
            num_map.setdefault(num, 0)
            num_map[num] += 1
        num_counts = list(num_map.items())
        print(num_counts)
        self.arrangeKthFrequentItem(num_counts, k, 0, len(num_counts)-1)
        return [val for (val, count) in num_counts[:k]]

   def arrangeKthFrequentItem(self, num_counts: List[Tuple[int, int]], k: int, left: int, right: int) -> Tuple[int, int]:
        pivot = num_counts[left]
        pivot_idx = left
        for i in range(left+1, right+1):
            if num_counts[i][1] >= pivot[1]:
                num_counts[pivot_idx] = num_counts[i] 
                pivot_idx += 1
                num_counts[i] = num_counts[pivot_idx]
        num_counts[pivot_idx] = pivot
        pivot_k = pivot_idx - left + 1
        if pivot_k == k:
            return
        elif pivot_k < k:
            self.arrangeKthFrequentItem(num_counts, k - pivot_k, pivot_idx + 1, right)
        else:
            self.arrangeKthFrequentItem(num_counts, k, left, pivot_idx - 1) 

    def topKFrequentByHeap(self, nums: List[int], k: int) -> List[int]:
        heap = []
        elem_map = {}
        for num in nums:
            elem = elem_map.get(num, None)
            if elem is None:
                new_elem = Element(num, 1, len(heap))
                elem_map[num] = new_elem
                heap.append(new_elem)
            else:
                elem.inc()
                self.adjustUpwards(heap, elem)
                
        results = []
        for i in range(k):
            results.append(heap[0].val)
            last_elem = heap.pop()
            if len(heap) > 0:
                heap[0] = last_elem
                heap[0].idx = 0
                self.adjustDownwards(heap, heap[0])
        return results

    def adjustUpwards(self, heap: List[Element], elem: Element):
        parent_idx = int(elem.idx-1 / 2)
        while parent_idx >= 0:
            if heap[parent_idx].cnt >= elem.cnt:
                break
            heap[parent_idx], heap[elem.idx] = elem, heap[parent_idx]
            heap[elem.idx].idx = elem.idx
            elem.idx = parent_idx
            parent_idx = int(elem.idx-1 / 2)
    
    def adjustDownwards(self, heap: List[Element], elem: Element):
        hsize = len(heap)
        while 2 * elem.idx + 1 < hsize:
            next_idx = elem.idx
            left_child_idx = 2 * next_idx + 1
            right_child_idx = 2 * next_idx + 2
            if heap[left_child_idx].cnt > heap[next_idx].cnt:
                next_idx = left_child_idx
            if right_child_idx < hsize and heap[right_child_idx].cnt > heap[next_idx].cnt:
                next_idx = right_child_idx
            if next_idx == elem.idx: break
            heap[next_idx], heap[elem.idx] = elem, heap[next_idx]
            heap[elem.idx].idx = elem.idx
            elem.idx = next_idx
