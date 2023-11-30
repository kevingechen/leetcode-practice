class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[] dp = new int[text1.length() + 1];
        int prev, current;

        for (int j = 0; j < text2.length(); j++) {
            prev = 0;
            for (int i = 0; i < text1.length(); i++) {
                current = dp[i+1];
                dp[i+1] = Math.max(dp[i+1], dp[i]);
                if (text2.charAt(j) == text1.charAt(i)) {
                    dp[i+1] = Math.max(dp[i+1], prev + 1);
                }
                prev = current;
            }
        }

        return dp[text1.length()];
    }
}
