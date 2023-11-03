# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeTwoLists(self, list1, list2):
        dummy = ListNode(0, None)
        move, move1, move2 = dummy, list1, list2
        while move1 and move2:
            if move1.val < move2.val:
                move.next = move1
                move1 = move1.next
            else:
                move.next = move2
                move2 = move2.next
            move = move.next
        if move1 is not None:
            move.next = move1
        if move2 is not None:
            move.next = move2
        return dummy.next
