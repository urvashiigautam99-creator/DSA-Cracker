class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int countL = 0;
        int countR = 0;
        int countUnderscore = 0;

        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                countL++;
            } else if (c == 'R') {
                countR++;
            } else {
                countUnderscore++;
            }
        }

        // The furthest point is the absolute difference between L and R,
        // plus all the underscores used in the direction of the majority.
        return Math.abs(countL - countR) + countUnderscore;
    }
}