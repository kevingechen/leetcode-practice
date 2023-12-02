class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] dp = new int[n];
        dp[0] = 1;
        int prev;

        for (int i = 0; i < m; i++) {
            prev = 0;
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    prev = 0;
                } else {
                    dp[j] = dp[j] + prev;
                    prev = dp[j];
                }
            }
        }

        return dp[n-1];
    }
}
