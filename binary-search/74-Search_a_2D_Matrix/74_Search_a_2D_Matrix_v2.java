class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int targetRow = binarySearchTargetRow(matrix, target, 0, m - 1);
        return binarySearchTargetInRow(matrix[targetRow], target, 0, n - 1);
    }

    private int binarySearchTargetRow(int[][] matrix, int target, int low, int high) {
        if (target >= matrix[high][0]) {
            return high;
        }

        if (low == high || high == low + 1) {
            return low;
        }

        int mid = low + (high - low) / 2;
        if (target >= matrix[mid][0]) {
            return binarySearchTargetRow(matrix, target, mid, high);
        } else {
            return binarySearchTargetRow(matrix, target, low, mid - 1);
        }

    }

    private boolean binarySearchTargetInRow(int[] row, int target, int left, int right) {
        if (left > right) {
            return false;
        }

        int mid = left + (right - left) / 2;
        if (row[mid] == target) {
            return true;
        } else if (row[mid] > target) {
            return binarySearchTargetInRow(row, target, left, mid - 1);
        } else {
            return binarySearchTargetInRow(row, target, mid + 1, right);
        }
    }
}
