class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isValid(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isValid(int i) {
        boolean hasChangingDigit = false;
        
        while (i > 0) {
            int digit = i % 10;
            
            // Check for invalid digits
            if (digit == 3 || digit == 4 || digit == 7) {
                return false;
            }
            
            // Check if the digit actually changes the number (2, 5, 6, 9)
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                hasChangingDigit = true;
            }
            
            i /= 10;
        }
        
        // It must be valid AND have at least one digit that changes
        return hasChangingDigit;
    }
}