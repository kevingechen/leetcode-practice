class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int prev;
        for (int j = 0; j < m; j++) {
            prev = 0;
            for (int i = 0; i < n; i++) {
                dp[i] = prev + dp[i];
                prev = dp[i];
            }
        }

        return dp[n-1];
    }
}
