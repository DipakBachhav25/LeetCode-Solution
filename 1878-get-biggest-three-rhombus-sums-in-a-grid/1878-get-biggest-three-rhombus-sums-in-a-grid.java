class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        TreeSet<Integer> ts = new TreeSet<>(Collections.reverseOrder());

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                ts.add(grid[i][j]);

                for (int k = 1; i + 2 * k < m && j - k >= 0 && j + k < n; k++){

                    int sum = 0;
                    // Top to Right
                    for (int s = 0; s < k; s++) sum += grid[i + s][j + s];
                    // Right to Bottom
                    for (int s = 0; s < k; s++) sum += grid[i + k + s][j + k - s];
                    // Bottom to Left
                    for (int s = 0; s < k; s++) sum += grid[i + 2 * k - s][j - s];
                    // Left to Top
                    for (int s = 0; s < k; s++) sum += grid[i + k - s][j - k + s];
                    ts.add(sum);
                }
            }
        }

        int count = Math.min(ts.size(), 3);
        int[] ans = new int[count];
        Iterator<Integer> it = ts.iterator();
        for (int i = 0; i < count; i++) {
            ans[i] = it.next();
        }
        
        return ans;
    }
}