import java.util.Arrays;

class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        
        // Arrays to store characters at even and odd positions
        // Since we only deal with lowercase English letters, 
        // a frequency array of size 26 is efficient.
        int[] evenS1 = new int[26];
        int[] oddS1 = new int[26];
        int[] evenS2 = new int[26];
        int[] oddS2 = new int[26];
        
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                evenS1[s1.charAt(i) - 'a']++;
                evenS2[s2.charAt(i) - 'a']++;
            } else {
                oddS1[s1.charAt(i) - 'a']++;
                oddS2[s2.charAt(i) - 'a']++;
            }
        }
        
        // Check if the character counts match for both even and odd buckets
        return Arrays.equals(evenS1, evenS2) && Arrays.equals(oddS1, oddS2);
    }
}