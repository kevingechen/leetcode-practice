class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        return binarySearchMatrix(matrix, target, 0, m * n - 1, n);
    }

    private boolean binarySearchMatrix(int[][] matrix, int target, int left, int right, int n) {
        if (left > right) {
            return false;
        }

        int mid = left + (right - left) / 2;
        int midRow = mid / n;
        int midCol = mid % n;
        if (matrix[midRow][midCol] == target) {
            return true;
        } else if (matrix[midRow][midCol] > target) {
            return binarySearchMatrix(matrix, target, left, mid - 1, n);
        } else {
            return binarySearchMatrix(matrix, target, mid + 1, right, n);
        }
    }
}
