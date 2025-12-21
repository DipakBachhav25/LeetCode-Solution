class Solution {
    public int minDeletionSize(String[] strs) {
        if (strs == null || strs.length <= 1) return 0;

        int n = strs.length;
        int m = strs[0].length();
        int deletedColumns = 0;

        boolean[] sorted = new boolean[n - 1];
        int sortedCount = 0;

        columnLoop:
        for (int col = 0; col < m; col++) {
            for (int row = 0; row < n - 1; row++) {
                if (!sorted[row] && strs[row].charAt(col) > strs[row + 1].charAt(col)) {
                    deletedColumns++;
                    continue columnLoop;
                }
            }

            for (int row = 0; row < n - 1; row++) {
                if (!sorted[row] && strs[row].charAt(col) < strs[row + 1].charAt(col)) {
                    sorted[row] = true;
                    sortedCount++;
                }
            }

            if (sortedCount == n - 1) break;
        }

        return deletedColumns;

    }
}