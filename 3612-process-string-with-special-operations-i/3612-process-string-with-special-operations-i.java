class Solution {
    public String processStr(String s) {
        StringBuilder result = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                // If the character is a lowercase English letter, append it
                result.append(c);
            } else if (c == '*') {
                // Remove the last character if the string is not empty
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else if (c == '#') {
                // Duplicate the current result string and append it to itself
                result.append(result.toString());
            } else if (c == '%') {
                // Reverse the current result string
                result.reverse();
            }
        }
        
        return result.toString();
    }
}