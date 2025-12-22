class Solution {
    public int minDeletionSize(String[] strs) {
        // int n = strs[0].length();
        // int m = strs.length;

        // boolean[][] canFollow = new boolean[n][n];
        // for (int i = 0; i < n; i++) {
        //     for (int j = i + 1; j < n; j++) {
        //         boolean ok = true;
        //         for (int k = 0; k < m; k++) {
        //             if (strs[k].charAt(i) > strs[k].charAt(j)) {
        //                 ok = false;
        //                 break;
        //             }
        //         }
        //         canFollow[i][j] = ok;
        //     }
        // }

        // int[] dp = new int[n];
        // Arrays.fill(dp, 1);
        // int maxLen = 1;
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < i; j++) {
        //         if (canFollow[j][i]) {
        //             dp[i] = Math.max(dp[i], dp[j] + 1);
        //         }
        //     }
        //     maxLen = Math.max(maxLen, dp[i]);
        // }

        // return n - maxLen;

        int m = strs.length;
        int n = strs[0].length();

        char[][] a = new char[m][n];
        for (int i = 0; i < m; i++) {
            a[i] = strs[i].toCharArray();
        }

        int[] dp = new int[n];
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                boolean ok = true;

                for (int k = 0; k < m; k++) {
                    if (a[k][j] > a[k][i]) {
                        ok = false;
                        break;
                    }
                }

                if (ok && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
            }
        }

        return n - maxLen;
    }
}