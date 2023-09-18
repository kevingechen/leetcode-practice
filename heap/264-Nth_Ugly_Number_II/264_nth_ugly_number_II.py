class UglyFactor:
    def __init__(self, ugly_set, ugly_seq, seq_idx, factor):
        self.ugly_set = ugly_set
        self.ugly_seq = ugly_seq
        self.seq_idx = seq_idx
        self.factor = factor
    
    def incSeq(self):
        self.seq_idx += 1

    def getNextUgly(self):
        next_ugly = self.ugly_seq[self.seq_idx] * self.factor
        while next_ugly in self.ugly_set:
            self.incSeq()
            next_ugly = self.ugly_seq[self.seq_idx] * self.factor
        return next_ugly

class Solution:
    def nthUglyNumber(self, n: int) -> int:
        ugly_set = {1: True}
        ugly_seq = [1]
        size = 1
        ugly_factors = [UglyFactor(ugly_set, ugly_seq, 0, 2), UglyFactor(ugly_set, ugly_seq, 0, 3), UglyFactor(ugly_set, ugly_seq, 0, 5)]
        while size < n:
            next_uglys = []
            for ugly_factor in ugly_factors:
                next_uglys.append(ugly_factor.getNextUgly())
            min_idx = 0
            next_ugly = next_uglys[0]
            for i in range(1,3):
                if next_ugly > next_uglys[i]:
                    next_ugly = next_uglys[i]
                    min_idx = i
            ugly_set[next_ugly] = True
            ugly_seq.append(next_ugly)
            size += 1
        return ugly_seq[n - 1]
        
