class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        char[][] grid = new char[m][n];

        //Assume G: guard, W: wall, D: guarded
        for(int[] g : guards) grid[g[0]][g[1]] = 'G';
        for(int[] w : walls) grid[w[0]][w[1]] = 'W';

        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(grid[r][c] == 'G'){
                    for(int i=r-1; i>=0; i--){
                        if(grid[i][c] == 'G' || grid[i][c] == 'W') break;
                        if(grid[i][c] == 0) grid[i][c] = 'D';
                    }
                    for(int i=r+1; i<m; i++){
                        if(grid[i][c] == 'G' || grid[i][c] == 'W') break;
                        if(grid[i][c] == 0) grid[i][c] = 'D';
                    }
                    for(int j=c-1; j>=0; j--){
                        if(grid[r][j] == 'G' || grid[r][j] == 'W') break;
                        if(grid[r][j] == 0) grid[r][j] = 'D';
                    }
                    for(int j=c+1; j<n; j++){
                        if(grid[r][j] == 'G' || grid[r][j] == 'W') break;
                        if(grid[r][j] == 0) grid[r][j] = 'D';
                    }
                }
            }
        }

        int unGuardCount = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0) unGuardCount++;
            }
        }
        return unGuardCount;
    }
}