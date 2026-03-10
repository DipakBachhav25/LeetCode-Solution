class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1000000007;
        
        int[][] dp0 = new int[zero + 1][one + 1];
        int[][] dp1 = new int[zero + 1][one + 1];

        int maxZeroBase = Math.min(zero, limit);
        for (int i = 1; i <= maxZeroBase; i++) {
            dp0[i][0] = 1;
        }
        
        int maxOneBase = Math.min(one, limit);
        for (int j = 1; j <= maxOneBase; j++) {
            dp1[0][j] = 1;
        }

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                
                dp0[i][j] = (dp0[i - 1][j] + dp1[i - 1][j]) % MOD;
                if (i > limit) {
                    dp0[i][j] = (dp0[i][j] - dp1[i - limit - 1][j] + MOD) % MOD;
                }

                dp1[i][j] = (dp0[i][j - 1] + dp1[i][j - 1]) % MOD;
                if (j > limit) {
                    dp1[i][j] = (dp1[i][j] - dp0[i][j - limit - 1] + MOD) % MOD;
                }
            }
        }

        return (dp0[zero][one] + dp1[zero][one]) % MOD;
    }
}