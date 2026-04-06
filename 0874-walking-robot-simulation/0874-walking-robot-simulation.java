import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions: 0: North, 1: East, 2: South, 3: West
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        // Use a HashSet for O(1) obstacle lookup
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obstacleSet.add(obs[0] + "," + obs[1]);
        }
        
        int x = 0, y = 0, d = 0;
        int maxDistSq = 0;
        
        for (int cmd : commands) {
            if (cmd == -1) {
                // Turn right
                d = (d + 1) % 4;
            } else if (cmd == -2) {
                // Turn left
                d = (d + 3) % 4;
            } else {
                // Move forward k units
                for (int i = 0; i < cmd; i++) {
                    int nextX = x + dx[d];
                    int nextY = y + dy[d];
                    
                    // Check if the next step is an obstacle
                    if (!obstacleSet.contains(nextX + "," + nextY)) {
                        x = nextX;
                        y = nextY;
                        maxDistSq = Math.max(maxDistSq, x * x + y * y);
                    } else {
                        // Stop moving in this direction if blocked
                        break;
                    }
                }
            }
        }
        
        return maxDistSq;
    }
}