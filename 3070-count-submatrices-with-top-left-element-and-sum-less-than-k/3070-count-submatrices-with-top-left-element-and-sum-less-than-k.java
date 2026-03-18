class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;

        int[] prefixSum = new int[n];
        int maxCol = n-1;

        for(int i=0; i<m; i++){
            int rowSum = 0;
            for(int j=0; j<=maxCol; j++){
                rowSum += grid[i][j];
                prefixSum[j] += rowSum;

                if(prefixSum[j] <= k) ans++;
                else{
                    maxCol = j-1;
                    break;
                }
            }
        }

        return ans;
    }
}