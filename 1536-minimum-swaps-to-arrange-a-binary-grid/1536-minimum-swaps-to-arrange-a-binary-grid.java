class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] tmp = new int[n];

        for(int i=0; i<n; i++){
            int count = 0;
            for(int j=n-1; j>=0; j--){
                if(grid[i][j] == 0) count++;
                else break;
            }
            tmp[i] = count;
        }

        int swaps = 0;

        for(int i=0; i<n; i++){
            int tz = n-1-i;
            int idx = -1;

            for(int j=i; j<n; j++){
                if(tmp[j] >= tz){
                    idx = j;
                    break;
                }
            }

            if(idx == -1) return -1;

            swaps += (idx-i);

            int validRZ = tmp[idx];
            for(int j=idx; j>i; j--){
                tmp[j] = tmp[j-1];
            }
            tmp[i] = validRZ;
        }

        return swaps;
    }
}