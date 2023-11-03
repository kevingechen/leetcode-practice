# 21. Merge Two Sorted Lists

## Description

You are given the heads of two sorted linked lists `list1` and `list2`.

Merge the two lists into one **sorted** list. The list should be made by splicing together the nodes of the first two lists.

Return *the head of the merged linked list*.

**Example 1:**
```
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
```

**Example 2:**
```
Input: list1 = [], list2 = []
Output: []
```

**Example 3:**
```
Input: list1 = [], list2 = [0]
Output: [0]
```

**Constraints:**
+ The number of nodes in both lists is in the range `[0, 50]`.
+ `-100 <= Node.val <= 100`
+ Both `list1` and `list2` are sorted in **non-decreasing** order.


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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0, null);
        ListNode move1 = list1, move2 = list2, move = dummy;
        while (null != move1 && null != move2) {
            if (move1.val < move2.val) {
                move.next = move1;
                move1 = move1.next;
            } else {
                move.next = move2;
                move2 = move2.next;
            }
            move = move.next;
        }
        
        if (null != move1) {
            move.next = move1;
        }
        if (null != move2) {
            move.next = move2;
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
```
