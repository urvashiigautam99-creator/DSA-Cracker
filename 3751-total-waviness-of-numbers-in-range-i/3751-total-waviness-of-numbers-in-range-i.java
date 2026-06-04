class Solution {
    public int totalWaviness(int num1, int num2) {
        int totalSum = 0;
        for (int i = num1; i <= num2; i++) {
            totalSum += getWaviness(i);
        }
        return totalSum;
    }

    private int getWaviness(int x) {
        int[] digits = new int[15];
        int len = 0;
        
        while (x > 0) {
            digits[len++] = x % 10;
            x /= 10;
        }
        
        if (len < 3) {
            return 0;
        }
        
        int count = 0;
        for (int i = 1; i < len - 1; i++) {
            if (digits[i] > digits[i - 1] && digits[i] > digits[i + 1]) {
                count++;
            } else if (digits[i] < digits[i - 1] && digits[i] < digits[i + 1]) {
                count++;
            }
        }
        return count;
    }
}