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


# Your MyStack object will be instantiated and called as such:
# obj = MyStack()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.top()
# param_4 = obj.empty()
