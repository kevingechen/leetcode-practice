# [329. Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/)

## Description
Given an `m x n` integers `matrix`, return *the length of the longest increasing path in `matrix`*.

From each cell, you can either move in four directions: left, right, up, or down. You **may not** move **diagonally** or move **outside the boundary** (i.e., wrap-around is not allowed).

**Example 1:**

![Example 1](./example_1.jpg)
```
Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
```

**Example 2:**

![Example 2](./example_2.jpg)
```
Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
```

**Example 3:**
```
Input: matrix = [[1]]
Output: 1
```

**Constraints:**
+ `m == matrix.length`
+ `n == matrix[i].length`
+ `1 <= m, n <= 200`
+ `0 <= matrix[i][j] <= 2^31 - 1`


## Solution
### CPP
```cpp
class Solution {
public:
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();

        vector<vector<int>> dpMatrix(m, vector<int>(n, 0));
        int longestSize = 1;
        int currentSize;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                currentSize = longestIncreasingPathStartingFrom(i, j, matrix, dpMatrix);
                longestSize = max(longestSize, currentSize);
            }
        }

        return longestSize;
    }

    int longestIncreasingPathStartingFrom(int row, int col, vector<vector<int>>& matrix, vector<vector<int>>& dpMatrix) {
        int m = matrix.size();
        int n = matrix[0].size();

        int result = 1;
        if (row < m - 1 && matrix[row+1][col] > matrix[row][col]) {
            if (dpMatrix[row+1][col] == 0) {
                dpMatrix[row+1][col] = longestIncreasingPathStartingFrom(row+1, col, matrix, dpMatrix);
            }
            result = max(result, 1 + dpMatrix[row+1][col]);
        }
        if (row > 0 && matrix[row-1][col] > matrix[row][col]) {
            if (dpMatrix[row-1][col] == 0) {
                dpMatrix[row-1][col] = longestIncreasingPathStartingFrom(row-1, col, matrix, dpMatrix);
            }
            result = max(result, 1 + dpMatrix[row-1][col]);
        }
        if (col < n - 1 && matrix[row][col+1] > matrix[row][col]) {
            if (dpMatrix[row][col+1] == 0) {
                dpMatrix[row][col+1] = longestIncreasingPathStartingFrom(row, col+1, matrix, dpMatrix);
            }
            result = max(result, 1 + dpMatrix[row][col+1]);
        }
        if (col > 0 && matrix[row][col-1] > matrix[row][col]) {
            if (dpMatrix[row][col-1] == 0) {
                dpMatrix[row][col-1] = longestIncreasingPathStartingFrom(row, col-1, matrix, dpMatrix);
            }
            result = max(result, 1 + dpMatrix[row][col-1]);
        }

        return result;
    }
};
```
