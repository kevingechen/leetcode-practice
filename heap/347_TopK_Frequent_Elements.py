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
