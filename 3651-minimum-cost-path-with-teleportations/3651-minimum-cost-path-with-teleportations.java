class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        for(int[] r : dp){
            Arrays.fill(r, Integer.MAX_VALUE/2);
        }

        dp[0][0] = 0;
        helper(dp, grid, m, n);

        for(int i=0; i<k; i++){
            int maxVal = 0;
            for(int[] r : grid){
                for(int val : r) maxVal = Math.max(maxVal, val);
            }

            int[] minCost = new int[maxVal+2];
            Arrays.fill(minCost, Integer.MAX_VALUE/2);

            for(int r=0; r<m; r++){
                for(int c=0; c<n; c++){
                    int val = grid[r][c];
                    minCost[val] = Math.min(minCost[val], dp[r][c]);
                }
            }

            for(int v=maxVal; v>=0; v--){
                minCost[v] = Math.min(minCost[v], minCost[v+1]);
            }

            for(int r=0; r<m; r++){
                for(int c=0; c<n; c++){
                    int val = grid[r][c];
                    int teleportCost = minCost[val];
                    dp[r][c] = Math.min(dp[r][c], teleportCost);
                }
            }
            helper(dp, grid, m, n);
        }

        return dp[m-1][n-1];
    }

    public void helper(int[][] dp, int[][] grid, int m, int n){
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i > 0){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+grid[i][j]);
                }
                if(j > 0){
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+grid[i][j]);
                }
            }
        }
    }
}