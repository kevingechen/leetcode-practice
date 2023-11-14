# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def detectCycle(self, head):
        slow = head
        fast = head
        has_cycle = False
        # move fast twice while slow once each time
        while fast:
            slow = slow.next
            fast = fast.next
            if not fast:
                return None
            fast = fast.next
            if slow == fast:
                has_cycle = True
                break
        if not has_cycle:
            return None
        else:
            # calculate length of cycle
            cycle_len = 1
            while fast.next != slow:
                cycle_len += 1
                fast = fast.next
            # reset slow/fast and let fast ahead slow by the length of cycle
            slow = head
            fast = head
            for i in range(cycle_len):
                fast = fast.next
            # move both slow and fast till they meet at cycle entry
            while slow != fast:
                slow = slow.next
                fast = fast.next
            return slow
