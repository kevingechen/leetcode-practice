class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        anagram_map = {}
        for s in strs:
            key = ''.join(sorted(s))
            if key in anagram_map:
                anagram_map[key].append(s)
            else:
                anagram_map[key] = [s]
        results = []
        for key in anagram_map:
            results.append( anagram_map[key] )
        return results
