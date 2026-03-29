class Solution {
    public boolean canBeEqual(String s1, String s2) {
        // Convert to char array for easier handling
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        
        // Check even indices (0 and 2)
        // They are equal if they match directly OR if swapping c1[0] and c1[2] matches s2
        boolean evenMatch = (c1[0] == c2[0] && c1[2] == c2[2]) || 
                            (c1[0] == c2[2] && c1[2] == c2[0]);
        
        // Check odd indices (1 and 3)
        // They are equal if they match directly OR if swapping c1[1] and c1[3] matches s2
        boolean oddMatch = (c1[1] == c2[1] && c1[3] == c2[3]) || 
                           (c1[1] == c2[3] && c1[3] == c2[1]);
        
        return evenMatch && oddMatch;
    }
}