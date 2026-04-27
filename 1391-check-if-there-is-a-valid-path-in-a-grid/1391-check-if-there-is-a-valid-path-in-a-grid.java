import java.util.*;

class Solution {
    // Directions: 0: Top, 1: Bottom, 2: Left, 3: Right
    // streetConnects[i] defines which two directions street type 'i' connects
    private final int[][][] streetConnects = {
        {}, // Placeholder for index 0
        {{0, -1}, {0, 1}},  // 1: Left, Right
        {{-1, 0}, {1, 0}},  // 2: Upper, Lower
        {{0, -1}, {1, 0}},  // 3: Left, Lower
        {{0, 1}, {1, 0}},   // 4: Right, Lower
        {{0, -1}, {-1, 0}}, // 5: Left, Upper
        {{0, 1}, {-1, 0}}   // 6: Right, Upper
    };

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            if (r == m - 1 && c == n - 1) return true;

            int streetType = grid[r][c];
            for (int[] dir : streetConnects[streetType]) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Check boundaries and if already visited
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    // Check if the neighbor can connect back to the current cell
                    if (canConnectBack(nr, nc, r, c, grid)) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        return false;
    }

    private boolean canConnectBack(int nr, int nc, int r, int c, int[][] grid) {
        int neighborType = grid[nr][nc];
        for (int[] dir : streetConnects[neighborType]) {
            if (nr + dir[0] == r && nc + dir[1] == c) {
                return true;
            }
        }
        return false;
    }
}