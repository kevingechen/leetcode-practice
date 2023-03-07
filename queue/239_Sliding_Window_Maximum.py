class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        results = []
        max_queue = []
        qsize = 0
        for i in range(k - 1):
            self.updateMaxQueue(max_queue, qsize, nums[i])
            qsize += 1
        for i in range(k - 1, len(nums)):
            self.updateMaxQueue(max_queue, qsize, nums[i])
            results.append( max_queue.pop(0) )
        return results
    
    def updateMaxQueue(self, max_queue: List[int], qsize: int, num: int):
        for i in range(qsize-1, -1, -1):
            if max_queue[i] >= num: break
            max_queue[i] = num
        max_queue.append(num)
