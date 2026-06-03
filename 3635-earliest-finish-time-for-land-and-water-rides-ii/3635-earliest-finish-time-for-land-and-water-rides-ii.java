public class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        // Evaluate both sequence possibilities and return the minimum
        int option1 = evaluateSequence(landStartTime, landDuration, waterStartTime, waterDuration); // Land -> Water
        int option2 = evaluateSequence(waterStartTime, waterDuration, landStartTime, landDuration); // Water -> Land
        
        return Math.min(option1, option2);
    }
    
    private int evaluateSequence(int[] firstStart, int[] firstDuration, int[] secondStart, int[] secondDuration) {
        // Step 1: Find the absolute earliest time any ride in the first category can finish
        int minFirstEnd = Integer.MAX_VALUE;
        for (int i = 0; i < firstStart.length; i++) {
            minFirstEnd = Math.min(minFirstEnd, firstStart[i] + firstDuration[i]);
        }
        
        // Step 2: Test all rides in the second category assuming we start right after minFirstEnd
        int minTotalTime = Integer.MAX_VALUE;
        for (int j = 0; j < secondStart.length; j++) {
            int actualStartOfSecond = Math.max(minFirstEnd, secondStart[j]);
            int currentFinishTime = actualStartOfSecond + secondDuration[j];
            minTotalTime = Math.min(minTotalTime, currentFinishTime);
        }
        
        return minTotalTime;
    }
}