class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        
        // dp[i][j][k] -> max coins at (i, j) with k neutralizations used
        // Using a large negative number for initialization to handle negative coin paths
        int[][][] dp = new int[m][n][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE / 2; 
                }
            }
        }

        // Base Case: Starting point (0,0)
        int val = coins[0][0];
        dp[0][0][0] = val; // No neutralization used
        if (val < 0) {
            dp[0][0][1] = 0; // Neutralized the first robber
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                
                for (int k = 0; k < 3; k++) {
                    // Coming from Top or Left
                    int prevMax = Integer.MIN_VALUE / 2;
                    if (i > 0) prevMax = Math.max(prevMax, dp[i-1][j][k]);
                    if (j > 0) prevMax = Math.max(prevMax, dp[i][j-1][k]);
                    
                    // Case 1: Don't neutralize this cell (or it's positive)
                    if (prevMax != Integer.MIN_VALUE / 2) {
                        dp[i][j][k] = Math.max(dp[i][j][k], prevMax + coins[i][j]);
                    }
                    
                    // Case 2: Neutralize this cell (only if it's a robber and we have k > 0)
                    if (coins[i][j] < 0 && k > 0) {
                        int prevMaxLowerK = Integer.MIN_VALUE / 2;
                        if (i > 0) prevMaxLowerK = Math.max(prevMaxLowerK, dp[i-1][j][k-1]);
                        if (j > 0) prevMaxLowerK = Math.max(prevMaxLowerK, dp[i][j-1][k-1]);
                        
                        if (prevMaxLowerK != Integer.MIN_VALUE / 2) {
                            dp[i][j][k] = Math.max(dp[i][j][k], prevMaxLowerK);
                        }
                    }
                }
            }
        }

        // The answer is the max of having used 0, 1, or 2 neutralizations at the bottom-right
        return Math.max(dp[m-1][n-1][0], Math.max(dp[m-1][n-1][1], dp[m-1][n-1][2]));
    }
}