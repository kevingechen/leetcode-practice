# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1, l2):
        if l1 is None:
            return l2
        if l2 is None:
            return l1
        carry = 0
        move1 = l1
        move2 = l2
        dummy = ListNode(0, None)
        move = dummy
        while move1 is not None or move2 is not None:
            move_next, carry_next = self.addTwoCurrentNode(move1, move2, carry)
            move.next = move_next
            carry = carry_next
            move = move_next
            if move1 is not None:
                move1 = move1.next
            if move2 is not None:
                move2 = move2.next
 
        if carry > 0:
            move.next = ListNode(carry, None)
        
        return dummy.next
        
    def addTwoCurrentNode(self, n1, n2, carry):
        current_sum = carry
        new_node = ListNode(0, None)
        new_carry = 0
        if n1 is not None:
            current_sum += n1.val
        if n2 is not None:
            current_sum += n2.val
        if current_sum > 9:
            new_node.val = current_sum - 10
            new_carry = 1
        else:
            new_node.val = current_sum
        return new_node, new_carry
