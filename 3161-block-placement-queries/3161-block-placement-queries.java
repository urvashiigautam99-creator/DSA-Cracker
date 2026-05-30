import java.util.*;

class Solution {
    class MaxFenwickTree {
        int[] tree;
        int size;

        public MaxFenwickTree(int n) {
            this.size = n;
            this.tree = new int[n + 1];
        }

        public void update(int i, int val) {
            while (i <= size) {
                tree[i] = Math.max(tree[i], val);
                i += i & (-i);
            }
        }

        public int query(int i) {
            int mx = 0;
            while (i > 0) {
                mx = Math.max(mx, tree[i]);
                i -= i & (-i);
            }
            return mx;
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        // Hard limit based on maximum constraints to prevent Out of Bounds
        int n = 50005; 
        
        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(n);

        // Step 1: Gather all obstacles
        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        // Step 2: Build initial max gaps
        MaxFenwickTree tree = new MaxFenwickTree(n);
        Integer prev = null;
        for (int curr : obstacles) {
            if (prev != null) {
                tree.update(curr, curr - prev);
            }
            prev = curr;
        }

        List<Boolean> ans = new ArrayList<>();

        // Step 3: Process backwards
        for (int i = queries.length - 1; i >= 0; i--) {
            int type = queries[i][0];
            
            if (type == 1) {
                int x = queries[i][1];
                
                // Using wrapper Integer to check for safety, though sentinels prevent nulls
                Integer prevObs = obstacles.lower(x);
                Integer nextObs = obstacles.higher(x);
                
                if (prevObs != null && nextObs != null) {
                    tree.update(nextObs, nextObs - prevObs);
                }
                obstacles.remove(x);
                
            } else {
                int x = queries[i][1];
                int sz = queries[i][2];
                
                int prevObs = obstacles.floor(x);
                int maxGap = Math.max(tree.query(prevObs), x - prevObs);
                
                ans.add(maxGap >= sz);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}