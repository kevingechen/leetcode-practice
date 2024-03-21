# [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)

## Description
Given a string `s`, find the length of the **longest** substring without repeating characters.

**Example 1:**
```
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
```

**Example 2:**
```
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```

**Example 3:**
```
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

**Constraints:**
+ `0 <= s.length <= 5 * 10^4`
+ `s` consists of English letters, digits, symbols and spaces.


## Solution

### CPP
```cpp
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int longestLen = 0;
        int left = 0;
        unordered_map<char, int> charPosMap;
        for (int right = 0; right < s.size(); right++) {
            char c = s[right];
            auto it = charPosMap.find(c);
            left = (it != charPosMap.end() && it->second + 1 > left) ? it->second + 1 : left;
            charPosMap[c] = right;
            longestLen = max(right - left + 1, longestLen);
        }

        return longestLen;
    }
};
```

### Java
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int longestLen = 0;
        int left = 0;
        Integer prevPos;
        Map<Character, Integer> charPosMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            Character c = s.charAt(right);
            prevPos = charPosMap.get(c);
            left = (null != prevPos && prevPos+1 > left) ? prevPos+1 : left;
            charPosMap.put(c, right);
            longestLen = Math.max(right - left + 1, longestLen);
        }

        return longestLen;
    }
}
```
