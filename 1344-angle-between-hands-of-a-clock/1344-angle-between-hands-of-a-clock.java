class Solution {
    public double angleClock(int hour, int minutes) {
        // 1. Calculate the position of the minute hand in degrees
        double minuteAngle = minutes * 6.0;
        
        // 2. Calculate the position of the hour hand in degrees
        // (hour % 12) handles the 12 o'clock reset to 0 degrees
        double hourAngle = (hour % 12) * 30.0 + minutes * 0.5;
        
        // 3. Find the absolute difference between the two positions
        double diff = Math.abs(hourAngle - minuteAngle);
        
        // 4. Return the smaller of the two possible angles
        return Math.min(diff, 360.0 - diff);
    }
}