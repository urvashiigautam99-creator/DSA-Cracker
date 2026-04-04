class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        int cols = n / rows;
        StringBuilder res = new StringBuilder();

        // Each diagonal starts at row 0, column 'c'
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                int currentCol = c + r;
                // If the diagonal goes out of bounds horizontally, stop this diagonal
                if (currentCol >= cols) break;
                
                // Calculate the 1D index from 2D coordinates (r, currentCol)
                int index = r * cols + currentCol;
                res.append(encodedText.charAt(index));
            }
        }

        // Trim only trailing spaces
        int i = res.length() - 1;
        while (i >= 0 && res.charAt(i) == ' ') {
            i--;
        }
        
        return res.substring(0, i + 1);
    }
}