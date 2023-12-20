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
