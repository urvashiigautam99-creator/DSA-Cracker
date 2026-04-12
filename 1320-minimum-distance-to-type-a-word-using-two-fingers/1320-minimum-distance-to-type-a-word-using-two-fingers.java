import java.util.Arrays;

class Solution {
    private int[][][] memo;

    public int minimumDistance(String word) {
        // memo[index][finger1_pos][finger2_pos]
        // Using 27 to represent the 'initial' state (no position yet)
        memo = new int[word.length()][27][27];
        for (int[][] layer : memo) {
            for (int[] row : layer) {
                Arrays.fill(row, -1);
            }
        }
        return solve(word, 0, 26, 26); // 26 represents a finger not yet placed
    }

    private int solve(String word, int idx, int f1, int f2) {
        if (idx == word.length()) {
            return 0;
        }
        if (memo[idx][f1][f2] != -1) {
            return memo[idx][f1][f2];
        }

        int currChar = word.charAt(idx) - 'A';

        // Option 1: Use Finger 1
        int cost1 = getDist(f1, currChar) + solve(word, idx + 1, currChar, f2);

        // Option 2: Use Finger 2
        int cost2 = getDist(f2, currChar) + solve(word, idx + 1, f1, currChar);

        return memo[idx][f1][f2] = Math.min(cost1, cost2);
    }

    private int getDist(int from, int to) {
        if (from == 26) return 0; // First move is free
        int r1 = from / 6, c1 = from % 6;
        int r2 = to / 6, c2 = to % 6;
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}