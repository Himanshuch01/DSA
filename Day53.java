import java.util.Arrays;

class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;

        int[] rightmostOnePosition = new int[n];
        Arrays.fill(rightmostOnePosition, -1);

        for (int row = 0; row < n; ++row) {
            for (int col = n - 1; col >= 0; --col) {
                if (grid[row][col] == 1) {
                    rightmostOnePosition[row] = col;
                    break;
                }
            }
        }

        int totalSwaps = 0;

        for (int targetRow = 0; targetRow < n; ++targetRow) {
            int foundRowIndex = -1;

            for (int candidateRow = targetRow; candidateRow < n; ++candidateRow) {
                if (rightmostOnePosition[candidateRow] <= targetRow) {
                    totalSwaps += candidateRow - targetRow;
                    foundRowIndex = candidateRow;
                    break;
                }
            }

            if (foundRowIndex == -1) {
                return -1;
            }

            for (int currentPos = foundRowIndex; currentPos > targetRow; --currentPos) {
                int temp = rightmostOnePosition[currentPos];
                rightmostOnePosition[currentPos] = rightmostOnePosition[currentPos - 1];
                rightmostOnePosition[currentPos - 1] = temp;
            }
        }

        return totalSwaps;
    }
}