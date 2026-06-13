class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            int totalWeight = 0;
            for (char ch : word.toCharArray()) {
                totalWeight += weights[ch - 'a'];
            }
            
            int remainder = totalWeight % 26;
            result.append((char)('z' - remainder));
        }
        
        return result.toString();
    }
}