# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseBetween(self, head, left, right):
        if head is None or left == right:
            return head
        dummy = ListNode(0, head)
        sub_head_prev = dummy
        for i in range(left - 1):
            sub_head_prev = sub_head_prev.next

        sub_head = sub_head_prev.next
        sub_tail = sub_head_prev.next
        sub_head_prev.next = sub_tail.next

        for i in range(left, right):
            current = sub_head_prev.next
            sub_tail_next = current.next
            sub_head_prev.next = sub_tail_next
            current.next = sub_head
            sub_head = current

        sub_tail.next = sub_head_prev.next
        sub_head_prev.next = sub_head

        return dummy.next
