class Solution {
    public int diagonalSum(int[][] mat) {
        int sum = 0;
        int matrixSize = mat.length;
      
        // Iterate through each row of the matrix
        for (int row = 0; row < matrixSize; ++row) {
            // Calculate the column index for the anti-diagonal element
            int antiDiagonalCol = matrixSize - row - 1;
          
            // Add the primary diagonal element at position [row][row]
            sum += mat[row][row];
          
            // Add the anti-diagonal element at position [row][antiDiagonalCol]
            // Skip if it's the same element (center of odd-sized matrix)
            if (row != antiDiagonalCol) {
                sum += mat[row][antiDiagonalCol];
            }
        }
      
        return sum;
    }
}
