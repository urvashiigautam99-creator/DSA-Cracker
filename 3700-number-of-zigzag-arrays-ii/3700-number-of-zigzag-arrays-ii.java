public class Solution {
    private static final int MOD = 1_000_000_007;

    // Helper to multiply two matrices under modulo
    private long[][] multiply(long[][] A, long[][] B) {
        int len = A.length;
        long[][] C = new long[len][len];
        for (int i = 0; i < len; i++) {
            for (int k = 0; k < len; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < len; j++) {
                    if (B[k][j] == 0) continue;
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    // Helper for Fast Matrix Exponentiation: T^exp
    private long[][] power(long[][] base, long exp) {
        int len = base.length;
        long[][] res = new long[len][len];
        for (int i = 0; i < len; i++) {
            res[i][i] = 1; // Identity matrix
        }
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, base);
            }
            base = multiply(base, base);
            exp >>= 1;
        }
        return res;
    }

    // Helper to multiply a matrix by a vector
    private long[] multiply(long[][] A, long[] v) {
        int len = A.length;
        long[] res = new long[len];
        for (int i = 0; i < len; i++) {
            long cur = 0;
            for (int j = 0; j < len; j++) {
                if (A[i][j] == 0) continue;
                cur = (cur + A[i][j] * v[j]) % MOD;
            }
            res[i] = cur;
        }
        return res;
    }

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int states = 2 * m;
        long[][] T = new long[states][states];

        // Layout of the state vector:
        // Indices [0 ... m-1]     -> downState (Next comparison must be UP)
        // Indices [m ... 2m-1]    -> upState   (Next comparison must be DOWN)
        for (int x = 0; x < m; x++) {
            int downState = x;
            int upState = x + m;

            // Transition from UP(x) -> DOWN(y) where y > x
            for (int y = x + 1; y < m; y++) {
                T[y][upState] = 1;
            }

            // Transition from DOWN(x) -> UP(y) where y < x
            for (int y = 0; y < x; y++) {
                T[y + m][downState] = 1;
            }
        }

        // Initialize the base starting vector for an array of length 1
        long[] start = new long[states];
        for (int x = 0; x < m; x++) {
            start[x] = 1;     // Can transition UP next
            start[x + m] = 1; // Can transition DOWN next
        }

        // Raise transition matrix to the power of (n - 1)
        long[][] P = power(T, n - 1);
        long[] finalVec = multiply(P, start);

        // Sum up all valid final configurations
        long ans = 0;
        for (long val : finalVec) {
            ans = (ans + val) % MOD;
        }

        return (int) ans;
    }
}