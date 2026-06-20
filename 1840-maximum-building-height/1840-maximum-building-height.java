import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int k = restrictions.length;
        
        // Create an extended array to accommodate boundary conditions [1, 0] and [n, n-1]
        int[][] A = new int[k + 2][2];
        System.arraycopy(restrictions, 0, A, 0, k);
        A[k] = new int[] {1, 0};
        A[k + 1] = new int[] {n, n - 1};
        
        // Sort restrictions by building index
        Arrays.sort(A, Comparator.comparingInt(a -> a[0]));
        
        // 1. Left-to-Right Pass: Propagate constraints from left to right
        for (int i = 1; i < A.length; ++i) {
            int dist = A[i][0] - A[i - 1][0];
            A[i][1] = Math.min(A[i][1], A[i - 1][1] + dist);
        }
        
        // 2. Right-to-Left Pass: Propagate constraints from right to left
        for (int i = A.length - 2; i >= 0; --i) {
            int dist = A[i + 1][0] - A[i][0];
            A[i][1] = Math.min(A[i][1], A[i + 1][1] + dist);
        }
        
        // 3. Find the global maximum peak between any two restricted positions
        int maxGlobalHeight = 0;
        for (int i = 1; i < A.length; ++i) {
            int l = A[i - 1][0], r = A[i][0];
            int hL = A[i - 1][1], hR = A[i][1];
            
            // Formula to find the apex between two restrictions
            int peak = (hL + hR + (r - l)) / 2;
            maxGlobalHeight = Math.max(maxGlobalHeight, peak);
        }
        
        return maxGlobalHeight;
    }
}