class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        result = 0
        num_visited_map = {}
        for num in nums:
            num_visited_map[num] = False
        for num in nums:
            if num_visited_map[num]:
                continue
            num_visited_map[num] = True
            right = self.move_forward(num, num_visited_map, 1)
            left = self.move_forward(num, num_visited_map, -1)
            result = max(result, right - left + 1)
        return result
    
    def move_forward(self, pos, num_visited_map, delta):
        final_pos = pos
        while True:
            if (final_pos + delta) not in num_visited_map:
                break
            final_pos += delta
            num_visited_map[final_pos] = True
        return final_pos
