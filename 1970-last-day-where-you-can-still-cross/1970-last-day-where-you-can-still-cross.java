class Solution {
    private int rows, cols;
    private int[][] cells;

    public int latestDayToCross(int row, int col, int[][] cells) {
        this.rows = row;
        this.cols = col;
        this.cells = cells;

        int left = 1, right = cells.length, ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canCross(mid)) {
                ans = mid;
                left = mid + 1; // search later days
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private boolean canCross(int dayCount) {
        int[][] grid = new int[rows][cols];
        for (int i = 0; i < dayCount; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }

        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];

        for (int c = 0; c < cols; c++) {
            if (grid[0][c] == 0) {
                queue.offer(new int[]{0, c});
                visited[0][c] = true;
            }
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int r = pos[0], c = pos[1];
            if (r == rows - 1) return true;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                    !visited[nr][nc] && grid[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}