import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        
        for (String q : queries) {
            for (String d : dictionary) {
                if (canMatch(q, d)) {
                    result.add(q);
                    break; // Found a match within 2 edits, move to next query
                }
            }
        }
        
        return result;
    }
    
    private boolean canMatch(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
            // Optimization: if diff > 2, this dictionary word is invalid
            if (diff > 2) {
                return false;
            }
        }
        return true;
    }
}