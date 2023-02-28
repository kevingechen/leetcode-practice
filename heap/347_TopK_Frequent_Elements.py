class Solution:

    def topKFrequentElemnts(self, nums: List[int], k: int) -> List[int]:
        num_map = {}
        for num in nums:
            num_map.setdefault(num, 0)
            num_map[num] += 1
        return [item[0] for item in sorted(num_map.items(), key = lambda item: item[1], reverse = True)[:k]]

