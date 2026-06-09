class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        
        // Find the absolute max and min in the entire array
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
            if (num < minVal) {
                minVal = num;
            }
        }
        
        // Calculate the maximum possible value for a single subarray
        long maxSingleValue = (long) maxVal - minVal;
        
        // Multiply by k to get the maximum total value for k subarrays
        return maxSingleValue * k;
    }
}