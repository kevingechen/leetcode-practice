# [123. Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/)

## Description
You are given an array `prices` where `prices[i]` is the price of a given stock on the `i^th` day.

Find the maximum profit you can achieve. You may complete **at most two transactions**.

**Note**: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

**Example 1:**
```
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
```

**Example 2:**
```
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
```

**Example 3:**
```
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
```

**Constraints:**
+ `1 <= prices.length <= 10^5`
+ `0 <= prices[i] <= 10^5`

## Solution
### Java
```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0, maxPrice = 0, minPrice = prices[0];
        int[] maxPriceRightward = new int[prices.length];
        for (int i = prices.length - 1; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            maxPriceRightward[i] = maxPrice;
        }

        int currentProfit = 0;
        int maxFirstProfit = 0, maxSecondProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            currentProfit = prices[i] - minPrice;
            maxFirstProfit = Math.max(maxFirstProfit, currentProfit);
            if (i < prices.length - 1) {
                maxSecondProfit = maxPriceRightward[i+1] - prices[i+1];
            } else {
                maxSecondProfit = 0;
            }
            currentProfit = maxFirstProfit + maxSecondProfit;
            maxProfit = Math.max(maxProfit, currentProfit);
            minPrice = Math.min(minPrice, prices[i]);
        }

        return maxProfit;
    }
}
```
