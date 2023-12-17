class Solution {
public:
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();

        vector<vector<int>> dpMatrix(m, vector<int>(n, 0));
        int longestSize = 1;
        int currentSize;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                currentSize = longestIncreasingPathStartingFrom(i, j, matrix, dpMatrix);
                longestSize = max(longestSize, currentSize);
            }
        }

        return longestSize;
    }

    int longestIncreasingPathStartingFrom(int row, int col, vector<vector<int>>& matrix, vector<vector<int>>& dpMatrix) {
        int m = matrix.size();
        int n = matrix[0].size();

        int result = 1;
        if (row < m - 1 && matrix[row+1][col] > matrix[row][col]) {
            if (dpMatrix[row+1][col] == 0) {
                dpMatrix[row+1][col] = longestIncreasingPathStartingFrom(row+1, col, matrix, dpMatrix);
            }
            result = max(result, 1 + dpMatrix[row+1][col]);
        }
        if (row > 0 && matrix[row-1][col] > matrix[row][col]) {
            if (dpMatrix[row-1][col] == 0) {
                dpMatrix[row-1][col] = longestIncreasingPathStartingFrom(row-1, col, matrix, dpMatrix);
            }
            result = max(result, 1 + dpMatrix[row-1][col]);
        }
        if (col < n - 1 && matrix[row][col+1] > matrix[row][col]) {
            if (dpMatrix[row][col+1] == 0) {
                dpMatrix[row][col+1] = longestIncreasingPathStartingFrom(row, col+1, matrix, dpMatrix);
            }
            result = max(result, 1 + dpMatrix[row][col+1]);
        }
        if (col > 0 && matrix[row][col-1] > matrix[row][col]) {
            if (dpMatrix[row][col-1] == 0) {
                dpMatrix[row][col-1] = longestIncreasingPathStartingFrom(row, col-1, matrix, dpMatrix);
            }
            result = max(result, 1 + dpMatrix[row][col-1]);
        }

        return result;
    }
};
