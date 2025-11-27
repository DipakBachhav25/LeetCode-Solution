class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        final long INF = 1L << 62;
        long[] minPref = new long[k];
        Arrays.fill(minPref, INF);
        minPref[0] = 0;
        long pref = 0;
        long best = -INF;
        
        for (int i = 0; i < nums.length; ++i) {
            pref += nums[i];
            int r = (i + 1) % k;
            best = Math.max(best, pref - minPref[r]);
            if (pref < minPref[r]) minPref[r] = pref;
        }
        return best;
    }
}