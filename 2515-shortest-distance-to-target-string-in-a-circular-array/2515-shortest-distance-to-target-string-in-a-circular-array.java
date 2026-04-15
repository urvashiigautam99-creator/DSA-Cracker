class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                // Calculate direct distance
                int directDist = Math.abs(i - startIndex);
                
                // Calculate wrap-around distance
                int wrapDist = n - directDist;
                
                // Take the minimum of the two directions
                int currentMin = Math.min(directDist, wrapDist);
                
                // Update the overall minimum distance found so far
                minDistance = Math.min(minDistance, currentMin);
            }
        }

        // If minDistance was never updated, the target wasn't found
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}