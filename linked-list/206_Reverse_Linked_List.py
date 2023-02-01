# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseList(self, head):
        move = head
        new_head = None
        while move:
            tmp = move.next
            move.next = new_head
            new_head = move
            move = tmp
        return new_head
