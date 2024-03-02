# [213. House Robber II](https://leetcode.com/problems/house-robber-ii/description/)

## Description
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are **arranged in a circle**. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and **it will automatically contact the police if two adjacent houses were broken into on the same night**.

Given an integer array `nums` representing the amount of money of each house, return the maximum amount of money you can rob tonight **without alerting the police**.

**Example 1:**
```
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
```

**Example 2:**
```
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
```

**Example 3:**
```
Input: nums = [1,2,3]
Output: 3
```

**Constraints:**
+ `1 <= nums.length <= 100`
+ `0 <= nums[i] <= 1000`

## Solution
### Java
```java
/**
 * This time, we can get result based on insight from 198
 * the result should be max(rob(nums[0::end-1]), rob(nums[1::end])), with rob of 198 here
 */
class Solution {
    public int rob(int[] nums) {
        int a = 0, b = nums[0], maxMoney1 = nums[0], maxMoney2 = nums[nums.length - 1];
        for (int i = 1; i < nums.length - 1; i++) {
            maxMoney1 = a + nums[i] > b ? a + nums[i] : b;
            a = b;
            b = maxMoney1;
        }
        
        a = 0; b = nums[nums.length - 1];
        for (int i = nums.length - 2; i > 0; i--) {
            maxMoney2 = a + nums[i] > b ? a + nums[i] : b;
            a = b;
            b = maxMoney2;
        }

        return maxMoney1 > maxMoney2 ? maxMoney1 : maxMoney2;
    }
}
```


