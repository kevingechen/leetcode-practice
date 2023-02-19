class Solution:
    def setZeroes(self, matrix):
        """
        Do not return anything, modify matrix in-place instead.
        """
        zero_rows_map = {}
        zero_cols_map = {}
        row_num = len(matrix)
        col_num = len(matrix[0])
        for x in range(row_num):
            for y in range(col_num):
                if 0 == matrix[x][y]:
                    zero_rows_map[x] = 0
                    zero_cols_map[y] = 0
        for x in range(row_num):
            for y in range(col_num):
                if x in zero_rows_map or y in zero_cols_map:
                    matrix[x][y] = 0
