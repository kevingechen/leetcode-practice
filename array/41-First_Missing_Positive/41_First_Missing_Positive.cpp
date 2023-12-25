class Solution {
private:
    void swap(vector<int>& nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
    
public:
    int firstMissingPositive(vector<int>& nums) {
        int current;
        for (int i = 0; i < nums.size(); i++) {
            current = nums[i];
            while (current != i+1
                    && current > 0
                    && current <= nums.size()) {
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

        for (int i = 0; i < nums.size(); i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }

        return nums.size() + 1;
    }
};
