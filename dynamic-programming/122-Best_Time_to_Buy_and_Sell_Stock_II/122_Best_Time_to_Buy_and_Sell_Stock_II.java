class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0, currentProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            currentProfit = prices[i] - prices[i-1];
            if (currentProfit > 0) {
                maxProfit += currentProfit;
            }
        }

        return maxProfit;
    }
}
