# [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/description/)

## Description
Given an integer array nums, find the **subarray** with the largest sum, and return its sum.

**Example 1:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
```

**Example 2:**
```
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
```

**Example 3:**
```
Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
```

**Constraints:**
+ `1 <= nums.length <= 10^5`
+ `-10^4 <= nums[i] <= 10^4`


## Solution
### Java
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int accumSum = 0;
        int accumMinSum = 0;
        int maxSubSum = nums[0];
        int tmpMax;

        for (int i = 0; i < nums.length; i++) {
            accumSum += nums[i];
            tmpMax = accumSum - accumMinSum;
            maxSubSum = maxSubSum > tmpMax ? maxSubSum : tmpMax;
            accumMinSum = accumSum < accumMinSum ? accumSum : accumMinSum;
        }

        return maxSubSum;
    }
}
```

### C
```c
/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Example:
 *   Input: [-2,1,-3,4,-1,2,1,-5,4],
 *   Output: 6
 *   Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 */


int
maxSubArray(int* nums, int numsSize)
{
    if (numsSize <= 0)
        return 0;
    int accum_sum = 0;
    int accum_min = 0;
    int max_sub_sum = nums[0];
    int tmp_max;
    
    for (int i = 0; i < numsSize; i++) {
        accum_sum += nums[i];
        tmp_max = accum_sum - accum_min;
        max_sub_sum = max_sub_sum > tmp_max ? max_sub_sum : tmp_max;
        accum_min = accum_sum < accum_min ? accum_sum : accum_min;
    }
    
    return max_sub_sum;
}
```
