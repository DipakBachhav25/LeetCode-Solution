class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Prefix sums
        int[][] row = new int[m + 1][n + 1];
        int[][] col = new int[m + 1][n + 1];
        int[][] diag = new int[m + 1][n + 1];
        int[][] anti = new int[m + 1][n + 2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i + 1][j + 1] = row[i + 1][j] + grid[i][j];
                col[i + 1][j + 1] = col[i][j + 1] + grid[i][j];
                diag[i + 1][j + 1] = diag[i][j] + grid[i][j];
                anti[i + 1][j] = anti[i][j + 1] + grid[i][j];
            }
        }

        int maxSize = Math.min(m, n);

        for (int k = maxSize; k >= 2; k--) {
            for (int i = 0; i + k <= m; i++) {
                for (int j = 0; j + k <= n; j++) {

                    int target = row[i + 1][j + k] - row[i + 1][j];

                    boolean valid = true;

                    // Rows
                    for (int r = i; r < i + k && valid; r++) {
                        int sum = row[r + 1][j + k] - row[r + 1][j];
                        if (sum != target) valid = false;
                    }

                    // Columns
                    for (int c = j; c < j + k && valid; c++) {
                        int sum = col[i + k][c + 1] - col[i][c + 1];
                        if (sum != target) valid = false;
                    }

                    // Main diagonal
                    int d1 = diag[i + k][j + k] - diag[i][j];
                    if (d1 != target) valid = false;

                    // Anti-diagonal
                    int d2 = anti[i + k][j] - anti[i][j + k];
                    if (d2 != target) valid = false;

                    if (valid) return k;
                }
            }
        }

        return 1;
    }
}