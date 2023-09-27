# 49. Group Anagrams

## Description
Given an array of strings `strs`, group **the anagrams** together. You can return the answer in **any order**.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Example 1:**
```
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```

**Example 2:**
```
Input: strs = [""]
Output: [[""]]
```

**Example 3:**
```
Input: strs = ["a"]
Output: [["a"]]
```

**Constraints:**
+ `1 <= strs.length <= 10^4`
+ `0 <= strs[i].length <= 100`
+ `strs[i]` consists of lowercase English letters.


## Solution
### Python3
```python
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        anagram_map = {}
        for s in strs:
            key = ''.join(sorted(s))
            if key in anagram_map:
                anagram_map[key].append(s)
            else:
                anagram_map[key] = [s]
        results = []
        for key in anagram_map:
            results.append( anagram_map[key] )
        return results
```
