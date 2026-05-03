class Solution {
    public boolean rotateString(String s, String goal) {
        // Step 1: Check if the lengths are the same. 
        // If not, s can never become goal via rotation.
        if (s.length() != goal.length()) {
            return false;
        }
        
        // Step 2: Concatenate s with itself.
        String doubled = s + s;
        
        // Step 3: Check if goal is a substring of the doubled string.
        return doubled.contains(goal);
    }
}