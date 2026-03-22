import java.util.*;

class Solution {
    public long countGoodSubarrays(int[] nums) {
        long count = 0;
        int n = nums.length;
        
        // Stores pairs of {OR value, starting index} for all distinct ORs ending at current index
        List<int[]> currentOrs = new ArrayList<>();
        // Stores the last seen index of every number to check existence in subarray
        Map<Integer, Integer> lastSeen = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            lastSeen.put(val, i);
            
            List<int[]> nextOrs = new ArrayList<>();
            // Start a new OR sequence with the current element
            nextOrs.add(new int[]{val, i});
            
            for (int[] prev : currentOrs) {
                int newOr = prev[0] | val;
                // If the OR value is the same as the last one added, 
                // we keep the leftmost index (prev[1]) to cover the largest range.
                if (newOr == nextOrs.get(nextOrs.size() - 1)[0]) {
                    nextOrs.get(nextOrs.size() - 1)[1] = prev[1];
                } else {
                    nextOrs.add(new int[]{newOr, prev[1]});
                }
            }
            currentOrs = nextOrs;

            // At index i, we have several ranges [start, i] with a constant OR value.
            // currentOrs is sorted by index descending (i, then smaller indices).
            // Example: [{OR: 14, start: 0}, {OR: 6, start: 2}, {OR: 2, start: 3}]
            int rightBoundary = i;
            for (int[] orInfo : currentOrs) {
                int targetOr = orInfo[0];
                int leftBoundary = orInfo[1];
                
                // The OR value must exist as an element within [leftBoundary, i]
                Integer lastPos = lastSeen.get(targetOr);
                if (lastPos != null && lastPos >= leftBoundary) {
                    // All subarrays starting from leftBoundary up to 
                    // min(lastPos, rightBoundary) are valid.
                    int validStartEnd = Math.min(lastPos, rightBoundary);
                    if (validStartEnd >= leftBoundary) {
                        count += (validStartEnd - leftBoundary + 1);
                    }
                }
                rightBoundary = leftBoundary - 1;
            }
        }
        
        return count;
    }
}