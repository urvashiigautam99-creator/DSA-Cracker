class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        // delta[i] stores the difference in moves for target sum i
        int[] delta = new int[2 * limit + 2];
        
        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);
            
            // Default: 2 moves for all possible target sums [2, 2 * limit]
            delta[2] += 2;
            delta[2 * limit + 1] -= 2;
            
            // 1 move: if target sum is in [1 + a, limit + b]
            // We subtract 1 move from the range [1 + a, limit + b]
            delta[a + 1] -= 1;
            delta[limit + b + 1] += 1;
            
            // 0 moves: if target sum is exactly a + b
            // We subtract another 1 move for the specific point a + b
            delta[a + b] -= 1;
            delta[a + b + 1] += 1;
        }
        
        int minMoves = n;
        int currentMoves = 0;
        // Calculate prefix sum to find the target sum with minimum moves
        for (int i = 2; i <= 2 * limit; i++) {
            currentMoves += delta[i];
            minMoves = Math.min(minMoves, currentMoves);
        }
        
        return minMoves;
    }
}