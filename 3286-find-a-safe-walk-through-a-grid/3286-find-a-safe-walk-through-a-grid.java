import java.util.*;

public class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // dist[i][j] stores the minimum health points lost to reach cell (i, j)
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        // Initialize starting position
        dist[0][0] = grid.get(0).get(0);
        
        // Deque for 0-1 BFS
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{0, 0});
        
        // Direction vectors for moving up, down, left, right
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            
            // Early exit if we reached the destination
            if (r == m - 1 && c == n - 1) {
                break;
            }
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // Check grid boundaries
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int weight = grid.get(nr).get(nc);
                    
                    // Relaxation step: if a shorter/healthier path to (nr, nc) is found
                    if (dist[r][c] + weight < dist[nr][nc]) {
                        dist[nr][nc] = dist[r][c] + weight;
                        
                        // 0-1 BFS optimization
                        if (weight == 0) {
                            deque.offerFirst(new int[]{nr, nc});
                        } else {
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        
        // We must reach the destination with health >= 1, meaning health lost < total health
        return dist[m - 1][n - 1] < health;
    }
}