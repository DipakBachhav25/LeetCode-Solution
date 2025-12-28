class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int i = 0, j = n-1;
        int count = 0;

        while( i < m && j >= 0){
            if(grid[i][j] < 0) {
                count += (m-i);
                j -= 1;
            }else{
                i += 1;
            }
        }

        return count;
    }
}