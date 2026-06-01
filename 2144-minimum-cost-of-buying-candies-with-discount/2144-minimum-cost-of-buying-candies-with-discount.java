import java.util.Arrays;

public class Solution {
    public int minimumCost(int[] cost) {
        // Step 1: Sort the array in ascending order
        Arrays.sort(cost);
        
        int totalCost = 0;
        int count = 0;
        
        // Step 2: Iterate backwards from the most expensive candy
        for (int i = cost.length - 1; i >= 0; i--) {
            count++;
            // Buy the first two candies in the group of three
            if (count % 3 != 0) {
                totalCost += cost[i];
            }
            // The third candy (when count % 3 == 0) is skipped because it's free
        }
        
        return totalCost;
    }
}