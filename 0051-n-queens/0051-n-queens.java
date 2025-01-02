class Solution {
    static List<List<String>> mainList = new ArrayList<>();

    public static void addSolution(char[][] board){
        List<String> ls = new ArrayList<>();
        for(int i=0; i<board.length; i++){
            String temp = "";
            for(int j=0; j<board.length; j++){
                if(board[i][j] == 'Q'){
                    temp += 'Q';
                } else {
                    temp += '.';
                }
            }
            ls.add(temp);
        }

        mainList.add(ls);
    }

    public static boolean isSafe(char[][] board, int row, int col){
        // Check for vertical
        for(int i=row-1; i>=0; i--){
            if(board[i][col] == 'Q'){
                return false;
            }
        }

        // Check for left diagonal
        for(int i=row-1, j=col-1; i>=0 && j>=0; i--, j--){
            if(board[i][j] == 'Q'){
                return false;
            }
        }

        // Check for right diagonal
        for(int i=row-1, j=col+1; i>=0 && j<board.length; i--, j++){
            if(board[i][j] == 'Q'){
                return false;
            }
        }

        return true;
    }

    public static void nQueen(char[][] board, int row){
        if(row == board.length){
            addSolution(board);
            return;
        }

        for(int j=0; j<board.length; j++){
            if(isSafe(board, row, j)){
                board[row][j] = 'Q';
                nQueen(board, row+1);
                board[row][j] = '.';
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        mainList = new ArrayList<>();
        if(n == 1){
            List<String> ls = new ArrayList<>();
            ls.add("Q");
            mainList.add(ls);
            return mainList;
        }

        char[][] board = new char[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                board[i][j] = '.';
            }
        }

        nQueen(board, 0);

        return mainList;
    }
}