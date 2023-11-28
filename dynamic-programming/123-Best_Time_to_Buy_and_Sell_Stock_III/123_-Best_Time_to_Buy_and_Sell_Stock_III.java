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
