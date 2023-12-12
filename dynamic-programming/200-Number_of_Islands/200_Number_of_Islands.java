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
