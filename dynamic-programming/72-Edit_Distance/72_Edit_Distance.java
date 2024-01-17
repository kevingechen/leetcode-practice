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
