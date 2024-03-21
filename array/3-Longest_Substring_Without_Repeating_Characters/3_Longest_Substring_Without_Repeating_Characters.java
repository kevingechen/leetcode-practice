class Solution {
    public int lengthOfLongestSubstring(String s) {
        int longestLen = 0;
        int left = 0;
        Integer prevPos;
        Map<Character, Integer> charPosMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            Character c = s.charAt(right);
            prevPos = charPosMap.get(c);
            left = (null != prevPos && prevPos+1 > left) ? prevPos+1 : left;
            charPosMap.put(c, right);
            longestLen = Math.max(right - left + 1, longestLen);
        }

        return longestLen;
    }
}
