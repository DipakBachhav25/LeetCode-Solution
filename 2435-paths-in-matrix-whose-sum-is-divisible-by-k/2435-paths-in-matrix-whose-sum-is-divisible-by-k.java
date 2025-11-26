class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numberOfPaths(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][][] dp = new int[rows][cols][k];
        dp[0][0][grid[0][0] % k] = 1;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                for (int rem = 0; rem < k; rem++) {
                    if (dp[r][c][rem] == 0) continue;

                    int newRem;
                    if (r + 1 < rows) {
                        newRem = (rem + grid[r + 1][c]) % k;
                        dp[r + 1][c][newRem] = (dp[r + 1][c][newRem] + dp[r][c][rem]) % MOD;
                    }
                    if (c + 1 < cols) {
                        newRem = (rem + grid[r][c + 1]) % k;
                        dp[r][c + 1][newRem] = (dp[r][c + 1][newRem] + dp[r][c][rem]) % MOD;
                    }
                }
            }
        }

        return dp[rows - 1][cols - 1][0];
    }
}