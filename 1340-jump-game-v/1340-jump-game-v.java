class Solution {
    private int[] memo;
    private int n;

    public int maxJumps(int[] arr, int d) {
        n = arr.length;
        memo = new int[n];
        int maxTotal = 0;

        for (int i = 0; i < n; i++) {
            maxTotal = Math.max(maxTotal, dfs(arr, d, i));
        }
        return maxTotal;
    }

    private int dfs(int[] arr, int d, int i) {
        if (memo[i] != 0) {
            return memo[i];
        }

        int maxVisited = 1;

        // Explore Left
        for (int j = i - 1; j >= Math.max(0, i - d); j--) {
            if (arr[j] >= arr[i]) break;
            maxVisited = Math.max(maxVisited, 1 + dfs(arr, d, j));
        }

        // Explore Right
        for (int j = i + 1; j <= Math.min(n - 1, i + d); j++) {
            if (arr[j] >= arr[i]) break;
            maxVisited = Math.max(maxVisited, 1 + dfs(arr, d, j));
        }

        memo[i] = maxVisited;
        return maxVisited;
    }
}