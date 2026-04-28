import java.util.Arrays;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int[] flat = new int[m * n];
        int index = 0;
        
        // 1. Flatten the grid and check for validity
        int remainder = grid[0][0] % x;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] % x != remainder) {
                    return -1;
                }
                flat[index++] = grid[i][j];
            }
        }
        
        // 2. Sort to find the median
        Arrays.sort(flat);
        int median = flat[flat.length / 2];
        
        // 3. Calculate total operations to reach the median
        int operations = 0;
        for (int val : flat) {
            operations += Math.abs(val - median) / x;
        }
        
        return operations;
    }
}