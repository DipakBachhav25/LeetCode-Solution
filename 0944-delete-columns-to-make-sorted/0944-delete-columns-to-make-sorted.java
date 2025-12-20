class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;
        int cols = strs[0].length();
        // Precompute char grid to avoid repeated String.charAt calls
        char[][] grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            grid[r] = strs[r].toCharArray();
        }

        int count = 0;
        for (int c = 0; c < cols; c++) {
            for (int r = 1; r < rows; r++) {
                if (grid[r][c] < grid[r - 1][c]) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}