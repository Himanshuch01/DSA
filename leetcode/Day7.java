// 1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold

class Solution {
    private int rows;
    private int cols;
    private int threshold;
    private int[][] prefixSum;

    public int maxSideLength(int[][] mat, int threshold) {
        rows = mat.length;
        cols = mat[0].length;
        this.threshold = threshold;

        prefixSum = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1]
                                - prefixSum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int left = 1;
        int right = Math.min(rows, cols) + 1;
        int firstTrueIndex = Math.min(rows, cols) + 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (!feasible(mid)) {
                firstTrueIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return firstTrueIndex - 1;
    }

    private boolean feasible(int k) {
        for (int i = 0; i <= rows - k; i++) {
            for (int j = 0; j <= cols - k; j++) {
                int sum = prefixSum[i + k][j + k] - prefixSum[i][j + k]
                        - prefixSum[i + k][j] + prefixSum[i][j];
                if (sum <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
}