class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        int oneHitIndex = binarySearchTarget(nums, target, 0, nums.length - 1);
        if (-1 == oneHitIndex) {
            return result;
        } else {
            result[0] = binarySearchLeftBorder(nums, target, 0, oneHitIndex);
            result[1] = binarySearchRightBorder(nums, target, oneHitIndex, nums.length - 1);
            return result;
        }
    }

    private int binarySearchTarget(int []nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + ((right - left) / 2);
        if (target == nums[mid]) {
            return mid;
        } else if (target > nums[mid]) {
            return binarySearchTarget(nums, target, mid+1, right);
        } else {
            return binarySearchTarget(nums, target, left, mid-1);
        }
    }

    private int binarySearchLeftBorder(int []nums, int target, int left, int right) {
        if (left >= right) {
            return right;
        }

        int mid = left + ((right - left) / 2);
        if (target == nums[mid]) {
            return binarySearchLeftBorder(nums, target, left, mid);
        } else {
            return binarySearchLeftBorder(nums, target, mid+1, right);
        }
    }

    private int binarySearchRightBorder(int []nums, int target, int left, int right) {
        if (left >= right) {
            return left;
        }

        int mid = left + ((right - left + 1) / 2);
        if (target == nums[mid]) {
            return binarySearchRightBorder(nums, target, mid, right);
        } else {
            return binarySearchRightBorder(nums, target, left, mid-1);
        }
    }
}
