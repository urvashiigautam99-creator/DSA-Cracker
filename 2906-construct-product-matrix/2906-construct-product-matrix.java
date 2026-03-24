class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] p = new int[n][m];
        long product = 1;
        int MOD = 12345;

        // Step 1: Forward Pass (Prefix Product)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = (int) product;
                product = (product * (grid[i][j] % MOD)) % MOD;
            }
        }

        // Step 2: Backward Pass (Suffix Product)
        product = 1; // Reset product for suffix
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                p[i][j] = (int) ((p[i][j] * product) % MOD);
                product = (product * (grid[i][j] % MOD)) % MOD;
            }
        }

        return p;
    }
}