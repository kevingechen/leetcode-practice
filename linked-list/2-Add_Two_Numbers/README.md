# 2. Add Two Numbers

## Description

You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in **reverse order**, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

**Example 1:**
```
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
```

**Example 2:**
```
Input: l1 = [0], l2 = [0]
Output: [0]
```

**Example 3:**
```
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
```

**Constraints:**
+ The number of nodes in each linked list is in the range `[1, 100]`.
+ `0 <= Node.val <= 9`
+ It is guaranteed that the list represents a number that does not have leading zeros.

## Solution

### Java
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0, null);
        int carry = 0, current_sum = 0;
        ListNode move = dummy, move1 = l1, move2 = l2;
        ListNode moveNext = null;
        while (null != move1 || null != move2) {
            current_sum = carry;
            move.next = new ListNode(0, null);
            if (null != move1) {
                current_sum += move1.val;
                move1 = move1.next;
            }
            if (null != move2) {
                current_sum += move2.val;
                move2 = move2.next;
            }
            if (current_sum > 9) {
                move.next.val = current_sum - 10;
                carry = 1;
            } else {
                move.next.val = current_sum;
                carry = 0;
            }
            move = move.next;
        }

        if (carry > 0) {
            move.next = new ListNode(carry, null); 
        }

        return dummy.next;
    }
}
```

### Python3
```python
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
```
