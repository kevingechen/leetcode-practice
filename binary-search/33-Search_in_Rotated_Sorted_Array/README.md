# 33. Search in Rotated Sorted Array

There is an integer array `nums` sorted in ascending order (with **distinct** values).

Prior to being passed to your function, `nums` is possibly rotated at an unknown pivot index `k` (`1 <= k < nums.length`) such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]` (**0-indexed**). For example, `[0,1,2,4,5,6,7]` might be rotated at pivot index `3` and become `[4,5,6,7,0,1,2]`.

Given the array `nums` **after** the possible rotation and an integer `target`, return the index of `target` if it is in `nums`, or `-1` if it is not in `nums`.

You must write an algorithm with `O(log n)` runtime complexity.

**Example 1:**
```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

**Example 2:**
```
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

**Example 3:**
```
Input: nums = [1], target = 0
Output: -1
```


**Constraints:**
+ `1 <= nums.length <= 5000`
+ `-104 <= nums[i] <= 104`
+ All values of `nums` are **unique**.
+ `nums` is an ascending array that is possibly rotated.
+ `-10^4 <= target <= 10^4`


## Solution
### Java
```java
class Solution {
    public int search(int[] nums, int target) {
        return recursiveSearch(nums, target, 0, nums.length - 1);
    }

    private int recursiveSearch(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (nums[mid] == target) return mid;
        if (target < nums[left] && target > nums[right]) return -1;
        if (nums[left] < nums[right]) {
            // fall back to normal binary search
            if (target > nums[mid]) {
                return recursiveSearch(nums, target, mid+1, right);
            } else {
                return recursiveSearch(nums, target, left, mid - 1);
            }
        } else {
            if (target >= nums[left]) {
                if (nums[mid] > nums[left] && target > nums[mid]) {
                    return recursiveSearch(nums, target, mid+1, right);
                } else {
                    return recursiveSearch(nums, target, left, mid - 1);
                }  
            } else {
                if (nums[mid] < nums[right] && target < nums[mid]) {
                    return recursiveSearch(nums, target, left, mid - 1);
                } else {
                    return recursiveSearch(nums, target, mid+1, right);
                }
            }
        }
    }
}
```
