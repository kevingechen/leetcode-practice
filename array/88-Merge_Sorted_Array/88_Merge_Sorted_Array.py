class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        pos, move1, move2 = m + n - 1, m - 1, n - 1
        while move1 >= 0 and move2 >= 0:
            if nums1[move1] > nums2[move2]:
                nums1[pos] = nums1[move1]
                move1 -= 1
            else:
                nums1[pos] = nums2[move2]
                move2 -= 1
            pos -= 1
            
        while move2 >= 0:
            nums1[pos] = nums2[move2]
            pos -= 1
            move2 -= 1
