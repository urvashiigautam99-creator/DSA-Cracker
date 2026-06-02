class Solution {
    private int calc(int[] start1, int[] dur1, int[] start2, int[] dur2) {
        int minEnd = Integer.MAX_VALUE;
        for (int i = 0; i < start1.length; i++) {
            minEnd = Math.min(minEnd, start1[i] + dur1[i]);
        }
        
        int minTotalTime = Integer.MAX_VALUE;
        for (int j = 0; j < start2.length; j++) {
            int currentFinish = Math.max(start2[j], minEnd) + dur2[j];
            minTotalTime = Math.min(minTotalTime, currentFinish);
        }
        
        return minTotalTime;
    }

    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int landFirst = calc(landStartTime, landDuration, waterStartTime, waterDuration);
        int waterFirst = calc(waterStartTime, waterDuration, landStartTime, landDuration);
        
        return Math.min(landFirst, waterFirst);
    }
}