import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int resM = m - k + 1;
        int resN = n - k + 1;
        int[][] res = new int[resM][resN];

        // Process column by column to slide the window vertically
        for (int j = 0; j <= n - k; j++) {
            // TreeMap stores only DISTINCT values and their frequencies
            TreeMap<Integer, Integer> map = new TreeMap<>();
            
            // Initialize the first k*k window for the current column set
            for (int r = 0; r < k; r++) {
                for (int c = j; c < j + k; c++) {
                    int val = grid[r][c];
                    map.put(val, map.getOrDefault(val, 0) + 1);
                }
            }
            res[0][j] = getMinDiffOfDistinct(map);

            // Slide vertically
            for (int i = 1; i < resM; i++) {
                // Remove elements of the row that is exiting the top
                for (int c = j; c < j + k; c++) {
                    int val = grid[i - 1][c];
                    int count = map.get(val);
                    if (count == 1) map.remove(val);
                    else map.put(val, count - 1);
                }
                // Add elements of the row that is entering the bottom
                for (int c = j; c < j + k; c++) {
                    int val = grid[i + k - 1][c];
                    map.put(val, map.getOrDefault(val, 0) + 1);
                }
                res[i][j] = getMinDiffOfDistinct(map);
            }
        }
        return res;
    }

    private int getMinDiffOfDistinct(TreeMap<Integer, Integer> map) {
        // Rule: If all elements in the submatrix have the same value, answer is 0.
        if (map.size() <= 1) {
            return 0;
        }

        int minDiff = Integer.MAX_VALUE;
        Integer prev = null;

        // Iterate through the keys (which are the distinct values)
        for (int current : map.keySet()) {
            if (prev != null) {
                int diff = Math.abs(current - prev);
                if (diff < minDiff) minDiff = diff;
                // Optimization: if we find 1, it's the smallest possible 
                // positive integer difference (assuming integers)
                if (minDiff == 1) return 1;
            }
            prev = current;
        }

        return minDiff;
    }
}