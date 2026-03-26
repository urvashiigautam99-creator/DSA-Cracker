import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long totalSum = 0;
        Map<Integer, Integer> totalFreq = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
                totalFreq.put(grid[i][j], totalFreq.getOrDefault(grid[i][j], 0) + 1);
            }
        }

        // Horizontal Cuts
        long topSum = 0;
        Map<Integer, Integer> topFreq = new HashMap<>();
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                topSum += grid[i][j];
                topFreq.put(grid[i][j], topFreq.getOrDefault(grid[i][j], 0) + 1);
            }
            if (isValid(topSum, totalSum - topSum, topFreq, totalFreq, i + 1, n, m - (i + 1), n, grid, true, i)) return true;
        }

        // Vertical Cuts
        long leftSum = 0;
        Map<Integer, Integer> leftFreq = new HashMap<>();
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                leftSum += grid[i][j];
                leftFreq.put(grid[i][j], leftFreq.getOrDefault(grid[i][j], 0) + 1);
            }
            if (isValid(leftSum, totalSum - leftSum, leftFreq, totalFreq, m, j + 1, m, n - (j + 1), grid, false, j)) return true;
        }

        return false;
    }

    private boolean isValid(long s1, long s2, Map<Integer, Integer> freq1, Map<Integer, Integer> totalFreq, 
                           int h1, int w1, int h2, int w2, int[][] grid, boolean isHorizontal, int cutIdx) {
        
        // 1. Perfect Match
        if (s1 == s2) return true;

        // 2. Discount from Section 1
        long diff1 = s1 - s2;
        if (diff1 > 0 && diff1 <= Integer.MAX_VALUE && freq1.containsKey((int)diff1)) {
            if (canRemove(h1, w1, (int)diff1, grid, isHorizontal, cutIdx, true)) return true;
        }

        // 3. Discount from Section 2
        long diff2 = s2 - s1;
        if (diff2 > 0 && diff2 <= Integer.MAX_VALUE) {
            int val = (int)diff2;
            int countInSection2 = totalFreq.getOrDefault(val, 0) - freq1.getOrDefault(val, 0);
            if (countInSection2 > 0) {
                if (canRemove(h2, w2, val, grid, isHorizontal, cutIdx, false)) return true;
            }
        }

        return false;
    }

    private boolean canRemove(int h, int w, int val, int[][] grid, boolean isHorizontal, int cutIdx, boolean isFirstSection) {
        // If the section is a block (both dimensions > 1), any cell removal keeps it connected.
        if (h > 1 && w > 1) return true;
        
        // If it's a 1x1, removing it makes it empty (not allowed by problem constraints).
        if (h * w <= 1) return false;

        // If it's a 1xN or Mx1 string, we can only remove the "ends" of the string.
        if (isHorizontal) {
            // Section 1 is grid[0...cutIdx][0...n-1], Section 2 is grid[cutIdx+1...m-1][0...n-1]
            if (h == 1) { // It's a single row
                int row = isFirstSection ? cutIdx : cutIdx + 1;
                return grid[row][0] == val || grid[row][w - 1] == val;
            }
            if (w == 1) { // It's a single column
                return grid[isFirstSection ? 0 : cutIdx + 1][0] == val || grid[isFirstSection ? cutIdx : grid.length - 1][0] == val;
            }
        } else {
            // Vertical cut logic for 1D cases
            if (h == 1) {
                return grid[0][isFirstSection ? 0 : cutIdx + 1] == val || grid[0][isFirstSection ? cutIdx : grid[0].length - 1] == val;
            }
            if (w == 1) {
                int col = isFirstSection ? cutIdx : cutIdx + 1;
                return grid[0][col] == val || grid[h - 1][col] == val;
            }
        }
        return true; 
    }
}