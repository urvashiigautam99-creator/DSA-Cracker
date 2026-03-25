class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        long totalSum = 0;

        // 1. Calculate the total sum of the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                totalSum += grid[i][j];
            }
        }

        // 2. If the total sum is odd, we can't split it into two equal integers
        if (totalSum % 2 != 0) {
            return false;
        }
        
        long target = totalSum / 2;

        // 3. Check Horizontal Cuts
        long currentRowSum = 0;
        // We stop at rows - 1 because the second section must be non-empty
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols; j++) {
                currentRowSum += grid[i][j];
            }
            if (currentRowSum == target) {
                return true;
            }
        }

        // 4. Check Vertical Cuts
        long currentColSum = 0;
        // We stop at cols - 1 because the second section must be non-empty
        for (int j = 0; j < cols - 1; j++) {
            for (int i = 0; i < rows; i++) {
                currentColSum += grid[i][j];
            }
            if (currentColSum == target) {
                return true;
            }
        }

        return false;
    }
}