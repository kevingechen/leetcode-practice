class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int longestLen = 0;
        int left = 0;
        unordered_map<char, int> charPosMap;
        for (int right = 0; right < s.size(); right++) {
            char c = s[right];
            auto it = charPosMap.find(c);
            left = (it != charPosMap.end() && it->second + 1 > left) ? it->second + 1 : left;
            charPosMap[c] = right;
            longestLen = max(right - left + 1, longestLen);
        }

        return longestLen;
    }
};
