class Solution:
    def removeDuplicates(self, s: str, k: int) -> str:
        result = s
        need_traverse = True
        while need_traverse:
            need_traverse = False
            dup_count = 1
            stack =[]
            for char in result:
                if len(stack) == 0 or char != stack[-1]:
                    dup_count = 1
                    stack.append(char)
                else:
                    dup_count += 1
                    if dup_count == k:
                        dup_count = 1
                        need_traverse = True
                        for i in range(k-1):
                            stack.pop()
                    else:
                        stack.append(char)
            result = "".join(stack)
        return result
