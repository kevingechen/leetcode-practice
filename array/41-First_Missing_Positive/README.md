# [41. First Missing Positive]()

## Description
Given an unsorted integer array `nums`. Return the smallest positive integer that is *not present* in `nums`.

You must implement an algorithm that runs in `O(n)` time and uses `O(1)` auxiliary space.


**Example 1:**
```
Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.
```

**Example 2:**
```
Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.
```

**Example 3:**
```
Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.
```

**Constraints:**
+ `1 <= nums.length <= 10^5`
+ `-2^31 <= nums[i] <= 2^31 - 1`

## Solution

### CPP
```cpp
class Solution {
private:
    void swap(vector<int>& nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
    
public:
    int firstMissingPositive(vector<int>& nums) {
        int current;
        for (int i = 0; i < nums.size(); i++) {
            current = nums[i];
            while (current != i+1
                    && current > 0
                    && current <= nums.size()) {
                if (current < i+1
                        || current == nums[current-1]) {
                            nums[current-1] = current;
                            break;
                } else {
                    swap(nums, i, current-1);
                    current = nums[i];
                }
            }
        }

        for (int i = 0; i < nums.size(); i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }

        return nums.size() + 1;
    }
};
```

### Java
```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        int current;
        for (int i = 0; i < nums.length; i++) {
            current = nums[i];
            while (current != i+1
                    && current > 0
                    && current <= nums.length) {
                if (current < i+1
                        || current == nums[current-1]) {
                    nums[current-1] = current;
                    break;
                } else {
                    swap(nums, i, current-1);
                    current = nums[i];
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i+1 != nums[i]) {
                return i+1;
            }
        }

        return nums.length+1;
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
```
