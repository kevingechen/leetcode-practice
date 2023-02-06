# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def partition(self, head, x):
        dummy_left = ListNode(0, None)
        dummy_right = ListNode(0, None)
        move, move_left, move_right = head, dummy_left, dummy_right
        while move:
            if move.val < x:
                move_left.next = move
                move_left = move
            else:
                move_right.next = move
                move_right = move
            move = move.next
        # important tail operation to avoid cycle
        move_left.next, move_right.next = None, None
        # connect left and right lists
        move_left.next = dummy_right.next
        return dummy_left.next
