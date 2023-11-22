class Solution {
    public int maxSubArray(int[] nums) {
        int accumSum = 0;
        int accumMinSum = 0;
        int maxSubSum = nums[0];
        int tmpMax;

        for (int i = 0; i < nums.length; i++) {
            accumSum += nums[i];
            tmpMax = accumSum - accumMinSum;
            maxSubSum = maxSubSum > tmpMax ? maxSubSum : tmpMax;
            accumMinSum = accumSum < accumMinSum ? accumSum : accumMinSum;
        }

        return maxSubSum;
    }
}
