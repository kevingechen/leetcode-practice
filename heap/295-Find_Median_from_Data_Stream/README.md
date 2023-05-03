# 295. Find Median from Data Stream

## Description
The **median** is the middle value in an ordered integer list. If the size of the list is even,
there is no middle value, and the median is the mean of the two middle values.
+ For example, for `arr = [2,3,4]`, the median is `3`.
+ For example, for `arr = [2,3]`, the median is `(2 + 3) / 2 = 2.5`.

Implement the MedianFinder class:
+ `MedianFinder()` initializes the MedianFinder object.
+ `void addNum(int num)` adds the integer num from the data stream to the data structure.
+ `double findMedian()` returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

**Example 1**
```
Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
```

## Solution in Java
```java

class Heap<T extends Comparable<T>> {
    private int size;
    private List<T> heap;
    private HeapType type;

    public static enum HeapType {
        MAX_HEAP,
        MIN_HEAP
    }

    public int size() {
        return this.size;
    }

    public T peek() {
        return heap.get(0);
    }

    public Heap(HeapType heapType) {
        this.heap = new ArrayList<>();
        this.size = 0;
        this.type = heapType;
    }

    public void push(T t) {
        this.heap.add(t);
        this.size += 1;
        adjustUp(this.size - 1);

    }

    public T pop() {
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


class MedianFinder {

    private Heap<Integer> higherPartMinHeap;
    private Heap<Integer> lowerPartMaxHeap;

    public MedianFinder() {
        higherPartMinHeap = new Heap<Integer>(Heap.HeapType.MIN_HEAP);
        lowerPartMaxHeap = new Heap<Integer>(Heap.HeapType.MAX_HEAP);
    }
    
    public void addNum(int num) {
        if (lowerPartMaxHeap.size() == 0) {
            lowerPartMaxHeap.push(num);
            return;
        }
        if (higherPartMinHeap.size() == 0) {
            lowerPartMaxHeap.push(num);
            higherPartMinHeap.push(lowerPartMaxHeap.pop());
            return;
        }
        
        int lowSize = lowerPartMaxHeap.size();
        int lowRoot = lowerPartMaxHeap.peek();
        int highSize = higherPartMinHeap.size();
        int highRoot = higherPartMinHeap.peek();
        if (num < lowRoot) {
            lowerPartMaxHeap.push(num);
            if (lowSize > highSize) {
                higherPartMinHeap.push(lowerPartMaxHeap.pop());
            }
        } else if (num > highRoot) {
            higherPartMinHeap.push(num);
            if (highSize > lowSize) {
                lowerPartMaxHeap.push(higherPartMinHeap.pop());
            }
        } else {
            if (lowSize > highSize) {
                higherPartMinHeap.push(num);
            } else {
                lowerPartMaxHeap.push(num);
            }
        }
    }
    
    public double findMedian() {
        if (higherPartMinHeap.size() > lowerPartMaxHeap.size()) {
            return Double.valueOf(higherPartMinHeap.peek());
        } else if (higherPartMinHeap.size() < lowerPartMaxHeap.size()) {
            return Double.valueOf(lowerPartMaxHeap.peek());
        } else {
            return (Double.valueOf(higherPartMinHeap.peek()) +
                    Double.valueOf(lowerPartMaxHeap.peek())) / 2;
        }
    }
}

```
