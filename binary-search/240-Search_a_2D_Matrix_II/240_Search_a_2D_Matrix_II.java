class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix[0].length;
        return recursiveSearchMatrix(matrix, target, 0, n-1);
    }

    public boolean recursiveSearchMatrix(int[][] matrix, int target, int top, int right) {
        if (top >= matrix.length || right < 0) {
            return false;
        }

        if (matrix[top][right] == target) {
            return true;
        } else if (matrix[top][right] > target) {
            return recursiveSearchMatrix(matrix, target, top, right-1);
        } else {
            return recursiveSearchMatrix(matrix, target, top+1, right);
        }
    }
}
