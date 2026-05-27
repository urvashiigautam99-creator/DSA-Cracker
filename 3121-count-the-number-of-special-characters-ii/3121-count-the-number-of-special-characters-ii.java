import java.util.*;

class Solution {
    public int numberOfSpecialChars(String word) {
        int[] lastLower = new int[26];
        int[] firstUpper = new int[26];
        boolean[] invalidated = new boolean[26];
        
        // Initialize arrays with -1 to signify "not found"
        Arrays.fill(lastLower, -1);
        Arrays.fill(firstUpper, -1);
        
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            
            if (Character.isLowerCase(ch)) {
                int idx = ch - 'a';
                lastLower[idx] = i;
                // If uppercase already exists before this lowercase instance
                if (firstUpper[idx] != -1) {
                    invalidated[idx] = true;
                }
            } else {
                int idx = ch - 'A';
                // Only record the first occurrence
                if (firstUpper[idx] == -1) {
                    firstUpper[idx] = i;
                }
            }
        }
        
        int specialCount = 0;
        for (int i = 0; i < 26; i++) {
            // Must have both characters, not be invalid, and lower must finish before upper starts
            if (!invalidated[i] && lastLower[i] != -1 && firstUpper[i] != -1 && lastLower[i] < firstUpper[i]) {
                specialCount++;
            }
        }
        
        return specialCount;
    }
}