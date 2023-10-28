# 227. Basic Calculator II

## Description

Given a string `s` which represents an expression, *evaluate this expression and return its value*. 

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of `[-2^31, 2^31 - 1]`.

**Note**: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as `eval()`.

**Example 1:**
```
Input: s = "3+2*2"
Output: 7
```

**Example 2:**
```
Input: s = " 3/2 "
Output: 1
```

**Example 3:**
```
Input: s = " 3+5 / 2 "
Output: 5
```

**Constraints:**
+ `1 <= s.length <= 3 * 10^5`
+ `s` consists of integers and operators (`'+'`, `'-'`, `'*'`, `'/'`) separated by some number of spaces.
+ `s` represents a **valid expression**.
+ All the integers in the expression are non-negative integers in the range `[0, 2^31 - 1]`.
+ The answer is **guaranteed** to fit in a **32-bit integer**.


## Solution

### Python3
```python
class Solution:
    def calculate(self, s: str) -> int:
        elems = self.init_elems(s)
        print (elems)
        elems_size = len(elems)
        rstack = []
        has_multi_demulti = False
        for i in range(elems_size - 1, -1, -1):
            elem = elems[i]
            if self.is_multi_demulti(elem):
                has_multi_demulti = True
            elif self.is_plus_minus(elem):
                if has_multi_demulti:
                    val = self.calculate_multi_demulti(rstack)
                    rstack.append(val)
                has_multi_demulti = False
            rstack.append(elem)
        if has_multi_demulti:
            val = self.calculate_multi_demulti(rstack)
            rstack.append(val)
        return self.calculate_plus_minus(rstack)
    
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
    
    def calculate_multi_demulti(self, rstack):
        result = rstack.pop()
        rstack_size = len(rstack)
        while rstack_size > 1 and not self.is_plus_minus(rstack[-1]):
            operator = rstack.pop()
            operant = rstack.pop()
            rstack_size -= 2
            if operator == '*':
                result = result * operant
            else:
                result = int (result / operant)
        return result
    
    def calculate_plus_minus(self, rstack):
        rstack_size = len(rstack)
        if rstack_size == 0: return 0
        result = rstack.pop()
        while rstack_size > 1:
            operator = rstack.pop()
            operant = rstack.pop()
            rstack_size -= 2
            if operator == '+':
                result = result + operant
            else:
                result = result - operant
        return result

    def is_digit(self, char):
        return char >= '0' and char <= '9'

    def is_plus_minus(self, char):
        return char == '+' or char == '-'

    def is_multi_demulti(self, char):
        return char == '*' or char == '/'
```
