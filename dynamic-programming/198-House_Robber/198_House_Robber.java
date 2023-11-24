/**
 * Let dp[i]: the max money for robbing first i houses,
 *            which should be the result for nums[0::i]
 * dp[i] = max(dp[i-1], dp[i-2] + nums[i])
 */
class Solution {
    public int rob(int[] nums) {
        int a = 0;
        int b = nums[0];
        int maxMoney = b;
        
        for (int i = 1; i < nums.length; i++) {
            maxMoney = nums[i] + a > b ? nums[i] + a : b;
            a = b;
            b = maxMoney;
        }
        
        return maxMoney;
    }
}
