class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] r = new int[9];
        int[] c = new int[9];
        int[] grid = new int[9];

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] == '.') continue;

                int curr = board[i][j]-'0';
                int bitMask = 1 << curr;

                int boxIdx = (i/3)*3 + (j/3);

                if((r[i] & bitMask) != 0 || (c[j] & bitMask) != 0 || (grid[boxIdx] & bitMask) != 0) return false;

                r[i] |= bitMask;
                c[j] |= bitMask;
                grid[boxIdx] |= bitMask;
            }
        }

        return true;
    }
}