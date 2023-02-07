class Solution:
    def isValid(self, s: str) -> bool:
        paren_dict = {
            '(': ')',
            '{': '}',
            '[': ']'
        }
        stack = []
        ssize = 0
        for paren in s:
            if paren in paren_dict:
                stack.append(paren)
                ssize += 1
            else:
                if ssize == 0:
                    return False
                ssize -= 1
                if paren != paren_dict[stack.pop()]:
                    return False
        return ssize == 0
