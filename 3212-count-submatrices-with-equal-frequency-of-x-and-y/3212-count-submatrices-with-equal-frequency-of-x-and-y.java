class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] prefixX = new int[n];
        int[] prefixY = new int[n];
        int subMatCount = 0;

        for(int i=0; i<m; i++){
            int currRowX = 0;
            int currRowY = 0;
            for(int j=0; j<n; j++){

                if(grid[i][j] == 'X') currRowX++;
                else if(grid[i][j] == 'Y') currRowY++;

                prefixX[j] += currRowX;
                prefixY[j] += currRowY;

                if(prefixX[j] == prefixY[j] && prefixX[j] > 0) subMatCount++;
            }
        }

        return subMatCount;
    }
}