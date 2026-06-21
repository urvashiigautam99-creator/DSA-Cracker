import java.util.Arrays;

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // Sort the costs so we can buy the cheapest ones first
        Arrays.sort(costs);
        int iceCreamCount = 0;
        
        for (int cost : costs) {
            // If we have enough coins, buy it
            if (coins >= cost) {
                coins -= cost;
                iceCreamCount++;
            } else {
                // Since it's sorted, if we can't afford this one, 
                // we can't afford any remaining ones either.
                break;
            }
        }
        
        return iceCreamCount;
    }
}