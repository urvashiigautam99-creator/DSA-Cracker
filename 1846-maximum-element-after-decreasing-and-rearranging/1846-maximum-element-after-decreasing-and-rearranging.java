import java.util.Arrays;

public class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        // Step 1: Sort the array to handle elements greedily in increasing order
        Arrays.sort(arr);
        
        // Step 2: The first element must be 1
        arr[0] = 1;
        
        // Step 3: Iterate through the array and ensure the adjacent difference rule
        for (int i = 1; i < arr.length; i++) {
            // The current element cannot exceed the previous element + 1
            if (arr[i] > arr[i - 1] + 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        
        // Step 4: The last element will be the maximum possible value
        return arr[arr.length - 1];
    }
}