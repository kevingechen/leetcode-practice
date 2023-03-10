# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeKLists(self, lists):
        dummy = ListNode(0, None)
        move = dummy
        heap = []
        hsize = 0
        for node in lists:
            if node is not None:
                heap.append(node)
                hsize += 1
        self.init_heap(heap, hsize)
        while hsize > 0:
            move.next = heap[0]
            move = move.next
            heap[0] = heap[0].next
            if heap[0] is None:
                hsize -= 1
                self.swap_heap(heap, 0, hsize)
            self.adjust_down(heap, hsize, 0)
        return dummy.next
    
    def init_heap(self, heap, hsize):
        for i in range(int(hsize / 2), -1, -1):
            self.adjust_down(heap, hsize, i)
    
    def swap_heap(self, heap, index1, index2):
        tmp = heap[index1]
        heap[index1] = heap[index2]
        heap[index2] = tmp

    def should_swap(self, heap, hsize, parent, child):
        return child < hsize and heap[child].val < heap[parent].val

    def adjust_down(self, heap, hsize, start_index):
        parent = start_index
        while parent < hsize:
            next_parent = parent
            left_child = 2 * parent + 1
            right_child = 2 * parent + 2
            if self.should_swap(heap, hsize, next_parent, left_child):
                next_parent = left_child
            if self.should_swap(heap, hsize, next_parent, right_child):
                next_parent = right_child
            if next_parent == parent:
                return
            else:
                self.swap_heap(heap, parent, next_parent)
                parent = next_parent

            

