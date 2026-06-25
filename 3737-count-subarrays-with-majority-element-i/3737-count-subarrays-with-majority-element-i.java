class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;
        
        // Loop through every possible starting position of the subarray
        for (int i = 0; i < n; i++) {
            int targetCount = 0;
            
            // Expand the subarray to the right
            for (int j = i; j < n; j++) {
                // If the current element matches our target, increment count
                if (nums[j] == target) {
                    targetCount++;
                }
                
                int currentWindowSize = j - i + 1;
                
                // Target must appear strictly more than half the time
                if (targetCount > currentWindowSize / 2) {
                    ans++;
                }
            }
        }
        
        return ans;
    }
}