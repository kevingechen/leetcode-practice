/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Example:
 *   Input: [-2,1,-3,4,-1,2,1,-5,4],
 *   Output: 6
 *   Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 */


int
maxSubArray(int* nums, int numsSize)
{
    if (numsSize <= 0)
        return 0;
    int accum_sum = 0;
    int accum_min = 0;
    int max_sub_sum = nums[0];
    int tmp_max;
    
    for (int i = 0; i < numsSize; i++) {
        accum_sum += nums[i];
        tmp_max = accum_sum - accum_min;
        max_sub_sum = max_sub_sum > tmp_max ? max_sub_sum : tmp_max;
        accum_min = accum_sum < accum_min ? accum_sum : accum_min;
    }
    
    return max_sub_sum;
}
