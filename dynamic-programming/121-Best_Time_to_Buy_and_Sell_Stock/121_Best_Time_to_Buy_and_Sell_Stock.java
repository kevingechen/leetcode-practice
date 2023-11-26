class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0, currentProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            currentProfit = prices[i] - minPrice;
            minPrice = Math.min(prices[i], minPrice);
            maxProfit = Math.max(maxProfit, currentProfit);
        }

        return maxProfit;
    }
}
