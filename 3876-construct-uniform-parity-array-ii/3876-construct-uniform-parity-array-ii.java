import java.util.Arrays;

class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;
        int oddCount = 0;

        for (int num : nums1) {
            if (num % 2 == 0) {
                minEven = Math.min(minEven, num);
            } else {
                minOdd = Math.min(minOdd, num);
                oddCount++;
            }
        }

        // Target: All Even
        // Only possible if there are NO odd numbers to begin with.
        // Because the smallest odd number cannot be turned even (nothing smaller to subtract).
        if (oddCount == 0) return true;

        // Target: All Odd
        // Possible if there is at least one odd number AND
        // the smallest odd number is smaller than the smallest even number.
        // If minOdd < minEven, we can use minOdd to flip every even number to odd.
        if (oddCount > 0 && minOdd < minEven) {
            return true;
        }

        return false;
    }
}