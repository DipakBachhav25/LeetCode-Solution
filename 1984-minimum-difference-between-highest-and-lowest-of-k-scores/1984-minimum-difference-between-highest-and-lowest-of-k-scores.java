class Solution {
    public int minimumDifference(int[] nums, int k) {
        if(k == 1) return 0;
        int n = nums.length;
        Arrays.sort(nums);
        int ans = nums[k-1] - nums[0];
        
        for(int i=1; i<n-k+1; i++){
            int currMinDiff = nums[i+k-1] - nums[i];
            ans = Math.min(ans, currMinDiff);
        }

        return ans;
    }
}