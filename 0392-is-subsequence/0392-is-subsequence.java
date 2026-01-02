class Solution {
    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        
        boolean[][] dp = new boolean[n + 1][m + 1];
      
        for (int j = 0; j <= m; j++) {
            dp[n][j] = true;
        }
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = dp[i][j + 1];
                }
            }
        }
        
        return dp[0][0];

    }
}