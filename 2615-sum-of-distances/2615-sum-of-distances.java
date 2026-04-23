import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        
        // Map to store value -> list of indices where that value appears
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        // Process each group of indices
        for (List<Integer> indices : map.values()) {
            int size = indices.size();
            if (size <= 1) continue;
            
            // Calculate total sum of indices in this group
            long totalSum = 0;
            for (int index : indices) {
                totalSum += index;
            }
            
            long prefixSum = 0;
            for (int i = 0; i < size; i++) {
                int currentIndex = indices.get(i);
                
                // Number of elements to the left and right
                long countLeft = i;
                long countRight = size - 1 - i;
                
                // Sum of elements to the right
                long suffixSum = totalSum - prefixSum - currentIndex;
                
                // Distance formula: 
                // (currentIndex * countLeft - prefixSum) + (suffixSum - currentIndex * countRight)
                long leftDist = (currentIndex * countLeft) - prefixSum;
                long rightDist = suffixSum - (currentIndex * countRight);
                
                arr[currentIndex] = leftDist + rightDist;
                
                // Update prefixSum for the next iteration
                prefixSum += currentIndex;
            }
        }
        
        return arr;
    }
}