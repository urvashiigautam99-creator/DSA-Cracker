class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] result = new int[n];
        
        int left = 0;
        int right = n - 1;
        
        // Fill smaller elements from the left and larger from the right
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            if (nums[i] < pivot) {
                result[left++] = nums[i];
            }
            if (nums[j] > pivot) {
                result[right--] = nums[j];
            }
        }
        
        // Fill the remaining middle positions with the pivot value
        while (left <= right) {
            result[left++] = pivot;
        }
        
        return result;
    }
}