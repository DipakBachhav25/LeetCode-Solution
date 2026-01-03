class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] multiply(long[][] A, long[][] B) {
        long[][] res = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                res[i][j] = (A[i][0] * B[0][j] + A[i][1] * B[1][j]) % MOD;
            }
        }
        return res;
    }

    private long[][] power(long[][] M, int exp) {
        long[][] res = {{1, 0}, {0, 1}};
        while (exp > 0) {
            if ((exp & 1) == 1) res = multiply(res, M);
            M = multiply(M, M);
            exp >>= 1;
        }
        return res;
    }

    public int numOfWays(int n) {
        if (n == 1) return 12;

        long[][] baseMatrix = {{3, 2}, {2, 2}};
        long[][] M = power(baseMatrix, n - 1);


        long two = (M[0][0] * 6 + M[0][1] * 6) % MOD;
        long three = (M[1][0] * 6 + M[1][1] * 6) % MOD;

        return (int) ((two + three) % MOD);
    }
}