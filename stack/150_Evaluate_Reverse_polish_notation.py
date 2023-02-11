class Solution:
    def evalRPN(self, tokens: List):
        stack = []
        for token in tokens:
            match token:
                case '+':
                    op2, op1 = stack.pop(), stack.pop()
                    stack.append(op1 + op2)
                case '-':
                    op2, op1 = stack.pop(), stack.pop()
                    stack.append(op1 - op2)
                case '*':
                    op2, op1 = stack.pop(), stack.pop()
                    stack.append(op1 * op2)
                case '/':
                    op2, op1 = stack.pop(), stack.pop()
                    stack.append(int(op1 / op2))
                case _:
                    stack.append(int(token))
        return stack.pop()
