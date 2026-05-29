class Solution {
    public int minElement(int[] nums) {
        // Initialize with the maximum possible integer value
        int minSum = Integer.MAX_VALUE;
        
        // Loop through each number in the array
        for (int num : nums) {
            int currentDigitSum = getDigitSum(num);
            
            // Update the minimum sum found so far
            minSum = Math.min(minSum, currentDigitSum);
        }
        
        return minSum;
    }
    
    // Helper method to calculate the sum of digits of a number
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;  // Get the last digit
            num /= 10;        // Remove the last digit
        }
        return sum;
    }
}