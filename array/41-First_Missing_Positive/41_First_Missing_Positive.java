class Solution {
    public int firstMissingPositive(int[] nums) {
        int current;
        for (int i = 0; i < nums.length; i++) {
            current = nums[i];
            while (current != i+1
                    && current > 0
                    && current <= nums.length) {
                if (current < i+1
                        || current == nums[current-1]) {
                    nums[current-1] = current;
                    break;
                } else {
                    swap(nums, i, current-1);
                    current = nums[i];
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i+1 != nums[i]) {
                return i+1;
            }
        }

        return nums.length+1;
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
