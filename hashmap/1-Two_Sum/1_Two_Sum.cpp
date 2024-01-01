class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> result;
        unordered_map<int, int> valueMap;
        int residus;
        for (int i = 0; i < nums.size(); i++) {
            residus = target - nums[i];
            if (valueMap.find(residus) != valueMap.end()) {
                result.push_back(valueMap[residus]);
                result.push_back(i);
                return result;
            } else {
                valueMap[nums[i]] = i;
            }
        }

        return result;
    }
};
