class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = 0;
        int capacity = 0;
        while (right < height.length-1) {
            if (height[left] <= height[right]) {
                left = findNextLeftPeak(height, right);
                right = left;
            }
            right = findNextRightPeak(height, right+1);
            capacity += updateCapacityInBetween(height, left, right);
        }

        return capacity;
    }

    private int findNextLeftPeak(int[] height, int start) {
        if (0 == start) return 0;
        int i = start;
        while (i < height.length-1) {
            if (height[i-1] <= height[i] && height[i] > height[i+1]) {
                break;
            }
            i++;
        }

        return i;
    }

    private int findNextRightPeak(int[] height, int start) {
        if (height.length-1 <= start) return height.length-1;
        int i = start;
        while (i < height.length-1) {
            if (height[i-1] < height[i] && height[i] >= height[i+1]) {
                break;
            }
            i++;
        }

        return i;
    }

    private int updateCapacityInBetween(int[] height, int left, int right) {
        int low = Math.min(height[left], height[right]);
        int capacity = 0;
        for (int i = left+1; i < right; i++) {
            if (height[i] < low) {
                capacity += low - height[i];
                height[i] = low;
            }
        }

        return capacity;
    }
}
