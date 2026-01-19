class Solution {
    private int rows;
    private int cols;
    private int threshold;
    private int[][] prefixSum;

    public int maxSideLength(int[][] mat, int threshold) {
        rows = mat.length;
        cols = mat[0].length;
        this.threshold = threshold;

        // Build prefix sum array (1-based indexing for convenience)
        prefixSum = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1]
                                - prefixSum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        // Binary search for maximum feasible side length
        int left = 1, right = Math.min(rows, cols), ans = 0;
        while (left <= right) {
            int mid = (left + right) >>> 1; // faster midpoint
            if (feasible(mid)) {
                ans = mid;       // mid is feasible, try larger
                left = mid + 1;
            } else {
                right = mid - 1; // mid infeasible, try smaller
            }
        }
        return ans;
    }

    // Check if any k x k submatrix has sum <= threshold
    private boolean feasible(int k) {
        for (int i = k; i <= rows; i++) {
            for (int j = k; j <= cols; j++) {
                int sum = prefixSum[i][j] - prefixSum[i - k][j]
                        - prefixSum[i][j - k] + prefixSum[i - k][j - k];
                if (sum <= threshold) return true; // early exit
            }
        }
        return false;
    }
}