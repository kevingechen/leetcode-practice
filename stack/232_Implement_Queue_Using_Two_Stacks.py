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
