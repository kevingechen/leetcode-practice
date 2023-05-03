# 767. Reorganize String

## Description
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.

**Example 1:**
```
Input: s = "aab"
Output: "aba"
```

**Example 2:**
```
Input: s = "aaab"
Output: ""
```

**Constraints**
+ `1 <= s.length <= 500`
+ `s` consists of lowercase English letters.

## Solution in Java
```java
class CharNode {
    public Character character;
    public Integer count;
    public CharNode(Character character, Integer count) {
        this.character = character;
        this.count = count;
    }
}

class Solution {
    public String reorganizeString(String s) {
        Character maxChar = 'a';
        Integer maxCnt = 0;
        StringBuilder resultBuilder = new StringBuilder();
        Map<Character, Integer> charAmountMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character current = s.charAt(i);
            charAmountMap.putIfAbsent(current, 0);
            Integer prevAmount = charAmountMap.get(current);
            charAmountMap.put(current, prevAmount + 1);
            if (prevAmount + 1 > maxCnt) {
                maxCnt = prevAmount + 1;
                maxChar = current;
            }
            
        }
        int sepIndex = (s.length() + 1) / 2;
        if (maxCnt > sepIndex) {
            return "";
        }

        List<CharNode> firstHalf = new ArrayList<>();
        List<CharNode> secondHalf = new ArrayList<>();
        int accumCnt = 0;
        if (maxCnt == sepIndex) {
            firstHalf.add(new CharNode(maxChar, maxCnt));
            accumCnt = maxCnt;
            charAmountMap.remove(maxChar);
        }
        for (Map.Entry<Character, Integer> entry : charAmountMap.entrySet()) {
            Character c = entry.getKey();
            Integer cnt = entry.getValue();
            CharNode node = new CharNode(c, cnt);
            if (accumCnt < sepIndex) {
                if (accumCnt + cnt <= sepIndex) {
                    firstHalf.add(node);
                } else {
                    CharNode node1 = new CharNode(c, sepIndex - accumCnt);
                    firstHalf.add(node1);
                    CharNode node2 = new CharNode(c, accumCnt + cnt - sepIndex);
                    secondHalf.add(node2);
                }
            } else {
                secondHalf.add(node);
            }
            accumCnt += cnt;
        }
        
        int firstCursor = 0, secondCursor = 0;
        while(firstCursor < firstHalf.size() && secondCursor < secondHalf.size()) {
            resultBuilder.append(firstHalf.get(firstCursor).character);
            firstHalf.get(firstCursor).count -= 1;
            if (firstHalf.get(firstCursor).count == 0) {
                firstCursor += 1;
            }

            resultBuilder.append(secondHalf.get(secondCursor).character);
            secondHalf.get(secondCursor).count -= 1;
            if (secondHalf.get(secondCursor).count == 0) {
                secondCursor += 1;
            }
        }

        if (firstCursor < firstHalf.size()) {
            resultBuilder.append(firstHalf.get(firstCursor).character);
        }

        return resultBuilder.toString();
    }
}
```
