# 1. Two Sum

## Description
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.
You may assume that each input would have **exactly one solution**, and you may not use the same element twice.

You can return the answer in any order.

**Example 1:**
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```

**Example 2:**
```
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

**Example 3:**
```
Input: nums = [3,3], target = 6
Output: [0,1]
```

**Constraints:**
+ `2 <= nums.length <= 104`
+ `-109 <= nums[i] <= 109`
+ `-109 <= target <= 109`
+ **Only one valid answer exists.**

## Solution

### Python3
```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        result = []
        value_map = {}
        for i in range(len(nums)):
            value = nums[i]
            residus = target - value
            if residus in value_map:
                return [value_map[residus], i]
            else:
                value_map[value] = i
        return []
```

### C++
```c++
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> result;
        unordered_map<int, int> valueMap;
        int residus;
        for (int i = 0; i < nums.size(); i++) {
            residus = target - nums[i];
            if (valueMap.find(residus) != valueMap.end()) {
                result.push_back(valueMap[residus]);
                result.push_back(i);
                return result;
            } else {
                valueMap[nums[i]] = i;
            }
        }

        return result;
    }
};
```
