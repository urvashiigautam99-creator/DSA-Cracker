class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] s = new char[n];
        char curChar = 'a';

        // 1. Greedy Construction
        for (int i = 0; i < n; i++) {
            if (s[i] != 0) continue; // Already assigned
            if (curChar > 'z') return ""; // Ran out of alphabet characters
            
            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) {
                    s[j] = curChar;
                }
            }
            curChar++;
        }

        // 2. Validation
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expectedLCP = 0;
                if (s[i] == s[j]) {
                    if (i == n - 1 || j == n - 1) {
                        expectedLCP = 1;
                    } else {
                        expectedLCP = 1 + lcp[i + 1][j + 1];
                    }
                }
                
                if (lcp[i][j] != expectedLCP) {
                    return "";
                }
            }
        }

        return new String(s);
    }
}