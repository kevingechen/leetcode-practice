# [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions/description/)

## Description
Given an `m x n` matrix `board` containing `'X'` and `'O'`, *capture all regions that are 4-directionally surrounded by* `'X'`.

A region is **captured** by flipping all `'O'`s into `'X'`s in that surrounded region.


**Example 1:**

![Example 1](./example_1.jpg)
```
Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Notice that an 'O' should not be flipped if:
- It is on the border, or
- It is adjacent to an 'O' that should not be flipped.
The bottom 'O' is on the border, so it is not flipped.
The other three 'O' form a surrounded region, so they are flipped.
```

**Example 2:**
```
Input: board = [["X"]]
Output: [["X"]]
```

**Constraints:**
+ `m == board.length`
+ `n == board[i].length`
+ `1 <= m, n <= 200`
+ `board[i][j]` is `'X'` or `'O'`.


## Solution
### CPP
```cpp
class Solution {
public:
    void DFSMarking(int i, int j,
                    std::vector<std::vector<char>>& board,
                    int rowSize, int colSize) {
        board.size();
        if (i >= 0 && i < rowSize && j >= 0 && j < colSize && board[i][j] == 'O') {
            board[i][j] = 'N';
            DFSMarking(i+1, j, board, rowSize, colSize);
            DFSMarking(i-1, j, board, rowSize, colSize);
            DFSMarking(i, j+1, board, rowSize, colSize);
            DFSMarking(i, j-1, board, rowSize, colSize);
        }
    }

    void solve(vector<vector<char>>& board) {
        int rowSize = board.size();
        int colSize = board[0].size();

        for (int i = 0; i < rowSize; i++) {
            DFSMarking(i, 0, board, rowSize, colSize);
            DFSMarking(i, colSize-1, board, rowSize, colSize);
        }
        for (int j = 0; j < colSize; j++) {
            DFSMarking(0, j, board, rowSize, colSize);
            DFSMarking(rowSize-1, j, board, rowSize, colSize);
        }

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (board[i][j] == 'N') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
```
