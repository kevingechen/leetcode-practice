# 34. Find First and Last Position of Element in Sorted Array

## Description

Given an array of integers `nums` sorted in non-decreasing order, find the starting and ending position of a given `target` value.

If `target` is not found in the array, return `[-1, -1]`.

You must write an algorithm with `O(log n)` runtime complexity.

**Example 1:**
```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
```

**Example 2:**
```
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```

**Example 3:**
```
Input: nums = [], target = 0
Output: [-1,-1]
```


**Constraints:**
+ `0 <= nums.length <= 10^5`
+ `-10^9 <= nums[i] <= 10^9`
+ `nums` is a non-decreasing array.
+ `-10^9 <= target <= 10^9`


## Solution

### Java
```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        int oneHitIndex = binarySearchTarget(nums, target, 0, nums.length - 1);
        if (-1 == oneHitIndex) {
            return result;
        } else {
            result[0] = binarySearchLeftBorder(nums, target, 0, oneHitIndex);
            result[1] = binarySearchRightBorder(nums, target, oneHitIndex, nums.length - 1);
            return result;
        }
    }

    private int binarySearchTarget(int []nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + ((right - left) / 2);
        if (target == nums[mid]) {
            return mid;
        } else if (target > nums[mid]) {
            return binarySearchTarget(nums, target, mid+1, right);
        } else {
            return binarySearchTarget(nums, target, left, mid-1);
        }
    }

    private int binarySearchLeftBorder(int []nums, int target, int left, int right) {
        if (left >= right) {
            return right;
        }

        int mid = left + ((right - left) / 2);
        if (target == nums[mid]) {
            return binarySearchLeftBorder(nums, target, left, mid);
        } else {
            return binarySearchLeftBorder(nums, target, mid+1, right);
        }
    }

    private int binarySearchRightBorder(int []nums, int target, int left, int right) {
        if (left >= right) {
            return left;
        }

        int mid = left + ((right - left + 1) / 2);
        if (target == nums[mid]) {
            return binarySearchRightBorder(nums, target, mid, right);
        } else {
            return binarySearchRightBorder(nums, target, left, mid-1);
        }
    }
}
```
