class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        int currentCoin, m;
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            currentCoin = coins[i-1];
            for (int j = 1; j <= amount; j++) {
                m = j / currentCoin;
                for (int k = 0; k <= m; k++) {
                    dp[i][j] += dp[i-1][j - k*currentCoin];
                }
            }
        }

        return dp[coins.length][amount];
    }
}
