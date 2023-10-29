# 232. Implement Queue using Stacks

## Description

Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (`push`, `peek`, `pop`, and `empty`).

Implement the `MyQueue` class:

+ `void push(int x)` Pushes element x to the back of the queue.
+ `int pop()` Removes the element from the front of the queue and returns it.
+ `int peek()` Returns the element at the front of the queue.
+ `boolean empty()` Returns `true` if the queue is empty, `false` otherwise.

**Notes:**
+ You must use `only` standard operations of a stack, which means only `push to top`, `peek/pop from top`, `size`, and `is empty` operations are valid.
+ Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.

**Example 1:**
```
Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
```

**Constraints:**
+ `1 <= x <= 9`
+ At most `100` calls will be made to `push`, `pop`, `peek`, and `empty`.
+ All the calls to `pop` and `peek` are valid.

## Solution

### Python3
```python
class MyQueue:

    def __init__(self):
        self.instack = []
        self.outstack = []

    def push(self, x: int) -> None:
        self.instack.append(x)

    def pop(self) -> int:
        if 0 == len(self.outstack):
            self.shuffle()
        return self.outstack.pop()

    def peek(self) -> int:
        if 0 == len(self.outstack):
            self.shuffle()
        return self.outstack[-1]

    def empty(self) -> bool:
        return 0 == len(self.instack) and \
                0 == len(self.outstack)
    
    def shuffle(self):
        insize = len(self.instack)
        for i in range(insize):
            self.outstack.append(self.instack.pop())
```
