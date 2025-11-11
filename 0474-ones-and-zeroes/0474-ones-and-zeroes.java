class Solution {

    private long countZeroesOrOnes(String s, char ch){
        return s.chars().filter(c -> c == ch).count();
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];

        for(String s : strs){
            int zeroes = (int) countZeroesOrOnes(s, '0');
            int ones = (int) countZeroesOrOnes(s, '1');
            for(int i=m; i>=zeroes; i--){
                for(int j=n; j>=ones; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i-zeroes][j-ones]+1);
                }
            }
        }

        return dp[m][n];
    }
}