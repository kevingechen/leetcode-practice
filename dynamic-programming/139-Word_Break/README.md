# [139. Word Break](https://leetcode.com/problems/word-break/description/)

## Description
Given a string `s` and a dictionary of strings `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of one or more dictionary words.
**Note** that the same word in the dictionary may be reused multiple times in the segmentation.

**Example 1:**
```
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
```

**Example 2:**
```
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
```

**Example 3:**
```
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
```

**Constraints:**
+ `1 <= s.length <= 300`
+ `1 <= wordDict.length <= 1000`
+ `1 <= wordDict[i].length <= 20`
+ `s` and `wordDict[i]` consist of only lowercase English letters.
+ All the strings of `wordDict` are **unique**.

## Solution
### Java
```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (String word : wordDict) {
                if (!dp[i+1] && endsWithWordTillIndex(s, i, word)) {
                    dp[i+1] = dp[i+1 - word.length()];
                }
            }
        }

        return dp[s.length()];
    }

    private boolean endsWithWordTillIndex(String s, int i, String word) {
        if (i + 1 < word.length()) {
            return false;
        }

        for (int j = 0; j < word.length(); j++) {
            if (s.charAt(i+1 - word.length() + j) != word.charAt(j)) {
                return false;
            }
        }

        return true;
    }

}
```
