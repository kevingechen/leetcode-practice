class DlNode:

    def __init__(self, val, prev = None, next = None):
        self.val = val
        self.prev = prev
        self.next = next


class RandomizedSet:

    def __init__(self):
        self.cursor = None
        self.data_node_map = {}

    def insert(self, val: int) -> bool:
        if self.data_node_map.get(val, None) is not None:
            return False
        if self.cursor is None:
            new_node = DlNode(val, None, None)
            new_node.prev = new_node
            new_node.next = new_node
            self.cursor = new_node
            self.data_node_map[val] = new_node
        else:
            next_node = self.cursor.next
            new_node = DlNode(val, self.cursor, next_node)
            self.cursor.next = new_node
            next_node.prev = new_node
            self.data_node_map[val] = new_node
        return True

    def remove(self, val: int) -> bool:
        if self.data_node_map.get(val, None) is None:
            return False
        self.data_node_map[val].prev.next = self.data_node_map[val].next
        self.data_node_map[val].next.prev = self.data_node_map[val].prev
        if self.cursor == self.data_node_map[val]:
            self.cursor = self.data_node_map[val].next
            if self.cursor == self.data_node_map[val]:
                self.cursor = None
        self.data_node_map.pop(val)
        return True

    def getRandom(self) -> int:
        self.cursor = self.cursor.next
        return self.cursor.val
