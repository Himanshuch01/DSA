public class Day55 {
    class Solution {
        /**
         * Counts the number of special positions in a binary matrix.
         * A position (i, j) is special if mat[i][j] == 1 and all other elements
         * in row i and column j are 0.
         *
         * @param mat The input binary matrix
         * @return The count of special positions
         */
        public int numSpecial(int[][] mat) {
            // Get matrix dimensions
            int rowCount = mat.length;
            int colCount = mat[0].length;

            // Arrays to store the sum of 1s in each row and column
            int[] rowSums = new int[rowCount];
            int[] colSums = new int[colCount];

            // First pass: Calculate the sum of elements in each row and column
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    rowSums[row] += mat[row][col];
                    colSums[col] += mat[row][col];
                }
            }

            // Second pass: Count special positions
            int specialCount = 0;
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    // A position is special if:
                    // 1. The element at this position is 1
                    // 2. The sum of its row is 1 (only this element is 1)
                    // 3. The sum of its column is 1 (only this element is 1)
                    if (mat[row][col] == 1 && rowSums[row] == 1 && colSums[col] == 1) {
                        specialCount++;
                    }
                }
            }

            return specialCount;
        }
    }

}
