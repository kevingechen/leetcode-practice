class Solution:
    def getHint(self, secret: str, guess: str) -> str:
        length = len(secret)
        bulls, cows = 0, 0
        secret_extra_map, guess_extra_map = {}, {}
        for i in range(length):
            s, g = secret[i], guess[i]
            if s == g:
                bulls += 1
            else:
                secret_extra_map.setdefault(s, 0)
                secret_extra_map[s] += 1
                guess_extra_map.setdefault(g, 0)
                guess_extra_map[g] += 1
        for k,v in secret_extra_map.items():
            cows += min(v, guess_extra_map.get(k, 0))
        return str.format("{}A{}B", bulls, cows)
