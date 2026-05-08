class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize maxSum with the first element to handle arrays with one element
        int maxSum = nums[0];
        int currSum = 0;

        for (int i = 0; i < nums.length; i++) {
            // Add the current element to our running sum
            currSum += nums[i];

            // Update maxSum if the current running sum is greater
            if (currSum > maxSum) {
                maxSum = currSum;
            }

            // If currSum becomes negative, it will only decrease the sum of 
            // any future subarray. So, we reset it to 0.
            if (currSum < 0) {
                currSum = 0;
            }
        }

        return maxSum;
    }
}