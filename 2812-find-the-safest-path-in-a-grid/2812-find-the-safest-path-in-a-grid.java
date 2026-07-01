import java.util.*;

public class Solution {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // Step 1: Precompute the minimum distance to any thief for each cell
        int[][] minDist = computeMinDistanceToThieves(grid, n);
        
        // Step 2: Use Dijkstra-like approach (Max-Heap) to find the maximum safeness path
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]); // Sort by safeness factor descending
        boolean[][] visited = new boolean[n][n];
        
        // int[] format: {safeness_factor, row, col}
        maxHeap.offer(new int[]{minDist[0][0], 0, 0});
        visited[0][0] = true;
        
        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int currentSafeness = curr[0];
            int r = curr[1];
            int c = curr[2];
            
            // If we reached the destination, return the answer
            if (r == n - 1 && c == n - 1) {
                return currentSafeness;
            }
            
            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (isValid(nr, nc, n) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // The safeness of the path to the neighbor is the minimum of 
                    // the current path's safeness and the neighbor's individual safeness
                    int nextSafeness = Math.min(currentSafeness, minDist[nr][nc]);
                    maxHeap.offer(new int[]{nextSafeness, nr, nc});
                }
            }
        }
        
        return 0;
    }
    
    private int[][] computeMinDistanceToThieves(List<List<Integer>> grid, int n) {
        int[][] minDist = new int[n][n];
        for (int[] row : minDist) {
            Arrays.fill(row, -1);
        }
        
        Queue<int[]> queue = new LinkedList<>();
        
        // Add all thieves to the queue to kick off multi-source BFS
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    queue.offer(new int[]{r, c});
                    minDist[r][c] = 0;
                }
            }
        }
        
        // Standard BFS layer by layer
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            
            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (isValid(nr, nc, n) && minDist[nr][nc] == -1) {
                    minDist[nr][nc] = minDist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        
        return minDist;
    }
    
    private boolean isValid(int r, int c, int n) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}