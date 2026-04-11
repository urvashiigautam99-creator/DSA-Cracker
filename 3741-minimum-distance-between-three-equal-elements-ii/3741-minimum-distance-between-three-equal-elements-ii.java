import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        // Map to store the list of indices for each number
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int minDistance = Integer.MAX_VALUE;
        boolean found = false;
        
        // Iterate through each unique number's indices
        for (List<Integer> indices : indexMap.values()) {
            // We need at least 3 occurrences to form a "good" tuple
            if (indices.size() >= 3) {
                found = true;
                for (int p = 0; p <= indices.size() - 3; p++) {
                    // Distance for sorted indices i, j, k is 2 * (k - i)
                    int i = indices.get(p);
                    int k = indices.get(p + 2);
                    int currentDistance = 2 * (k - i);
                    
                    minDistance = Math.min(minDistance, currentDistance);
                }
            }
        }
        
        return found ? minDistance : -1;
    }
}