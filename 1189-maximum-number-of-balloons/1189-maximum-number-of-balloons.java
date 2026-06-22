import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] counts = new int[26];
        
        // Count frequencies using an array for O(1) space efficiency
        for (char c : text.toCharArray()) {
            counts[c - 'a']++;
        }
        
        // Find the minimum bottleneck
        int bCount = counts['b' - 'a'];
        int aCount = counts['a' - 'a'];
        int lCount = counts['l' - 'a'] / 2;
        int oCount = counts['o' - 'a'] / 2;
        int nCount = counts['n' - 'a'];
        
        return Math.min(bCount, Math.min(aCount, Math.min(lCount, Math.min(oCount, nCount))));
    }
}