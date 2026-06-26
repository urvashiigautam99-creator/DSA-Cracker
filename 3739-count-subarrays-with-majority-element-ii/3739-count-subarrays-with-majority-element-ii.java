class Solution {
    // Fenwick Tree (Binary Indexed Tree) implementation for range queries
    class FenwickTree {
        private int[] tree;
        private int size;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }

        // Add 1 to the frequency of index x
        public void update(int x, int delta) {
            for (; x <= size; x += x & -x) {
                tree[x] += delta;
            }
        }

        // Query the cumulative frequency from 1 to x
        public int query(int x) {
            int sum = 0;
            for (; x > 0; x -= x & -x) {
                sum += tree[x];
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        // The prefix sum ranges from -n to n. 
        // Total possible unique states = 2 * n + 1.
        FenwickTree bit = new FenwickTree(2 * n + 1);
        
        // Start tracking prefix sum from an initial offset to handle negative values safely.
        int currentPrefixSum = n + 1;
        
        // Insert the initial prefix sum of 0 (shifted by n + 1) into the tree
        bit.update(currentPrefixSum, 1);
        
        long totalSubarrays = 0;
        
        for (int num : nums) {
            // Transform target to +1, and everything else to -1
            if (num == target) {
                currentPrefixSum += 1;
            } else {
                currentPrefixSum -= 1;
            }
            
            // Count all previous prefix sums that are strictly less than currentPrefixSum
            totalSubarrays += bit.query(currentPrefixSum - 1);
            
            // Add the current prefix sum state into the Fenwick Tree
            bit.update(currentPrefixSum, 1);
        }
        
        return totalSubarrays;
    }
}