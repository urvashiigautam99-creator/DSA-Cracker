public class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Initialize tracking variables with the first element
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            // If the current number is negative, swapping max and min 
            // optimizes the calculation since min * negative becomes the new max
            if (current < 0) {
                int temp = maxSoFar;
                maxSoFar = minSoFar;
                minSoFar = temp;
            }

            // Calculate new boundaries for the current position
            maxSoFar = Math.max(current, maxSoFar * current);
            minSoFar = Math.min(current, minSoFar * current);

            // Update the overall peak max product found so far
            globalMax = Math.max(globalMax, maxSoFar);
        }

        return globalMax;
    }
}