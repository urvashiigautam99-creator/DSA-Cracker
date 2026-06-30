class Solution {
    public int numberOfSubstrings(String s) {
        // Array to store the last seen index of 'a', 'b', and 'c'
        // Initialized to -1 because we haven't seen them yet
        int[] lastSeen = {-1, -1, -1};
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Update the last seen position of the current character
            lastSeen[s.charAt(i) - 'a'] = i;
            
            // If we have seen all three characters at least once
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                // The number of valid substrings ending at index i
                int minIndex = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
                count += minIndex + 1;
            }
        }
        
        return count;
    }
}