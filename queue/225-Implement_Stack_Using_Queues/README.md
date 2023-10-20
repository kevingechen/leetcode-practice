# 225. Implement Stack Using Queues

## Description
Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (`push`, `top`, `pop`, and `empty`).

Implement the `MyStack` class:

+ `void push(int x)` Pushes element x to the top of the stack.
+ `int pop()` Removes the element on the top of the stack and returns it.
+ `int top()` Returns the element on the top of the stack.
+ `boolean empty()` Returns `true` if the stack is empty, `false` otherwise.

**Notes:**

+ You must use **only** standard operations of a queue, which means that only `push to back`, `peek/pop from front`, `size` and `is empty` operations are valid.
+ Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.

**Example 1:**
```
Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False
```

**Constraints:**
+ `1 <= x <= 9`
+ At most `100` calls will be made to `push`, `pop`, `top`, and `empty`.
+ All the calls to `pop` and `top` are valid.


## Solution

### Java
```java
class MyStack {

    private int stackSize;
    private List<Integer> q1;
    private List<Integer> q2;

    public MyStack() {
        this.stackSize = 0;
        this.q1 = new ArrayList<>();
        this.q2 = new ArrayList<>();
    }
    
    public void push(int x) {
        if (!q2.isEmpty()) {
            q2.add(x);
        } else {
            q1.add(x);
        }
        stackSize++;
    }
    
    public int pop() {
        stackSize--;
        if (!q2.isEmpty()) {
            for (int i = 0; i < stackSize; i++) {
                q1.add(q2.remove(0));
            }
            return q2.remove(0);
        } else {
            for (int i = 0; i < stackSize; i++) {
                q2.add(q1.remove(0));
            }
            return q1.remove(0);
        }
    }
    
    public int top() {
        if (!q2.isEmpty()) {
            return q2.get(q2.size() - 1);
        } else {
            return q1.get(q1.size() - 1);
        }
    }
    
    public boolean empty() {
        return 0 == stackSize;
    }
}
```

### Python3
```python
class MyStack:

    def __init__(self):
        self.stack_size = 0
        self.q1 = []
        self.q2 = []

    def push(self, x: int) -> None:
        if len(self.q2) > 0:
            self.q2.append(x)
        else:
            self.q1.append(x)
        self.stack_size += 1

    def pop(self) -> int:
        self.stack_size -= 1
        if len(self.q2) > 0:
            for i in range(self.stack_size):
                self.q1.append( self.q2.pop(0) )
            return self.q2.pop(0)
        else:
            for i in range(self.stack_size):
                self.q2.append( self.q1.pop(0) )
            return self.q1.pop(0)

    def top(self) -> int:
        if len(self.q2) > 0:
            return self.q2[-1]
        return self.q1[-1]

    def empty(self) -> bool:
        return self.stack_size == 0

```
