/**
 * This time, we can get result based on insight from 198
 * the result should be max(rob(nums[0::end-1]), rob(nums[1::end])), with rob of 198 here
 */
class Solution {
    public int rob(int[] nums) {
        int a = 0, b = nums[0], maxMoney1 = nums[0], maxMoney2 = nums[nums.length - 1];
        for (int i = 1; i < nums.length - 1; i++) {
            maxMoney1 = a + nums[i] > b ? a + nums[i] : b;
            a = b;
            b = maxMoney1;
        }
        
        a = 0; b = nums[nums.length - 1];
        for (int i = nums.length - 2; i > 0; i--) {
            maxMoney2 = a + nums[i] > b ? a + nums[i] : b;
            a = b;
            b = maxMoney2;
        }

        return maxMoney1 > maxMoney2 ? maxMoney1 : maxMoney2;
    }
}
