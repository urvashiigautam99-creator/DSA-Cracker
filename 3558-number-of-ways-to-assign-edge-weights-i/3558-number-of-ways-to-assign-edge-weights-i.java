import java.util.*;

public class Solution {
    private int maxDepth = 0;

    public int assignEdgeWeights(int[][] edges) {
        // Step 1: Find the number of nodes (n) dynamically from the edge list
        int n = 0;
        for (int[] edge : edges) {
            n = Math.max(n, Math.max(edge[0], edge[1]));
        }

        // Step 2: Build the adjacency list for the tree
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Step 3: DFS to find the maximum depth (number of edges) starting from root 1
        maxDepth = 0;
        dfs(1, -1, 0, adj);

        if (maxDepth == 0) return 0;
        
        long MOD = 1_000_000_007;
        // Result is 2^(maxDepth - 1) % MOD
        return (int) power(2, maxDepth - 1, MOD);
    }

    private void dfs(int node, int parent, int currentDepth, List<List<Integer>> adj) {
        maxDepth = Math.max(maxDepth, currentDepth);
        
        for (int neighbor : adj.get(node)) {
            if (neighbor != parent) {
                dfs(neighbor, node, currentDepth + 1, adj);
            }
        }
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}