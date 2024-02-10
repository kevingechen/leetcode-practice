# [126. Word Ladder II](https://leetcode.com/problems/word-ladder-ii/description/)

## Description
A **transformation sequence** from word `beginWord` to word `endWord` using a dictionary `wordList` is a sequence of words `beginWord -> s_1 -> s_2 -> ... -> s_k` such that:

+ Every adjacent pair of words differs by a single letter.
+ Every `s_i` for `1 <= i <= k` is in `wordList`. Note that `beginWord` does not need to be in `wordList`.
+ `s_k == endWord`

Given two words, `beginWord` and `endWord`, and a dictionary `wordList`, return *all the **shortest transformation sequences** from `beginWord` to `endWord`, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words `[beginWord, s_1, s_2, ..., s_k]`*.

**Example 1:**
```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
```

**Example 2:**
```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
```

**Constraints:**
+ `1 <= beginWord.length <= 5`
+ `endWord.length == beginWord.length`
+ `1 <= wordList.length <= 500`
+ `wordList[i].length == beginWord.length`
+ `beginWord`, `endWord`, and `wordList[i]` consist of lowercase English letters.
+ `beginWord != endWord`
+ All the words in `wordList` are **unique**.
+ The **sum** of all shortest transformation sequences does not exceed `10^5`.

## Solution
### Java
```java
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> shortestPrevWordMap = new HashMap<>(wordList.size());
        if (!wordList.contains(endWord)) {
            return new ArrayList<>();
        }

        Set<String> visitedSet = new HashSet<>();
        Deque<String> bfsQueue = new ArrayDeque<>();
        Deque<String> bfsBufferQueue = new ArrayDeque<>();
        Deque<String> tmp;

        // initialize by beginWord
        bfsQueue.offerFirst(beginWord);

        // do BFS return if hit endWord
        while (!bfsQueue.isEmpty()) {
            visitedSet.addAll(bfsQueue);
            while (!bfsQueue.isEmpty()) {
                String prevWord = bfsQueue.pollLast();
                for (int i = 0; i < wordList.size(); i++) {
                    String currentWord = wordList.get(i);
                    if (!visitedSet.contains(currentWord) && isTransformation(prevWord, currentWord)) {
                        if (!bfsBufferQueue.contains(currentWord)) {
                            bfsBufferQueue.offerFirst(currentWord);
                        }
                        shortestPrevWordMap.putIfAbsent(currentWord, new ArrayList<>());
                        shortestPrevWordMap.get(currentWord).add(prevWord);
                    }
                }
            }
            tmp = bfsQueue;
            bfsQueue = bfsBufferQueue;
            bfsBufferQueue = tmp;
            if (shortestPrevWordMap.containsKey(endWord)) {
                return buildLadders(beginWord, endWord, shortestPrevWordMap);
            }
        }
        
        return new ArrayList<>();
    }

    private List<List<String>> buildLadders(String beginWord, String endWord, Map<String, List<String>> shortestPrevWordMap) {
        List<List<String>> ladders = new ArrayList<>();
        if (beginWord.equals(endWord)) {
            ladders.add(Arrays.asList(beginWord));
            return ladders;
        }

        for (String prevWord : shortestPrevWordMap.get(endWord)) {
            List<List<String>> prevLadders = buildLadders(beginWord, prevWord, shortestPrevWordMap);
            for (List<String> prevLadder : prevLadders) {
                List<String> currentLadder = new ArrayList(prevLadder);
                currentLadder.add(endWord);
                ladders.add(currentLadder);
            }
        }

        return ladders;
    }

    private boolean isTransformation(String aWord, String bWord) {
        int diffCharCount = 0;
        for (int i = 0; i < aWord.length(); i++) {
            if (aWord.charAt(i) != bWord.charAt(i)) {
                diffCharCount++;
                if (diffCharCount > 1) {
                    return false;
                }
            }
        }

        return diffCharCount == 1;
    }
}
```
