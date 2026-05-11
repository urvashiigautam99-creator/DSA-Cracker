import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        
        for (int num : nums) {
            // We use a temporary list or stack to keep digits in correct order
            // or simply convert to string for ease during competitive programming.
            String s = Integer.toString(num);
            for (char c : s.toCharArray()) {
                resultList.add(c - '0');
            }
        }
        
        // Convert List<Integer> to int[]
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        
        return result;
    }
}