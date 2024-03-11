# [322. Coin Change](https://leetcode.com/problems/coin-change/description/)

## Description
You are given an integer array `coins` representing coins of different denominations and an integer `amount` representing a total amount of money.

Return *the fewest number of coins that you need to make up that amount*. If that amount of money cannot be made up by any combination of the coins, return `-1`.

You may assume that you have an infinite number of each kind of coin.


**Example 1:**
```
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
```

**Example 2:**
```
Input: coins = [2], amount = 3
Output: -1
```

**Example 3:**
```
Input: coins = [1], amount = 0
Output: 0
```

**Constraints:**
+ `1 <= coins.length <= 12`
+ `1 <= coins[i] <= 2^31 - 1`
+ `0 <= amount <= 10^4`

## Solution
### Java
```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length+1][amount+1];
        int currentCoin, m, prev, currentMin;
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = -1;
        }

        for (int i = 1; i <= coins.length; i++) {
            currentCoin = coins[i-1];
            for (int j = 1; j <= amount; j++) {
                m = j / currentCoin;
                dp[i][j] = -1;
                for (int k = 0; k <= m; k++) {
                    prev = dp[i-1][j - k*currentCoin];
                    if (prev == -1) {
                        currentMin = -1;
                    } else {
                        currentMin = k + prev;
                    }
                    if (dp[i][j] == -1) {
                        dp[i][j] = currentMin;
                    } else if (prev != -1) {
                        dp[i][j] = Math.min(currentMin, dp[i][j]);
                    }
                }
            }
        }


        return dp[coins.length][amount];
    }
}
```
