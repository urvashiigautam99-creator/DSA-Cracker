class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];
        
        // Since elements are from 1 to n, size n + 1 prevents out of bounds
        int[] frequency = new int[n + 1];
        int commonCount = 0;
        
        for (int i = 0; i < n; i++) {
            // Process element from array A
            frequency[A[i]]++;
            if (frequency[A[i]] == 2) {
                commonCount++;
            }
            
            // Process element from array B
            frequency[B[i]]++;
            if (frequency[B[i]] == 2) {
                commonCount++;
            }
            
            // The result at the current prefix index is our running count
            result[i] = commonCount;
        }
        
        return result;
    }
}