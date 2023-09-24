class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        result = []
        value_map = {}
        for i in range(len(nums)):
            value = nums[i]
            residus = target - value
            if residus in value_map:
                return [value_map[residus], i]
            else:
                value_map[value] = i
        return []
