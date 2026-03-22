import java.util.*;

class Solution {
    public int minRemovals(int[] nums, int target) {
        int n = nums.length;
        
        // Use a Map to store {XOR_sum : max_elements_used}
        // This avoids the fixed array size limit (maxVal) issue.
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);

        for (int num : nums) {
            Map<Integer, Integer> nextDp = new HashMap<>(dp);
            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                int currentXor = entry.getKey();
                int count = entry.getValue();
                
                int nextXor = currentXor ^ num;
                nextDp.put(nextXor, Math.max(nextDp.getOrDefault(nextXor, -1), count + 1));
            }
            dp = nextDp;
        }

        if (!dp.containsKey(target)) {
            return -1;
        }

        return n - dp.get(target);
    }
}