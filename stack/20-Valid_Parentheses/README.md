# 20. Valid Parentheses

## Description

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

**Example 1:**
```
Input: s = "()"
Output: true
```

**Example 2:**
```
Input: s = "()[]{}"
Output: true
```


**Example 3:**
```
Input: s = "(]"
Output: false
```

**Constraints:**
+ `1 <= s.length <= 10^4`
+ `s` consists of parentheses only `'()[]{}'`.

## Solution

### Python3
```python
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
```
