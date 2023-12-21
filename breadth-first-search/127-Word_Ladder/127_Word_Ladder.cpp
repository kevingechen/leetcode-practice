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
};
