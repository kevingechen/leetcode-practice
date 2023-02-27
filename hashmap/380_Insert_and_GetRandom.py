from random import choice

class RandomizedSet:

    def __init__(self):
        self.data_arr = []
        self.data_index_map = {}

    def insert(self, val: int) -> bool:
        if self.data_index_map.get(val, None) is not None:
            return False
        self.data_index_map[val] = len(self.data_arr)
        self.data_arr.append(val)
        return True

    def remove(self, val: int) -> bool:
        if self.data_index_map.get(val, None) is None:
            return False
        idx = self.data_index_map[val]
        self.data_arr[idx] = self.data_arr[-1]
        self.data_index_map[self.data_arr[-1]] = idx
        self.data_arr.pop()
        del self.data_index_map[val]
        return True

    def getRandom(self) -> int:
        return choice(self.data_arr)
