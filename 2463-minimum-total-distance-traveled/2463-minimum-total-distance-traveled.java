import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // 1. Sort robots by position
        Collections.sort(robot);
        
        // 2. Sort factories by position
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 3. Flatten factories based on their limits
        List<Integer> factorySlots = new ArrayList<>();
        for (int[] f : factory) {
            for (int i = 0; i < f[1]; i++) {
                factorySlots.add(f[0]);
            }
        }
        
        int n = robot.size();
        int m = factorySlots.size();
        
        // 4. DP Table
        // dp[i][j] = min distance for first i robots using first j factory slots
        long[][] dp = new long[n + 1][m + 1];
        
        // Initialization: 
        // If 0 robots, distance is 0. 
        // If 0 slots but >0 robots, distance is infinity.
        long INF = 1_000_000_000_000_000L; // Use a long literal with 'L'
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        // 5. Fill DP Table
        for (int j = 1; j <= m; j++) {
            for (int i = 1; i <= n; i++) {
                // Option 1: Don't use the current factory slot for robot i
                dp[i][j] = dp[i][j - 1];
                
                // Option 2: Use the current factory slot for robot i
                // Cost is the distance between robot i-1 and factory slot j-1
                long currentDist = Math.abs((long)robot.get(i - 1) - factorySlots.get(j - 1));
                if (dp[i - 1][j - 1] != INF) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + currentDist);
                }
            }
        }
        
        return dp[n][m];
    }
}