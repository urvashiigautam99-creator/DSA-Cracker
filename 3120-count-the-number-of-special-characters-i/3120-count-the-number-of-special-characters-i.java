import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numberOfSpecialChars(String word) {
        // Using a boolean array for ASCII optimization
        boolean[] present = new boolean[128];
        for (char c : word.toCharArray()) {
            present[c] = true;
        }
        
        int count = 0;
        // Check matching lower and upper pairs
        for (int i = 0; i < 26; i++) {
            if (present['a' + i] && present['A' + i]) {
                count++;
            }
        }
        
        return count;
    }
}