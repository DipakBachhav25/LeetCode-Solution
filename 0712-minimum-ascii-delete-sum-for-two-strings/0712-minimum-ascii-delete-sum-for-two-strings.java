class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int[] r : dp){
            Arrays.fill(r, -1);
        }
        return findMinASCII(s1, s2, 0, 0, dp);
    }

    public int findMinASCII(String s1, String s2, int m, int n, int[][] dp){
        if(m >= s1.length() && n >= s2.length()) return 0;

        if(dp[m][n] != -1) return dp[m][n];

        if(m >= s1.length()){
            return dp[m][n] = s2.charAt(n) + findMinASCII(s1, s2, m, n+1,  dp);
        }else if(n >= s2.length()){
            return dp[m][n] = s1.charAt(m) + findMinASCII(s1, s2, m+1, n,  dp);
        }

        if(s1.charAt(m) == s2.charAt(n)){
            return dp[m][n] = findMinASCII(s1, s2, m+1, n+1,  dp);     
        }

        int deleteS1 = s1.charAt(m) + findMinASCII(s1, s2, m+1, n,  dp);
        int deleteS2 = s2.charAt(n) + findMinASCII(s1, s2, m, n+1,  dp);

        return dp[m][n] = Math.min(deleteS1, deleteS2);

    }
}