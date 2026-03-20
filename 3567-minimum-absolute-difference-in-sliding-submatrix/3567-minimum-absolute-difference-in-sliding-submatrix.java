class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m-k+1][n-k+1];
        int[] tmp = new int[k*k];

        for(int i=0; i<=m-k; i++){
            for(int j=0; j<=n-k; j++){
                int idx = 0;
                for(int r=i; r<i+k; r++){
                    for(int c=j; c<j+k; c++){
                        tmp[idx++] = grid[r][c];
                    }
                }

                Arrays.sort(tmp);
                int minDiff = Integer.MAX_VALUE;
                boolean hasDistinct = false;

                for (int x = 1; x < tmp.length; x++) {
                    if (tmp[x] != tmp[x - 1]) {
                        hasDistinct = true;
                        int diff = tmp[x] - tmp[x - 1];
                        if (diff < minDiff) {
                            minDiff = diff;
                        }
                    }
                }

                ans[i][j] = hasDistinct ? minDiff : 0;
            }
        }

        return ans;
    }
}