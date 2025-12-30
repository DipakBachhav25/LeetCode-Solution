public class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        if (grid == null || grid.length < 3 || grid[0].length < 3) return 0;
        int rows = grid.length, cols = grid[0].length, count = 0;

        for (int r = 0; r <= rows - 3; r++) {
            for (int c = 0; c <= cols - 3; c++) {
                if (grid[r + 1][c + 1] != 5) continue;

                int a = grid[r][c], b = grid[r][c + 1], cc = grid[r][c + 2];
                int d = grid[r + 1][c], e = grid[r + 1][c + 1], f = grid[r + 1][c + 2];
                int g = grid[r + 2][c], h = grid[r + 2][c + 1], i = grid[r + 2][c + 2];

                int mask = 0;
                int[] vals = {a, b, cc, d, e, f, g, h, i};
                boolean valid = true;
                for (int v : vals) {
                    if (v < 1 || v > 9) { valid = false; break; }
                    int bit = 1 << (v - 1);
                    if ((mask & bit) != 0) { valid = false; break; }
                    mask |= bit;
                }
                if (!valid) continue;
                if (a + b + cc != 15) continue;
                if (d + e + f != 15) continue;
                if (g + h + i != 15) continue;
                if (a + d + g != 15) continue;
                if (b + e + h != 15) continue;
                if (cc + f + i != 15) continue;
                if (a + e + i != 15) continue;
                if (cc + e + g != 15) continue;

                count++;
            }
        }

        return count;
    }
}