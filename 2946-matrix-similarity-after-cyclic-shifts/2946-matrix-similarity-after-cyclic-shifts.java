class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        
        // We only care about k relative to the row length n
        k = k % n;
        
        // If k is 0, no change happens, so it's always similar
        if (k == 0) return true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // For any row, the element at j must match the element
                // that would be rotated into its position.
                // This covers both left and right shifts because similarity
                // implies mat[i][j] == mat[i][(j + k) % n]
                if (mat[i][j] != mat[i][(j + k) % n]) {
                    return false;
                }
            }
        }
        
        return true;
    }
}