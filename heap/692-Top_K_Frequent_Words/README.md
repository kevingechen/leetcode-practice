# 692. Top K Frequent Words

## Description
Given an array of strings words and an integer k, return the k most frequent strings.

Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.

**Example 1:**
```
Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.
```

**Example 2:**
```
Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
```

## Solution
### Java
```java
class Heap {
    public Integer len;
    public String[] heap;
    public Map<String, Integer> wordCntMap;

    public Heap(Map<String, Integer> wordCntMap) {
        this.heap = new String[wordCntMap.size()];
        this.wordCntMap = wordCntMap;
        this.len = 0;
        for (String word : wordCntMap.keySet()) {
            this.push(word);
        }
    }

    public void push(String word) {
        heap[this.len] = word;
        this.adjustUp(this.len);
        this.len += 1;
    }

    public String pop() {
        String root = heap[0];
        if (1 == this.len) {
            return heap[0];
        }
        swap(0, this.len-1);
        this.len -= 1;
        adjustDown(0);
        return root;
    }

    public void adjustUp(Integer pos) {
        Integer child = pos, parent;
        while (child >= 0) {
            parent = (child - 1) / 2;
            if (compareTo(parent, child) >= 0) {
                break;
            }
            swap(child, parent);
            child = parent;
        }
    }

    public void adjustDown(Integer pos) {
        Integer parent = pos;
        Integer leftChild = 2 * parent + 1;
        Integer rightChild = 2 * parent + 2;
        while (leftChild < this.len) {
            Integer next = parent;
            if (compareTo(leftChild, next) > 0) {
                next = leftChild;
            }
            if (rightChild < this.len && compareTo(rightChild, next) > 0) {
                next = rightChild;
            }
            if (next == parent) {
                break;
            }
            swap(parent, next);
            parent = next;
            leftChild = 2 * parent + 1;
            rightChild = 2 * parent + 2;
        }
    }

    public void swap(Integer idx1, Integer idx2) {
        if (idx1 == idx2) {
            return;
        }
        String tmp = heap[idx1];
        heap[idx1] = heap[idx2];
        heap[idx2] = tmp;
    }

    public Integer compareTo(Integer idx1, Integer idx2) {
        String w1 = heap[idx1];
        String w2 = heap[idx2];
        Integer cntCompareResult = wordCntMap.get(w1).compareTo(wordCntMap.get(w2));
        if (cntCompareResult != 0) {
            return cntCompareResult;
        } else {
            return w2.compareTo(w1);
        }
    }
}

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordCntMap = new HashMap<>();
        for (String word : words) {
            wordCntMap.put(word, wordCntMap.getOrDefault(word, 0) + 1);
        }
        Heap heap = new Heap(wordCntMap);
        List<String> topKWords = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            topKWords.add( heap.pop() );
        }
        return topKWords;
    }
}
```
