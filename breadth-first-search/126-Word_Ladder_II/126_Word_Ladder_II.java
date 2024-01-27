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
