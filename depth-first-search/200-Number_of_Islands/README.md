# [200. Number of Islands](https://leetcode.com/problems/number-of-islands/description/)

## Description
Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water), return *the number of islands*.

An `island` is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

**Example 1:**
```
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
```

**Example 2:**
```
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
```

**Constraints:**
+ `m == grid.length`
+ `n == grid[i].length`
+ `1 <= m, n <= 300`
+ `grid[i][j]` is `'0'` or `'1'`.

## Solution
### Java
```java
class Solution {
    public int numIslands(char[][] grid) {
        int islandNum = 0;
        int m = grid.length;
        int n = grid[0].length;
        Map<Integer, Integer> index2IslandIdMap = new HashMap<>(m * n);
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> islandIndexSet;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }

                Integer flatIndex = getFlatIndex(i, j, n);
                Integer existingIslandId = index2IslandIdMap.get(flatIndex);
                if (Objects.nonNull(existingIslandId)) {
                    continue;
                }

                islandNum += 1;
                queue.add(flatIndex);
                islandIndexSet = new HashSet<>();
                islandIndexSet.add(flatIndex);
                while (!queue.isEmpty()) {
                    Integer currentIndex = queue.remove();
                    index2IslandIdMap.put(currentIndex, islandNum);
                    int row = getRow(currentIndex, n);
                    int col = getCol(currentIndex, n);
                    if (col > 0 && grid[row][col-1] == '1') {
                        updateIslandStatus(row, col-1, n, queue, islandIndexSet);
                    }
                    if (col < n-1 && grid[row][col+1] == '1') {
                        updateIslandStatus(row, col+1, n, queue, islandIndexSet);
                    }
                    if (row > 0 && grid[row-1][col] == '1') {
                        updateIslandStatus(row-1, col, n, queue, islandIndexSet);
                    }
                    if (row < m-1 && grid[row+1][col] == '1') {
                        updateIslandStatus(row+1, col, n, queue, islandIndexSet);
                    }
                
                }
            }
        }

        return islandNum;
    }

    public int getFlatIndex(int row, int col, int rowSize) {
        return row * rowSize + col;
    }

    public int getRow(int index, int rowSize) {
        return index / rowSize;
    }

    public int getCol(int index, int rowSize) {
        return index % rowSize;
    }

    private void updateIslandStatus(int row, int col, int rowSize,
                                    Deque<Integer> queue, Set<Integer> islandIndexSet) {
        int index = getFlatIndex(row, col, rowSize);
        if (!islandIndexSet.contains(index)) {
            queue.add(index);
            islandIndexSet.add(index);
        }
    }
}
```

### CPP
```cpp
class Solution {
private:
    int n, m;

    void DFSMarking(vector<vector<char>>& grid, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }
    
public:
    int numIslands(vector<vector<char>>& grid) {
        int count = 0;
        n = grid.size();
        if (n == 0) return 0;
        m = grid[0].size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    ++count;
                }
            }
        }
        return count;
    }
};
```
