import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int LOG = 18; // 2^17 = 131,072, which is > 10^5

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build the adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] depth = new int[n + 1];
        int[][] parent = new int[LOG][n + 1];
        
        // Initialize parent array with -1
        for (int i = 0; i < LOG; i++) {
            Arrays.fill(parent[i], -1);
        }

        // Step 1: DFS to compute depths and immediate parents (2^0 provider)
        dfs(1, -1, 0, graph, parent, depth);

        // Step 2: Binary Lifting table construction
        for (int k = 1; k < LOG; k++) {
            for (int v = 1; v <= n; v++) {
                if (parent[k - 1][v] != -1) {
                    parent[k][v] = parent[k - 1][parent[k - 1][v]];
                }
            }
        }

        // Step 3: Process queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                ans[i] = 0;
            } else {
                int lcaNode = getLCA(u, v, parent, depth);
                int d = depth[u] + depth[v] - 2 * depth[lcaNode];
                ans[i] = modPow(2, d - 1);
            }
        }

        return ans;
    }

    private void dfs(int u, int p, int d, List<Integer>[] graph, int[][] parent, int[] depth) {
        parent[0][u] = p;
        depth[u] = d;
        for (int v : graph[u]) {
            if (v != p) {
                dfs(v, u, d + 1, graph, parent, depth);
            }
        }
    }

    private int getLCA(int u, int v, int[][] parent, int[] depth) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Bring both nodes to the same depth level
        for (int k = LOG - 1; k >= 0; k--) {
            if (depth[u] - (1 << k) >= depth[v]) {
                u = parent[k][u];
            }
        }

        if (u == v) return u;

        // Lift both nodes simultaneously right below their LCA
        for (int k = LOG - 1; k >= 0; k--) {
            if (parent[k][u] != -1 && parent[k][u] != parent[k][v]) {
                u = parent[k][u];
                v = parent[k][v];
            }
        }

        return parent[0][u];
    }

    private int modPow(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return (int) res;
    }
}