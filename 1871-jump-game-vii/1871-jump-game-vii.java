import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) == '1') return false;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int farthest = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            int start = Math.max(curr + minJump, farthest + 1);
            int end = Math.min(curr + maxJump, n - 1);

            for (int j = start; j <= end; j++) {
                if (s.charAt(j) == '0') {
                    if (j == n - 1) return true;
                    queue.offer(j);
                }
            }
            farthest = Math.max(farthest, curr + maxJump);
        }
        return false;
    }
}