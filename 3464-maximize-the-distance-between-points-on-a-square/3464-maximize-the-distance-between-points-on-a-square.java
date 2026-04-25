import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] d = new long[n];
        
        // Step 1: Map 2D points to 1D perimeter distance
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            if (y == 0) d[i] = x;
            else if (x == side) d[i] = side + y;
            else if (y == side) d[i] = 2L * side + (side - x);
            else d[i] = 3L * side + (side - y);
        }
        
        Arrays.sort(d);
        
        // Manhattan distance on a square boundary is 
        // min(perimeter_dist, 2*side - perimeter_dist) in some cases,
        // but it can't exceed 2 * side.
        int low = 0, high = 2 * side;
        int ans = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid, d, k, side)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int mid, long[] d, int k, int side) {
        int n = d.length;
        long perimeter = 4L * side;
        
        // We only need to check starting points within the first 'mid' distance
        // because if a solution exists, one of the points must fall in this range.
        for (int i = 0; i < n; i++) {
            if (d[i] > d[0] + mid) break;
            
            int count = 1;
            long last = d[i];
            for (int j = i + 1; j < n; j++) {
                if (d[j] - last >= mid) {
                    count++;
                    last = d[j];
                    if (count == k) break;
                }
            }
            
            // Circular check: Is the distance between the last point and the first point 
            // (across the boundary) also at least 'mid'?
            if (count == k && (perimeter - (last - d[i])) >= mid) {
                // Additional Manhattan constraint:
                // For a square, the Manhattan distance between any two points 
                // on the perimeter is at least their distance along the perimeter 
                // ONLY if we consider the 'unrolled' path correctly.
                return true;
            }
        }
        return false;
    }
}