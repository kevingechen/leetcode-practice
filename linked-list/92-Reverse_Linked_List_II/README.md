# 92. Reverse Linked List II

## Description

Given the `head` of a singly linked list and two integers `left` and `right` where `left` <= `right`, reverse the nodes of the list from position `left` to position `right`, and return **the reversed list**.


**Example 1:**
```
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
```

**Example 2:**
```
Input: head = [5], left = 1, right = 1
Output: [5]
```

**Constraints:**
+ The number of nodes in the list is `n`.
+ `1 <= n <= 500`
+ `-500 <= Node.val <= 500`
+ `1 <= left <= right <= n`

## Solution

### Python3
```python
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
```
