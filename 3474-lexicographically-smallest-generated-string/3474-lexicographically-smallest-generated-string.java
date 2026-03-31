import java.util.*;

class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int len = n + m - 1;
        
        char[] res = new char[len];
        boolean[] fixed = new boolean[len];
        Arrays.fill(res, '?');

        // Step 1: Fill all 'T' constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (res[i + j] != '?' && res[i + j] != str2.charAt(j)) {
                        return ""; // Conflict between 'T' constraints
                    }
                    res[i + j] = str2.charAt(j);
                    fixed[i + j] = true;
                }
            }
        }

        // Step 2: Fill remaining slots with 'a' for lexicographical smallest
        for (int i = 0; i < len; i++) {
            if (res[i] == '?') {
                res[i] = 'a';
            }
        }

        // Step 3: Handle 'F' constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                // Check if the current substring matches str2
                if (isMatch(res, i, str2)) {
                    // Try to change the rightmost non-fixed character to 'b'
                    boolean changed = false;
                    for (int j = m - 1; j >= 0; j--) {
                        if (!fixed[i + j]) {
                            char original = res[i + j];
                            // Try the next character after 'a' (usually 'b')
                            // Or if str2 has 'a' at this position, try 'b'
                            for (char c = 'b'; c <= 'z'; c++) {
                                res[i + j] = c;
                                // After changing, we must ensure we didn't accidentally
                                // break an 'F' we already satisfied or create a new 'T'
                                // But since we only care about "not equaling str2", 
                                // simply changing to something else works.
                                if (res[i + j] != str2.charAt(j)) {
                                    changed = true;
                                    break;
                                }
                            }
                        }
                        if (changed) break;
                    }
                    
                    if (!changed) return ""; // No flexible characters to break the 'F' match
                }
            }
        }

        // Final Verification: Ensure no 'F' was violated by later changes
        for (int i = 0; i < n; i++) {
            boolean match = isMatch(res, i, str2);
            if (str1.charAt(i) == 'T' && !match) return "";
            if (str1.charAt(i) == 'F' && match) return "";
        }

        return new String(res);
    }

    private boolean isMatch(char[] res, int start, String str2) {
        for (int j = 0; j < str2.length(); j++) {
            if (res[start + j] != str2.charAt(j)) return false;
        }
        return true;
    }
}