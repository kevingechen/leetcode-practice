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
