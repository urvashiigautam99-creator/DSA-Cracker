import java.util.PriorityQueue;

class SparseTableRMQ {
    int n;
    int maxLog;
    int[][] fMax;
    int[][] fMin;
    int[] lg;

    public SparseTableRMQ(int[] data) {
        this.n = data.length;
        this.maxLog = 32 - Integer.numberOfLeadingZeros(n) + 1;
        this.fMax = new int[n][maxLog];
        this.fMin = new int[n][maxLog];
        this.lg = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            this.lg[i] = this.lg[i >> 1] + 1;
        }

        for (int i = 0; i < n; i++) {
            this.fMax[i][0] = data[i];
            this.fMin[i][0] = data[i];
        }

        for (int j = 1; j < maxLog; j++) {
            for (int i = 0; i <= n - (1 << j); i++) {
                this.fMax[i][j] = Math.max(this.fMax[i][j - 1], this.fMax[i + (1 << (j - 1))][j - 1]);
                this.fMin[i][j] = Math.min(this.fMin[i][j - 1], this.fMin[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    public int queryMax(int l, int r) {
        int k = lg[r - l + 1];
        return Math.max(fMax[l][k], fMax[r - (1 << k) + 1][k]);
    }

    public int queryMin(int l, int r) {
        int k = lg[r - l + 1];
        return Math.min(fMin[l][k], fMin[r - (1 << k) + 1][k]);
    }
}

public class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        SparseTableRMQ st = new SparseTableRMQ(nums);
        
        // Max-Heap storing elements as: {subarray_value, left_idx, right_idx}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));

        // Initialize heap with the largest possible right bound (n - 1) for each left bound l
        for (int l = 0; l < n; l++) {
            long val = (long) st.queryMax(l, n - 1) - st.queryMin(l, n - 1);
            pq.offer(new long[]{val, l, n - 1});
        }

        long totalValue = 0;

        // Process top k largest values greedily
        for (int i = 0; i < k; i++) {
            if (pq.isEmpty()) break;
            
            long[] current = pq.poll();
            long val = current[0];
            int l = (int) current[1];
            int r = (int) current[2];

            totalValue += val;

            // If the subarray has a size greater than 1, push its next smaller subsegment [l, r - 1]
            if (r > l) {
                long nextVal = (long) st.queryMax(l, r - 1) - st.queryMin(l, r - 1);
                pq.offer(new long[]{nextVal, l, r - 1});
            }
        }

        return totalValue;
    }
}