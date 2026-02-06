class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        int l = 0;
        int ans =  0;

        Arrays.sort(nums);

        for(int i=0; i<n; i++){
            while((long) nums[i] > (long) nums[l]*k) l++;
            ans = Math.max(ans, i-l+1);
        }

        return n-ans;
    }
}