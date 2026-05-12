import java.util.Arrays;

class Solution {
    public int minimumEffort(int[][] tasks) {
        // Sort tasks based on (minimum - actual) in descending order
        // We use (b[1] - b[0]) - (a[1] - a[0]) for descending sort
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));

        int totalNeeded = 0;
        int currentEnergy = 0;

        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];

            // If we don't have enough energy to start this task
            if (currentEnergy < minimum) {
                // Increase the initial pool by the exact deficit
                totalNeeded += (minimum - currentEnergy);
                // After adding, we now have exactly the minimum required
                currentEnergy = minimum;
            }

            // Perform the task and subtract the actual cost
            currentEnergy -= actual;
        }

        return totalNeeded;
    }
}