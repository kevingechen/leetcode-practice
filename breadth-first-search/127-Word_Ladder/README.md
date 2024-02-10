# [127. Word Ladder](https://leetcode.com/problems/word-ladder/description/)

## Description
A **transformation sequence** from word `beginWord` to word `endWord` using a dictionary `wordList` is a sequence of words `beginWord -> s_1 -> s_2 -> ... -> s_k` such that:

+ Every adjacent pair of words differs by a single letter.
+ Every `s_i` for `1 <= i <= k` is in `wordList`. Note that `beginWord` does not need to be in `wordList`.
+ `s_k == endWord`

Given two words, `beginWord` and `endWord`, and a dictionary `wordList`, return *the **number of words** in the**shortest transformation sequences** from `beginWord` to `endWord`, or `0` if no such sequence exists*.

**Example 1:**
```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
```

**Example 2:**
```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
```

**Constraints:**
+ `1 <= beginWord.length <= 10`
+ `endWord.length == beginWord.length`
+ `1 <= wordList.length <= 5000`
+ `wordList[i].length == beginWord.length`
+ `beginWord`, `endWord`, and `wordList[i]` consist of lowercase English letters.
+ `beginWord != endWord`
+ All the words in `wordList` are **unique**.


## Solution
### Java
```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        int ladderLen = 1;
        int prevVisitedSize = 0;
        Set<String> visitedSet = new HashSet<>();
        Deque<String> bfsQueue = new ArrayDeque<>();
        Deque<String> bfsBufferQueue = new ArrayDeque<>();
        Deque<String> tmp;

        // initialize by beginWord
        visitedSet.add(beginWord);
        bfsQueue.offerFirst(beginWord);
        // do BFS return if hit endWord
        while (visitedSet.size() > prevVisitedSize) {
            prevVisitedSize = visitedSet.size();
            ladderLen++;
            while (bfsQueue.size() > 0) {
                String nextWord = bfsQueue.pollLast();
                for (int i = 0; i < wordList.size(); i++) {
                    if (!visitedSet.contains(wordList.get(i)) && isTransformation(nextWord, wordList.get(i))) {
                        if (endWord.equals(wordList.get(i))) {
                            return ladderLen;
                        }
                        visitedSet.add(wordList.get(i));
                        bfsBufferQueue.offerFirst(wordList.get(i));
                    }
                }
            }
            tmp = bfsQueue;
            bfsQueue = bfsBufferQueue;
            bfsBufferQueue = tmp;
        }
        
        return 0;
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

### CPP
```cpp
class Solution {
private:
    bool isTransformation(const string& aWord, const string& bWord) {
        int diffCharCount = 0;
        for (int i = 0; i < aWord.size(); i++) {
            if (aWord[i] != bWord[i]) {
                ++diffCharCount;
                if (diffCharCount > 1) {
                    return false;
                }
            }
        }

        return 1 == diffCharCount;
    }
public:
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        if (find(wordList.begin(), wordList.end(), endWord) == wordList.end()) {
            return 0;
        }

        int ladderLen = 1;
        int prevVisitedSize = 0;
        unordered_set<string> visitedSet;
        deque<string> bfsQueue;
        deque<string> bfsBufferQueue;

        // initialize by beginWord
        visitedSet.insert(beginWord);
        bfsQueue.push_back(beginWord);

        // do BFS return if hit endWord
        while (visitedSet.size() > prevVisitedSize) {
            prevVisitedSize = visitedSet.size();
            ladderLen++;
            while (bfsQueue.size() > 0) {
                string nextWord = bfsQueue.front();
                bfsQueue.pop_front();
                for (int i = 0; i < wordList.size(); i++) {
                    if (visitedSet.find(wordList[i]) == visitedSet.end()
                        && isTransformation(nextWord, wordList[i])) {
                        if (endWord == wordList[i]) {
                            return ladderLen;
                        }
                        visitedSet.insert(wordList[i]);
                        bfsBufferQueue.push_back(wordList[i]);
                    }
                }
            }
            swap(bfsQueue, bfsBufferQueue);
        }
        
        return 0;
    }
}
```
