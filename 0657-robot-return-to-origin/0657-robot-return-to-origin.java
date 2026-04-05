class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;

        // Iterate through each character in the moves string
        for (char move : moves.toCharArray()) {
            if (move == 'U') {
                y++;
            } else if (move == 'D') {
                y--;
            } else if (move == 'L') {
                x--;
            } else if (move == 'R') {
                x++;
            }
        }

        // Return true if the robot is back at (0,0)
        return x == 0 && y == 0;
    }
}