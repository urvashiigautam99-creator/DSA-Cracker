import java.util.Arrays;

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // Sort to handle the smallest asteroids first
        Arrays.sort(asteroids);
        
        // Use long to prevent integer overflow
        long currentMass = mass;
        
        for (int asteroid : asteroids) {
            if (currentMass < asteroid) {
                return false;
            }
            currentMass += asteroid;
        }
        
        return true;
    }
}