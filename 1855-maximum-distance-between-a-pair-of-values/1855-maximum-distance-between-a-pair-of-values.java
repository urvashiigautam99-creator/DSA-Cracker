class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int maxDist = 0;
        int i = 0; // Pointer for nums1
        int j = 0; // Pointer for nums2
        
        int n = nums1.length;
        int m = nums2.length;
        
        while (i < n && j < m) {
            // If the condition is satisfied
            if (nums1[i] <= nums2[j]) {
                // Update maximum distance found so far
                maxDist = Math.max(maxDist, j - i);
                // Try to increase j to see if we can get a larger distance
                j++;
            } else {
                // If nums1[i] > nums2[j], we need a smaller nums1[i]
                // Since arrays are non-increasing, incrementing i gives a smaller or equal value
                i++;
                // Optimization: Ensure i doesn't get ahead of j unnecessarily
                // though the logic works even if i moves ahead.
                if (i > j) {
                    j = i;
                }
            }
        }
        
        return maxDist;
    }
}