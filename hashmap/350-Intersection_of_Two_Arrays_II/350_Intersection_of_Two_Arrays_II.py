class Solution:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        result, nums1_map, nums2_map = [], {}, {}
        for num in nums1:
            nums1_map.setdefault(num, 0)
            nums1_map[num] += 1
        for num in nums2:
            nums2_map.setdefault(num, 0)
            nums2_map[num] += 1
        for num, count1 in nums1_map.items():
            count2 = nums2_map.get(num, 0)
            for i in range(min(count1, count2)):
                result.append(num)
        return result
