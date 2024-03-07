# [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/description/)

## Description
Given an integer array nums, return *the length of the longest **strictly increasing subsequence***.

**Example 1:**
```
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
```

**Example 2:**
```
Input: nums = [0,1,0,3,2,3]
Output: 4
```

**Example 3:**
```
Input: nums = [7,7,7,7,7,7,7]
Output: 1
```

**Constraints:**
+ `1 <= nums.length <= 2500`
+ `-10^4 <= nums[i] <= 10^4`

## Solution
### Java
```java
class Solution {
    // dp[i]: LIS ended at i
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLengthEndedAtI, maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            maxLengthEndedAtI = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxLengthEndedAtI = Math.max(maxLengthEndedAtI, dp[j]+1);
                }
            }
            dp[i] = maxLengthEndedAtI;
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }
}```
