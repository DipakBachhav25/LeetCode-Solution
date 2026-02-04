class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long INF = Long.MIN_VALUE/2;

        long[] dp0 = new long[n];
        long[] dp1 = new long[n];
        long[] dp2 = new long[n];
        long[] dp3 = new long[n];

        for(int i=0; i<n; i++){
            dp0[i] = dp1[i] = dp2[i] = dp3[i] = INF;
        }

        dp0[0] = nums[0];
        long ans = INF;

        for(int i=1; i<n; i++){
            dp0[i] = nums[i];

            if(nums[i] > nums[i-1]){
                dp1[i] = Math.max(dp1[i-1], dp0[i-1]) + nums[i];
                dp3[i] = Math.max(dp3[i-1], dp2[i-1]) + nums[i];
            }

            if(nums[i] < nums[i-1]){
                dp2[i] = Math.max(dp2[i-1], dp1[i-1]) + nums[i];
            }

            ans = Math.max(ans, dp3[i]);
        }

        return ans;
    }
}