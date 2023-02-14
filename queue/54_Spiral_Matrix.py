class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        height, width = len(matrix), len(matrix[0])
        x_min, x_max = 0, width - 1
        y_min, y_max = 0, height - 1
        results = []
        while x_min <= x_max and y_min <= y_max:
            if x_min == x_max:
                for y in range(y_min, y_max + 1):
                    results.append(matrix[y][x_min])
            elif y_min == y_max:
                for x in range(x_min, x_max + 1):
                    results.append(matrix[y_min][x])
            else:
                for x in range(x_min, x_max):
                    results.append(matrix[y_min][x])
                for y in range(y_min, y_max):
                    results.append(matrix[y][x_max])
                for x in range(x_max, x_min, -1):
                    results.append(matrix[y_max][x])
                for y in range(y_max, y_min, -1):
                    results.append(matrix[y][x_min])
            x_min, x_max = x_min + 1, x_max - 1
            y_min, y_max = y_min + 1, y_max - 1
        return results
