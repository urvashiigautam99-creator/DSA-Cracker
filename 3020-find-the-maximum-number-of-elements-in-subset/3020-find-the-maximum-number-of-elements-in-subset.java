import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maximumLength(int[] nums) {
        // Find the maximum value in the array to avoid unnecessary squaring loops
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        
        // Step 1: Count the frequency of each number
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        // Step 2: Handle the special edge case for 1
        int ans = 0;
        if (countMap.containsKey(1)) {
            int countOfOnes = countMap.get(1);
            // Must be an odd length
            ans = (countOfOnes % 2 == 0) ? countOfOnes - 1 : countOfOnes;
        } else {
            ans = 1; // Any single element can form a subset of length 1
        }
        
        // Step 3: Iterate through each unique number to try it as the base 'x'
        for (int num : countMap.keySet()) {
            if (num == 1) continue;
            
            int currentLength = 0;
            long x = num; // Use long to prevent integer overflow when squaring
            
            // Collect pairs as long as the frequency is >= 2
            while (x <= maxNum && countMap.containsKey((int) x) && countMap.get((int) x) >= 2) {
                currentLength += 2;
                x *= x;
            }
            
            // Check the peak element capability
            // If the final x exists at least once, it can be the peak (+1)
            // If it doesn't exist, we must break one pair from the last valid layer (-1)
            if (x <= maxNum && countMap.containsKey((int) x)) {
                currentLength += 1;
            } else {
                currentLength -= 1;
            }
            
            ans = Math.max(ans, currentLength);
        }
        
        return ans;
    }
}