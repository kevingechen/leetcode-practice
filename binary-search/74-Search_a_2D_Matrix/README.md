# 74. Search a 2D Matrix

## Description

You are given an `m x n` integer matrix `matrix` with the following two properties:
+ Each row is sorted in non-decreasing order.
+ The first integer of each row is greater than the last integer of the previous row.

Given an integer `target`, return `true` if `target` is in `matrix` or `false` otherwise.

You must write a solution in `O(log(m * n))` time complexity.

**Example 1:**
```
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
```

**Example 2:**
```
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
```

**Constraints:**
+ `m == matrix.length`
+ `n == matrix[i].length`
+ `1 <= m, n <= 100`
+ `-10^4 <= matrix[i][j], target <= 10^4`


## Solution
### Java
Implementation with one-dimension approach
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        return binarySearchMatrix(matrix, target, 0, m * n - 1, n);
    }

    private boolean binarySearchMatrix(int[][] matrix, int target, int left, int right, int n) {
        if (left > right) {
            return false;
        }

        int mid = left + (right - left) / 2;
        int midRow = mid / n;
        int midCol = mid % n;
        if (matrix[midRow][midCol] == target) {
            return true;
        } else if (matrix[midRow][midCol] > target) {
            return binarySearchMatrix(matrix, target, left, mid - 1, n);
        } else {
            return binarySearchMatrix(matrix, target, mid + 1, right, n);
        }
    }
}
```

Implementation with two-dimensional approach
> first locate the row for target, then run a classical binary search within the row
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int targetRow = binarySearchTargetRow(matrix, target, 0, m - 1);
        return binarySearchTargetInRow(matrix[targetRow], target, 0, n - 1);
    }

    private int binarySearchTargetRow(int[][] matrix, int target, int low, int high) {
        if (target >= matrix[high][0]) {
            return high;
        }

        if (low == high || high == low + 1) {
            return low;
        }

        int mid = low + (high - low) / 2;
        if (target >= matrix[mid][0]) {
            return binarySearchTargetRow(matrix, target, mid, high);
        } else {
            return binarySearchTargetRow(matrix, target, low, mid - 1);
        }

    }

    private boolean binarySearchTargetInRow(int[] row, int target, int left, int right) {
        if (left > right) {
            return false;
        }

        int mid = left + (right - left) / 2;
        if (row[mid] == target) {
            return true;
        } else if (row[mid] > target) {
            return binarySearchTargetInRow(row, target, left, mid - 1);
        } else {
            return binarySearchTargetInRow(row, target, mid + 1, right);
        }
    }
}
```
