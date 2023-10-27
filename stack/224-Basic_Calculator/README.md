# 224. Basic Calculator

## Description

Given a string `s` representing a valid expression, implement a basic calculator to evaluate it, and return *the result of the evaluation*.

**Note**: You are **not** allowed to use any built-in function which evaluates strings as mathematical expressions, such as `eval()`.

**Example 1:**
```
Example 1:

Input: s = "1 + 1"
Output: 2
```

**Example 2:**
```
Input: s = " 2-1 + 2 "
Output: 3
```

**Example 3:**
```
Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23
```

**Constraints:**
+ `1 <= s.length <= 3 * 10^5`
+ `s` consists of digits, `'+'`, `'-'`, `'('`, `')'`, and `' '`.
+ `s` represents a valid expression.
+ `'+'` is **not** used as a unary operation (i.e., `"+1"` and `"+(2 + 3)"` is invalid).
+ `'-'` could be used as a unary operation (i.e., `"-1"` and `"-(2 + 3)"` is valid).
+ There will be no two consecutive operators in the input.
+ Every number and running calculation will fit in a signed 32-bit integer.

## Solution

### Python3
```python
class Solution:
    def calculate(self, s: str) -> int:
        elems = self.init_elems(s)
        elems_size = len(elems)
        rstack = []
        for i in range(elems_size - 1, -1, -1):
            elem = elems[i]
            if elem != '(':
                rstack.append(elem)
            else:
                np_rstack = []
                current_val = 0
                if rstack[-1] != '-':
                    current_val = rstack.pop()
                while rstack[-1] != ')':
                    operator = rstack.pop()
                    operant = rstack.pop()
                    if operator == '+':
                        current_val += operant
                    elif operator == '-':
                        current_val -= operant
                rstack.pop()
                rstack.append(current_val)
        result = 0
        rstack_size = len(rstack)
        if rstack[-1] != '-':
            result = rstack.pop()
            rstack_size -= 1
        while rstack_size > 0:
            operator = rstack.pop()
            operant = rstack.pop()
            rstack_size -= 2
            if operator == '+':
                result += operant
            elif operator == '-':
                result -= operant
        return result
                
    
    def init_elems(self, s):
        elems = []
        prev_is_digit = False
        current_num = 0
        for char in s:
            if char == ' ': continue
            elif self.is_digit(char):
                if prev_is_digit:
                    current_num = current_num * 10 + int(char, 10)
                else:
                    current_num = int(char, 10)
                prev_is_digit = True
            else:
                if prev_is_digit:
                    elems.append(current_num)
                prev_is_digit = False
                current_num = 0
                elems.append(char)
        if prev_is_digit:
            elems.append(current_num)
        return elems

    def is_digit(self, char):
        return char >= '0' and char <= '9'
```
