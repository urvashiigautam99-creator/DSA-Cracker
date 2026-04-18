class Solution {
    public int mirrorDistance(int n) {
        int original = n;
        int reversed = 0;
        
        // Step 1: Reverse the digits of n
        int temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            reversed = reversed * 10 + digit;
            temp /= 10;
        }
        
        // Step 2: Calculate absolute difference
        // abs(n - reverse(n))
        return Math.abs(original - reversed);
    }
}