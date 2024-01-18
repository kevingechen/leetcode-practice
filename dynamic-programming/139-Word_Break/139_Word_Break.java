class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (String word : wordDict) {
                if (!dp[i+1] && endsWithWordTillIndex(s, i, word)) {
                    dp[i+1] = dp[i+1 - word.length()];
                }
            }
        }

        return dp[s.length()];
    }

    private boolean endsWithWordTillIndex(String s, int i, String word) {
        if (i + 1 < word.length()) {
            return false;
        }

        for (int j = 0; j < word.length(); j++) {
            if (s.charAt(i+1 - word.length() + j) != word.charAt(j)) {
                return false;
            }
        }

        return true;
    }

}
