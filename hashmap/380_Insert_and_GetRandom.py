class RandomizedSet:

    def __init__(self):
        self.size = 0
        self.data_map = {}
        self.data_arr = []

    def insert(self, val: int) -> bool:
        if self.data_map.get(val, False):
            return False
        self.data_arr.append(val)
        self.data_map[val] = True
        self.size += 1
        return True

    def remove(self, val: int) -> bool:
        if self.data_map.get(val, False):
            self.data_arr.append(val)
            self.data_map[val] = False
            self.size -= 1
        return False

    def getRandom(self) -> int:
        


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
