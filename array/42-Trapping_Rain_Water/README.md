# [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/description/)

## Description
Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

**Example 1:**

![Example 1](./example_1.png)
```
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
```

**Example 2:**
```
Input: height = [4,2,0,3,2,5]
Output: 9
```

**Constraints:**
+ `n == height.length`
+ `1 <= n <= 2 * 10^4`
+ `0 <= height[i] <= 10^5`

## Solution

### Java
```java
class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = 0;
        int capacity = 0;
        while (right < height.length-1) {
            if (height[left] <= height[right]) {
                left = findNextLeftPeak(height, right);
                right = left;
            }
            right = findNextRightPeak(height, right+1);
            capacity += updateCapacityInBetween(height, left, right);
        }

        return capacity;
    }

    private int findNextLeftPeak(int[] height, int start) {
        if (0 == start) return 0;
        int i = start;
        while (i < height.length-1) {
            if (height[i-1] <= height[i] && height[i] > height[i+1]) {
                break;
            }
            i++;
        }

        return i;
    }

    private int findNextRightPeak(int[] height, int start) {
        if (height.length-1 <= start) return height.length-1;
        int i = start;
        while (i < height.length-1) {
            if (height[i-1] < height[i] && height[i] >= height[i+1]) {
                break;
            }
            i++;
        }

        return i;
    }

    private int updateCapacityInBetween(int[] height, int left, int right) {
        int low = Math.min(height[left], height[right]);
        int capacity = 0;
        for (int i = left+1; i < right; i++) {
            if (height[i] < low) {
                capacity += low - height[i];
                height[i] = low;
            }
        }

        return capacity;
    }
}
```
