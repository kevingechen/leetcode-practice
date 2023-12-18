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
};
