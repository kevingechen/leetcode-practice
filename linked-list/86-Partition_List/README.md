# 86. Partition List

## Description

Given the `head` of a linked list and a value `x`, partition it such that all nodes less than x come before nodes **greater than or equal** to `x`.

You should **preserve** the original relative order of the nodes in each of the two partitions.

**Example 1:**
```
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
```

**Example 2:**
```
Input: head = [2,1], x = 2
Output: [1,2]
```

**Constraints:**
+ The number of nodes in the list is in the range `[0, 200]`.
+ `-100 <= Node.val <= 100`
+ `-200 <= x <= 200`


## Solution

### Java
```
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
    public ListNode partition(ListNode head, int x) {
        ListNode dummyLeft = new ListNode(0, null);
        ListNode dummyRight = new ListNode(0, null);
        ListNode move = head, moveLeft = dummyLeft, moveRight = dummyRight, tmp;
        while (null != move) {
            if (move.val < x) {
                moveLeft.next = move;
                moveLeft = move;
            } else {
                moveRight.next = move;
                moveRight = move;
            }
            move = move.next;
        }
        moveRight.next = null;
        moveLeft.next = dummyRight.next;
        return dummyLeft.next;
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
```
