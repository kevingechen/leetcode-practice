# 350. Intersection of Two Arrays II

## Description
Given two integer arrays `nums1` and `nums2`, return *an array of their intersection*. Each element in the result must appear as many times as it shows in both arrays and you may return the result in **any order**.

**Example 1:**
```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
```

**Example 2:**
```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.
```

**Constraints:**
+ `1 <= nums1.length, nums2.length <= 1000`
+ `0 <= nums1[i], nums2[i] <= 1000`

## Solution

### Java
```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> results = new ArrayList<>();
        Map<Integer, Integer> numsCountMap1 = countNums(nums1);
        Map<Integer, Integer> numsCountMap2 = countNums(nums2);
        for (Map.Entry<Integer, Integer> entry : numsCountMap1.entrySet()) {
            int num = entry.getKey();
            int count1 = entry.getValue();
            int count2 = numsCountMap2.getOrDefault(num, 0);
            int intersectionCount = Math.min(count1, count2);
            for (int i = 0; i < intersectionCount; i++) {
                results.add(num);
            }
        }

        return results.stream().mapToInt(Integer::intValue).toArray();
    }

    public Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> numsCountMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            numsCountMap.put(nums[i], numsCountMap.getOrDefault(nums[i], 0) + 1);
        }

        return numsCountMap;
    }
}
```

### Python3
```python
class Solution:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        result, nums1_map, nums2_map = [], {}, {}
        for num in nums1:
            nums1_map.setdefault(num, 0)
            nums1_map[num] += 1
        for num in nums2:
            nums2_map.setdefault(num, 0)
            nums2_map[num] += 1
        for num, count1 in nums1_map.items():
            count2 = nums2_map.get(num, 0)
            for i in range(min(count1, count2)):
                result.append(num)
        return result
```
