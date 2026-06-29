class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        
        // Loop through each pattern in the array
        for (String pattern : patterns) {
            // Check if 'word' contains the current pattern
            if (word.contains(pattern)) {
                count++;
            }
        }
        
        return count;
    }
}