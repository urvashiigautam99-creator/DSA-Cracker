import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        // Map stores: Key = reverse(nums[i]), Value = index i
        Map<Integer, Integer> reverseMap = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;
        boolean found = false;

        for (int j = 0; j < nums.length; j++) {
            int currentVal = nums[j];

            // If the current value was the 'target' reverse of a previous number
            if (reverseMap.containsKey(currentVal)) {
                int i = reverseMap.get(currentVal);
                minDistance = Math.min(minDistance, j - i);
                found = true;
            }

            // Store the reverse of the current number for future elements to find
            // We use put() every time to keep the most recent index (minimizing distance)
            int target = reverseInt(currentVal);
            reverseMap.put(target, j);
        }

        return found ? minDistance : -1;
    }

    private int reverseInt(int n) {
        if (n == 0) return 0;
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + (n % 10);
            n /= 10;
        }
        return rev;
    }
}