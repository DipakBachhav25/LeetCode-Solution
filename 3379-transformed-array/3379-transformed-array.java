class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        for(int i=0; i<n; i++){
            if(nums[i] > 0){
                int idx = (i+nums[i])%n;
                ans[i] = nums[idx];
            }else if(nums[i] < 0){
                int idx = ((i+nums[i])%n + n)%n;
                ans[i] = nums[idx];
            }else{
                ans[i] = nums[i];
            }
        }
        return ans;
    }
}