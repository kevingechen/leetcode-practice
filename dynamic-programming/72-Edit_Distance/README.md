# [72. Edit Distance](https://leetcode.com/problems/edit-distance/description/)

## Description
Given two strings `word1` and `word2`, return *the minimum number of operations required to convert `word1` to `word2`*.

You have the following three operations permitted on a word:
+ Insert a character
+ Delete a character
+ Replace a character

**Example 1:**
```
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
```

**Example 2:**
```
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
```

**Constraints:**
+ `0 <= word1.length, word2.length <= 500`
+ `word1` and `word2` consist of lowercase English letters.


## Solution
### Java
```java
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }

        // initialize the DP cache
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int j = 0; j < word2.length() + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }

        // if word1[i] == word2[j]
        //   then dp[i][j] = min {dp[i-1][j]+1, dp[i][j-1]+1, dp[i-1][j-1]}
        // else
        //   then dp[i][j] = min {dp[i-1][j]+1, dp[i][j-1]+1, dp[i-1][j-1]+1}
        int prevMin;
        for (int i = 0 ; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                prevMin = Math.min(dp[i][j+1] + 1, dp[i+1][j] + 1);
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i+1][j+1] = Math.min(prevMin, dp[i][j]);
                } else {
                    dp[i+1][j+1] = Math.min(prevMin, dp[i][j] + 1);
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
```
