class Solution {
    public boolean uniformArray(int[] nums1) {
        // To make everything even: 
        // - If nums1[i] is odd, we need another odd nums1[j] to subtract.
        // - If there are 0 odd numbers, everything is already even (True).
        // - If there are 2+ odd numbers, we can flip any odd to even (True).
        // - If there is exactly 1 odd number, we can't flip it to even 
        //   because we'd need another odd number to subtract!
        
        // To make everything odd:
        // - We need at least one odd number to start with or flip evens.
        
        int oddCount = 0;
        for (int num : nums1) {
            if (Math.abs(num) % 2 != 0) {
                oddCount++;
            }
        }

        // If we have at least one odd number, we can definitely make 
        // all elements odd (by using that odd number to flip evens).
        // If we have 0 or >= 2 odd numbers, we can make everything even.
        
        // In this specific LeetCode problem "Construct Uniform Parity Array I", 
        // the logic simplifies to:
        return true; 
    }
}