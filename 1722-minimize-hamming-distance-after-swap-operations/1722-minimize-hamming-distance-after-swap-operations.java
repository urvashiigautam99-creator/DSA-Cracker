import java.util.*;

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind uf = new UnionFind(n);

        // Group connected indices
        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }

        // Map root index of component -> Frequency map of values in source
        Map<Integer, Map<Integer, Integer>> components = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            components.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> map = components.get(root);
            map.put(source[i], map.getOrDefault(source[i], 0) + 1);
        }

        int totalMatches = 0;
        // Check how many target elements exist in their respective component in source
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            Map<Integer, Integer> map = components.get(root);
            if (map.containsKey(target[i]) && map.get(target[i]) > 0) {
                totalMatches++;
                map.put(target[i], map.get(target[i]) - 1);
            }
        }

        return n - totalMatches;
    }

    // Standard Union-Find Helper Class
    class UnionFind {
        int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) parent[rootI] = rootJ;
        }
    }
}