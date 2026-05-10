import java.util.Arrays;

class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        
        // Fill dp array with -1 to indicate the index hasn't been reached yet
        Arrays.fill(dp, -1);
        
        // Starting point: 0 jumps to reach index 0
        dp[0] = 0;
        
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                // Check if index i was reachable and if we can jump from i to j
                if (dp[i] != -1 && Math.abs(nums[j] - nums[i]) <= target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }
        
        return dp[n - 1];
    }
}