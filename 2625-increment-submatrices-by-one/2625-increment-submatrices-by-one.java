class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] prefixSum = new int[n+1][n+1];
        for(int[] query : queries){
            int row1 = query[0], row2 = query[2];
            int col1 = query[1], col2 = query[3];
            prefixSum[row1][col1]++;
            prefixSum[row1][col2+1]--;
            prefixSum[row2+1][col1]--;
            prefixSum[row2+1][col2+1]++;
        }

        for(int i=0; i<=n; i++){
            for(int j=1; j<=n; j++){
                prefixSum[i][j] += prefixSum[i][j-1];
            }
        }

        for(int j=0; j<=n; j++){
            for(int i=1; i<=n; i++){
                prefixSum[i][j] += prefixSum[i-1][j];
            }
        }

        int[][] ans = new int[n][n];
        for(int i=0; i<n; i++){
            System.arraycopy(prefixSum[i], 0, ans[i], 0, n);
        }

        return ans;
    }
}