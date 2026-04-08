class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        long MOD = 1000000007L;
        
        // 1. Process each query
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            int k = query[2];
            long v = query[3];
            
            // 2. Update the array based on the jump 'k'
            for (int idx = l; idx <= r; idx += k) {
                long newVal = (nums[idx] * v) % MOD;
                nums[idx] = (int) newVal;
            }
        }
        
        // 3. Calculate the final XOR sum
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }
}