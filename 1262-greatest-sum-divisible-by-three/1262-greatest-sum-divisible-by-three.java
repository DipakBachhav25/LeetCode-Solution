class Solution {
    public int maxSumDivThree(int[] nums) {
        final int NEGATIVE_INFINITY = -(1 << 30);
        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = NEGATIVE_INFINITY;
        dp[2] = NEGATIVE_INFINITY;

        for(int n : nums){
            int[] tmp_dp = dp.clone();
            for(int rem=0; rem<3; rem++){
                int prev = (rem-n%3+3) % 3;
                dp[rem] = Math.max(tmp_dp[rem], tmp_dp[prev]+n);
            }
        }

        return dp[0];
    }
}