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
public class Heap<T extends Comparable<T>> {
    private int size;
    private int capaciy;
    private List<T> heap;
    private HeapType type;

    public static enum HeapType {
        MAX_HEAP,
        MIN_HEAP
    }

    public Heap(int capacity, HeapType heapType) {
        this.capaciy = capacity;
        this.heap = new ArrayList<>(capacity);
        this.size = 0;
        this.type = heapType;
    }

    public void push(T t) {
        if (this.size == this.capaciy) {
            throw new IllegalStateException("Out of capacity!");
        }

        this.heap.add(t);
        this.size += 1;
        adjustUp(this.size - 1);

    }

    public T pop() {
        if (this.size == 0) {
            throw new IllegalStateException("Heap empty!");
        }

        T root = this.heap.get(0);
        this.size -= 1;
        this.heap.set(0, this.heap.get(this.size));
        this.heap.remove(this.size);
        adjustDown(0);
        return root;
    }

    private void adjustUp(int index) {
        int child = index;
        int parent;
        while (child >= 0) {
            parent = (child - 1) / 2;
            if (!needSwap(parent, child)) {
                break;
            }
            swap(child, parent);
            child = parent;
        }
    }

    private void adjustDown(int index) {
        int parent = index;
        int leftChild = 2 * parent + 1;
        int rightChild = 2 * parent + 2;
        while (leftChild < this.size) {
            int next = parent;
            if (needSwap(next, leftChild)) {
                next = leftChild;
            }
            if (needSwap(next, rightChild)) {
                next = rightChild;
            }
            if (next == parent) {
                break;
            }
            swap(next, parent);
            parent = next;
            leftChild = 2 * parent + 1;
            rightChild = 2 * parent + 2;
        }
    }

    private void swap(int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        T tmp = this.heap.get(index1);
        this.heap.set(index1, this.heap.get(index2));
        this.heap.set(index2, tmp);
    }

    private boolean needSwap(int parentIndex, int childIndex) {
        if (parentIndex < 0) {
            return false;
        }

        if (childIndex == 0 || childIndex >= this.size) {
            return false;
        }

        int childCompareToParent =
                this.heap.get(childIndex).compareTo(this.heap.get(parentIndex));
        return HeapType.MAX_HEAP.equals(this.type) ?
                childCompareToParent > 0 :
                childCompareToParent < 0;
    }

}

class WordNode implements Comparable<WordNode> {
    public String word;
    public Integer cnt;

    public WordNode(String word, Integer cnt) {
        this.word = word;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(WordNode other) {
        int cntCompareResult = this.cnt.compareTo(other.cnt);
        if (cntCompareResult != 0) {
            return cntCompareResult;
        } else {
            return other.word.compareTo(this.word);
        }
    }
}

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        // get word count map
        Map<String, Integer> wordCntMap = new HashMap<>();
        for (String word : words) {
            wordCntMap.put(word, wordCntMap.getOrDefault(word, 0) + 1);
        }

        // init heap
        Heap<WordNode> heap = new Heap(wordCntMap.size(), Heap.HeapType.MAX_HEAP);
        for (Map.Entry<String, Integer> entry : wordCntMap.entrySet()) {
            heap.push(new WordNode(entry.getKey(), entry.getValue()));
        }

        // pick top k words
        List<String> topKWords = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            topKWords.add( heap.pop().word );
        }
        return topKWords;
    }
}
```
