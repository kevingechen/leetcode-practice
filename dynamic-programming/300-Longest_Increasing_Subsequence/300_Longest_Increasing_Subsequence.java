class Solution {
    // dp[i]: LIS ended at i
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLengthEndedAtI, maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            maxLengthEndedAtI = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxLengthEndedAtI = Math.max(maxLengthEndedAtI, dp[j]+1);
                }
            }
            dp[i] = maxLengthEndedAtI;
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }
}
