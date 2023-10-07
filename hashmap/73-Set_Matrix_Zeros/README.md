# 73. Set Matrix Zeros

## Description
Given an `m x n` integer matrix `matrix`, if an element is `0`, set its entire row and column to `0`'s.

You must do it in place.

**Example 1:**
```
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
```

**Example 2:**
```
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
```

**Constraints:**
+ `m == matrix.length`
+ `n == matrix[0].length`
+ `1 <= m, n <= 200`
+ `-2^31 <= matrix[i][j] <= 2^31 - 1`


## Solution

### Python
```python3
class Solution:
    def setZeroes(self, matrix):
        """
        Do not return anything, modify matrix in-place instead.
        """
        zero_rows_map = {}
        zero_cols_map = {}
        row_num = len(matrix)
        col_num = len(matrix[0])
        for x in range(row_num):
            for y in range(col_num):
                if 0 == matrix[x][y]:
                    zero_rows_map[x] = 0
                    zero_cols_map[y] = 0
        for x in range(row_num):
            for y in range(col_num):
                if x in zero_rows_map or y in zero_cols_map:
                    matrix[x][y] = 0
```
