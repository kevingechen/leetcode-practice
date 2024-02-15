# [63. Unique Paths II](https://leetcode.com/problems/unique-paths-ii/description/)

## Description

There is a robot on an `m x n` grid. The robot is initially located at the **top-left corner** (i.e., `grid[0][0]`). The robot tries to move to the **bottom-right corner** (i.e., `grid[m - 1][n - 1]`). The robot can only move either down or right at any point in time.

An obstacle and space are marked as `1` or `0` respectively in `grid`. A path that the robot takes cannot include **any** square that is an obstacle.

Return *the number of possible unique paths that the robot can take to reach the bottom-right corner*.

The test cases are generated so that the answer will be less than or equal to `2 * 10^9`.

**Example 1:**

![Example 1](./example_1.jpg)
```
Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
```

**Example 2:**

![Example 2](./example_2.jpg)
```
Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
```

**Constraints:**
+ `m == obstacleGrid.length
+ `n == obstacleGrid[i].length`
+ `1 <= m, n <= 100`
+ `obstacleGrid[i][j]` is `0` or `1`.

## Solution

### Java
```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] dp = new int[n];
        dp[0] = 1;
        int prev;

        for (int i = 0; i < m; i++) {
            prev = 0;
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    prev = 0;
                } else {
                    dp[j] = dp[j] + prev;
                    prev = dp[j];
                }
            }
        }

        return dp[n-1];
    }
}
```
