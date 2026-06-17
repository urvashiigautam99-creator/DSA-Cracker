class Solution {
    public char processStr(String s, long k) {
        long m = 0; // Tracks the simulated length of the string

        // Phase 1: Determine the total final length of the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                m = Math.max(0, m - 1);
            } else if (c == '#') {
                m <<= 1; // Duplication doubles the size (m * 2)
            } else if (c != '%') {
                m += 1;  // Normal lowercase letter
            }
            // For '%', the length remains unchanged
        }

        // Out of bounds safety check
        if (k >= m) {
            return '.';
        }

        // Phase 2: Reverse-trace the index 'k' from the end of the operations
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == '*') {
                m += 1; // Reversing a backspace means the prior virtual length was 1 larger
            } else if (c == '#') {
                m /= 2; // Reversing duplication cuts the virtual length in half
                if (k >= m) {
                    k -= m; // If k was in the duplicated half, map it back to the original half
                }
            } else if (c == '%') {
                k = m - 1 - k; // Reversing a string flip mirrors our target index k
            } else {
                m -= 1; // Reversing an appended letter reduces length by 1
                if (k == m) {
                    return c; // Found the character that originally landed at index k!
                }
            }
        }

        return '.';
    }
}