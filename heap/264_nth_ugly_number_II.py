class Solution:
    def nthUglyNumber(self, n: int) -> int:
        ugly_set = {1: True}
        ugly_seq = [1]
        size = 1
        two_base, three_base, five_base = 0, 0, 0
        while size < n:
            next_ugly_by_two = ugly_seq[two_base] * 2
            while next_ugly_by_two in ugly_set:
                two_base += 1
                next_ugly_by_two = ugly_seq[two_base] * 2
            next_ugly_by_three = ugly_seq[three_base] * 3
            while next_ugly_by_three in ugly_set:
                three_base += 1
                next_ugly_by_three = ugly_seq[three_base] * 3
            next_ugly_by_five = ugly_seq[five_base] * 5
            while next_ugly_by_five in ugly_set:
                five_base += 1
                next_ugly_by_five = ugly_seq[five_base] * 5
            next_ugly = min(next_ugly_by_two, next_ugly_by_three, next_ugly_by_five)
            if next_ugly == next_ugly_by_two:
                two_base += 1
            if next_ugly == next_ugly_by_three:
                three_base += 1
            if next_ugly == next_ugly_by_five:
                five_base += 1
            ugly_set[next_ugly] = True
            ugly_seq.append(next_ugly)
            size += 1
        return ugly_seq[n - 1]
        
