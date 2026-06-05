import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    private List<Integer> digits = new ArrayList<>();
    
    // Reduced state space: [pos][sum][prv2][prv]
    private long[][][][] memo = new long[20][20][12][12];
    private int[][][][] visited = new int[20][20][12][12];
    private int runId = 0;

    public long totalWaviness(long num1, long num2) {
        return count(num2) - count(num1 - 1);
    }

    private long count(long x) {
        if (x <= 0) return 0;

        digits.clear();
        long temp = x;
        while (temp > 0) {
            digits.add((int) (temp % 10));
            temp /= 10;
        }
        Collections.reverse(digits);

        // Increment runId so we don't have to manually clear the arrays
        runId++;

        // 11 acts as a sentinel meaning "no digit placed yet"
        return solve(0, 0, 11, 11, false);
    }

    private long solve(int pos, int sum, int prv2, int prv, boolean small) {
        if (pos == digits.size()) {
            return sum;
        }

        // Only memoize states where we are free to place any digit (small == true)
        if (small && visited[pos][sum][prv2][prv] == runId) {
            return memo[pos][sum][prv2][prv];
        }

        int limit = small ? 9 : digits.get(pos);
        long ans = 0;

        for (int i = 0; i <= limit; i++) {
            boolean nextSmall = small || (i < digits.get(pos));
            
            if (prv == 11 && i == 0) {
                // Leading zero: do not update waviness, keep sentinels as 11
                ans += solve(pos + 1, sum, 11, 11, nextSmall);
            } else {
                int nextSum = sum;
                // If both previous digits exist, check for peak or valley
                if (prv2 != 11 && prv != 11) {
                    if ((prv2 < prv && prv > i) || (prv2 > prv && prv < i)) {
                        nextSum++;
                    }
                }
                ans += solve(pos + 1, nextSum, prv, i, nextSmall);
            }
        }

        // Cache the result if it's a reusable state
        if (small) {
            visited[pos][sum][prv2][prv] = runId;
            memo[pos][sum][prv2][prv] = ans;
        }

        return ans;
    }
}