class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            // Check if the current element matches our target
            if (nums[i] == target) {
                // Calculate the absolute distance from 'start'
                int currentDistance = Math.abs(i - start);
                
                // Update minDistance if this one is smaller
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                }
                
                // Optimization: If distance is 0, we can't get any smaller
                if (minDistance == 0) return 0;
            }
        }
        
        return minDistance;
    }
}